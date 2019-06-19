<template>
	<div class="OpenWindow-box">
		<el-form :inline="true" ref="formform" :model="OpenInfo" label-width="100px">
			<el-form-item label="学号" prop="teacherNumber">
				<el-input v-model="OpenInfo.teacherNumber" :disabled="true" placeholder="请输入学号"></el-input>
			</el-form-item>
			<el-form-item label="姓名" prop="teacherName">
				<el-input v-model="OpenInfo.teacherName" placeholder="请输入姓名"></el-input>
			</el-form-item>
			<el-form-item label="身份证" prop="teacherIdcard">
				<el-input v-model="OpenInfo.teacherIdcard" placeholder="请输入身份证"></el-input>
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
			<el-form-item label="密码" prop="teacherPassword">
				<el-input v-model="OpenInfo.teacherPassword" placeholder="请输入登录密码" type="password"></el-input>
			</el-form-item>
			<el-form-item label="确认密码" prop="teacherPassworded">
				<el-input v-model="teacherPassworded" placeholder="请输入确认密码" type="password"></el-input>
			</el-form-item>
			<el-form-item label="年龄" prop="age">
				<el-input v-model="OpenInfo.age" placeholder="年龄"></el-input>
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
				teacherPassworded: ''
			}
		},
		methods: {
			add() {
				const { teacherId, teacherNumber, teacherName, teacherPassword, teacherIdcard, sex, age } = this.OpenInfo;
				if(age === '' || teacherNumber === '' || teacherName === '' || teacherPassword === '' || teacherIdcard === '' || sex === '') {
					this.$ShowToast('请完善信息');
					return;
				}
				if(teacherPassword != this.teacherPassworded) {
					this.$ShowToast('两次输入密码不正确');
					return;
				}
				this.$Post(this.$Api.CHANGE_TEACHER_INFO, {
					teacherId, teacherNumber, teacherName, teacherPassword, teacherIdcard, sex, age
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
				this.teacherPassworded = this.OpenInfo.teacherPassword
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
		height: 310px;
		background: #fff;
		padding-top: 15px;
	.add-btn
		margin-right: 35px;
		margin-top: -10px;
		float: right;
</style>