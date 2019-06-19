<template>
	<div class="TeacherAssignListen-box">
		<div class="tool-row">
			<div class="title">班级:</div>
			<el-select v-model="classId" placeholder="请选择班级" clearable>
				<el-option
					v-for="item in classList"
					:key="item.classId"
					:label="item.classNumber"
					:value="item.classId">
				</el-option>
			</el-select>
			<div class="title">题目数量:</div>
			<el-input v-model="num" placeholder="请输入题目数量" type="number"></el-input>
			<el-button icon="el-icon-search" type="primary" @click="Assign">布置</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'TeacherAssignListen',
		data() {
			return {
				classId: '',
				classList: [],
				num: ''
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
			Assign() {
				const { classId, num } = this;
				if(classId == '' || num == '') {
					this.$ShowToast('请完善信息'); return;
				}
				this.$Post(this.$Api.ADD_CLASS_WORK, {
					classId, num
				}).then(res => {
					if(res.code == 0) {
						this.$ShowToast('布置成功');
					} else {
						this.$ShowToast(res.msg);
					}
				})
			}
		},
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
</style>