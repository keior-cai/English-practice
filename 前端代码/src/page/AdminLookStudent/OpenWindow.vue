<template>
	<div class="OpenWindow-box">
		<el-form :inline="true" ref="formform" :model="OpenInfo" label-width="100px">
			<el-form-item label="学号" prop="studentNumber">
				<el-input v-model="OpenInfo.studentNumber" :disabled="true" placeholder="请输入学号"></el-input>
			</el-form-item>
			<el-form-item label="姓名" prop="studentName">
				<el-input v-model="OpenInfo.studentName" placeholder="请输入姓名"></el-input>
			</el-form-item>
			<el-form-item label="身份证" prop="studentIdcard">
				<el-input v-model="OpenInfo.studentIdcard" placeholder="请输入身份证"></el-input>
			</el-form-item>
			<el-form-item label="性别" prop="sex">
				<el-select v-model="OpenInfo.sex" placeholder="请选择性别">
					<el-option
						v-for="item in SexList"
						:key="item.value"
						:label="item.name"
						:value="item.value">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="密码" prop="studentPassword">
				<el-input v-model="OpenInfo.studentPassword" placeholder="请输入登录密码" type="password"></el-input>
			</el-form-item>
			<el-form-item label="确认密码" prop="studentPassworded">
				<el-input v-model="studentPassworded" placeholder="请输入确认密码" type="password"></el-input>
			</el-form-item>
		</el-form>
		<el-button type="primary" size="small" class="add-btn" @click="add">确认添加</el-button>
	</div>
</template>

<script>
	import moment from 'moment/moment'
	export default {
		name: 'OpenWindow',
		data() {
			return {
				passworded: '',
				SexList: [{ name: '男', value: 0 }, { name: '女', value: 1 }],
				studentPassworded: ''
			}
		},
		methods: {
			add() {
				const { studentId, studentNumber, studentName, studentPassword, studentIdcard, sex } = this.OpenInfo;
				if(studentNumber === '' || studentName === '' || studentPassword === '' || studentIdcard === '' || sex === '') {
					this.$ShowToast('请完善信息');
					return;
				}
				if(studentPassword != this.studentPassworded) {
					this.$ShowToast('两次输入密码不正确');
					return;
				}
				this.$Post(this.$Api.CHANGE_STUDENT_INFO, {
					studentId, studentNumber, studentName, studentPassword, studentIdcard, sex
				}).then(res => {
					if(res.code == 0) {
						this.$ShowToast('修改成功');
						this.$emit('refresh');
						this.$emit('update:open', false);
					} else {
						this.$ShowToast(res.msg);
					}
				})
			}
		},
		computed: {},
		props: {
			OpenInfo: [Object],
			open: Boolean
		},
		watch: {
			open() {
				this.studentPassworded = this.OpenInfo.studentPassword
			}
		},
	}
</script>

<style lang="stylus" scoped>
	.OpenWindow-box >>> .el-form-item__content
		width: 205px;
	.OpenWindow-box >>> .el-date-editor.el-input,
	.OpenWindow-box >>> .el-date-editor.el-input__inner
		width: 205px !important;
	.OpenWindow-box
		width: 660px;
		height: 240px;
		background: #fff;
		padding-top: 15px;
	.add-btn
		margin-right: 35px;
		margin-top: -10px;
		float: right;
</style>