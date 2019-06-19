<template>
	<div class="TeacherLookStudent-box">
		<div class="tool-row">
			<div class="title">筛选:</div>
			<el-select v-model="classId" placeholder="请选择班级" clearable>
				<el-option
					v-for="item in classList"
					:key="item.classId"
					:label="item.classNumber"
					:value="item.classId">
				</el-option>
			</el-select>
			<el-select v-model="type" placeholder="请选择作业类型" clearable>
				<el-option
					v-for="item in typeList"
					:key="item.value"
					:label="item.name"
					:value="item.value">
				</el-option>
			</el-select>
			<el-button icon="el-icon-search" type="primary" @click="Search">搜索</el-button>
		</div>
		<div class="table-box" v-if="show" :key="chooseClassId + '_' + chooseType">
			<layui-table :element.sync="table" v-bind="TableConfig" @tool="TableTool" />
		</div>
		<audio :src="playUrl" id="muse" hidden=""></audio>
	</div>
</template>

<script>
	export default {
		name: 'TeacherLookHomeWork',
		data() {
			return {
				classId: '',
				chooseClassId: '',
				classList: [],
				type: '',
				chooseType: '',
				typeList: [{ name: '口语', value: 0 }, { name: '听力', value: 1 }],
				show: false,
				table: null,
				playUrl: ''
			}
		},
		methods: {
			GetAllClass() {
				this.$Get(this.$Api.GET_TEA_ALL_CLASS).then(res => {
					if(res.code == 0) {
						this.classList = res.data
					} else {
						this.$ShowToast(res.msg);
					}
				})
			},
			Search() {
				if(this.classId === '') {
					this.$ShowToast('请选择班级');
					return;
				}
				if(this.type === '') {
					this.$ShowToast('请选择作业类型');
					return;
				}
				this.show = true;
				this.chooseClassId = this.classId;
				this.chooseType = this.type;
			},
			TableTool({ event, data }) {
				if(event === 'pf') {
					this.$prompt('请输入评分', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						inputType: 'number'
					}).then(({ value }) => {
						if(!value) {
							this.$ShowToast('请输入评分');
							return;
						}
						if(value < 0) {
							this.$ShowToast('请输入正确评分');
							return;
						}
						console.log(data)
						this.$Post(this.$Api.LURU_CHENGJI, {
							id: data.id,
							source: value
						}).then(res => {
							if(res.code == 0) {
								this.$ShowToast('评分成功')
								this.table.reloadTable();
							} else {
								this.$ShowToast(res.msg);
							}
						})
					}).catch(() => {});
				} else if(event === 'play') {
					this.playUrl = data.homeWorkPath;
					var audio = document.getElementById('muse');
					audio.pause();
					audio.play();
				}
			}
		},
		computed: {
			TableConfig() {
				if(this.show && this.chooseType == 0) {
					let info = JSON.parse(JSON.stringify(this.$TableConfig.GET_TEACHER_HOME_WORK_READ));
					info.where = {
						classId: this.chooseClassId,
						homeWorkType: this.chooseType
					}
					return info;
				} else if(this.show && this.chooseType == 1) {
					let info = JSON.parse(JSON.stringify(this.$TableConfig.GET_TEACHER_HOME_WORK_LISTEN));
					info.where = {
						classId: this.chooseClassId,
						homeWorkType: this.chooseType
					}
					return info;
				}
			}
		},
		components: {},
		mounted() {
			this.GetAllClass()
		}
	}
</script>

<style lang="stylus" scoped>
	.el-button
		margin-bottom: 10px;
	.el-input,
	.el-select
		float: left;
		width: 170px;
		margin-right: 10px;
		margin-bottom: 10px;
	.tool-row
		margin-bottom: 15px;
		background: #fff;
		border-radius: 3px;
		overflow: hidden;
		padding: 10px 15px 0;
		.title
			float: left;
			height: 40px;
			line-height: 40px;
			margin-right: 20px;
	.table-box
		padding: 10px 15px;
		background: #fff;
		border-radius: 3px;
</style>