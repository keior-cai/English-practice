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
			<el-button icon="el-icon-search" type="primary" @click="Search">搜索</el-button>
		</div>
		<div class="table-box" v-if="show" :key="chooseClassId">
			<layui-table :element.sync="table" v-bind="TableConfig" @tool="TableTool" />
		</div>
	</div>
</template>

<script>
	export default {
		name: 'TeacherLookStudent',
		data() {
			return {
				classId: '',
				chooseClassId: '',
				classList: [],
				show: false,
				table: null
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
				if(this.classId == '') {
					this.$ShowToast('请选择班级');
					return;
				}
				this.show = true;
				this.chooseClassId = this.classId;
			},
			TableTool({ event, data }) {
				if(event == 'del') {
					this.$confirm('此操作将永久删除该学生, 是否继续?', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
					}).then(() => {
						this.$Post(this.$Api.DEL_TEA_STUDENT, {
							id: data.studentId
						}).then(res => {
							if(res.code == 0) {
								this.$ShowToast('删除成功');
								this.table.reloadTable()
							} else {
								this.$ShowToast(res.msg);
							}
						})
					}).catch(() => {});
				}
			},
		},
		computed: {
			TableConfig() {
				let info = JSON.parse(JSON.stringify(this.$TableConfig.GET_TEA_ALL_STUDENT_TABLE));
				info.where = {
					classId: this.chooseClassId
				}
				return info;
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