<template>
	<div class="TeaAddCourse-box">
		<div class="input-item">
			<div class="item-title">工号</div>
			<div class="item-input">
				<input type="number" v-model="form.teacherNumber" class="layui-input" placeholder="请输入工号" />
			</div>
		</div>
		<div class="input-item">
			<div class="item-title">姓名</div>
			<div class="item-input">
				<input type="text" v-model="form.teacherName" class="layui-input" placeholder="请输入姓名" />
			</div>
		</div>
		<div class="input-item">
			<div class="item-title">密码</div>
			<div class="item-input">
				<input type="password" v-model="form.teacherPassword" class="layui-input" placeholder="请输入密码" />
			</div>
		</div>
		<div class="input-item">
			<div class="item-title">确认密码</div>
			<div class="item-input">
				<input type="password" v-model="teacherPassworded" class="layui-input" placeholder="请再次输入密码" />
			</div>
		</div>
		<div class="input-item">
			<div class="item-title">性别</div>
			<div class="item-input">
				<layui-select :value.sync="form.sex" :list="SexList" placeholder="请选择性别" />
			</div>
		</div>
		<div class="input-item">
			<div class="item-title">身份证</div>
			<div class="item-input">
				<input type="number" v-model="form.teacherIdcard" class="layui-input" placeholder="请输入身份证" />
			</div>
		</div>
		<div class="input-item">
			<div class="item-title">年龄</div>
			<div class="item-input">
				<input type="number" v-model="form.age" class="layui-input" placeholder="请输入年龄" />
			</div>
		</div>
		<div class="input-item">
			<div class="item-title"></div>
			<div class="item-input">
				<button class="layui-btn"  @click="OnSubmit">立即提交</button>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'AdminAddTeacher',
		data() {
			return {
				form: {
					teacherNumber: '',
					teacherName: '',
					teacherPassword: '',
					teacherIdcard: '',
					sex: '',
					age: ''
				},
				teacherPassworded: '',
				SexList: [{ name: '男', value: 0 }, { name: '女', value: 1 }]
			}
		},
		methods: {
			OnSubmit() {
				const { teacherNumber, teacherName, teacherPassword, teacherIdcard, sex, age } = this.form;
				if(teacherNumber === '' || teacherName === '' || teacherPassword === '' || teacherIdcard === '' || sex === '' || age === '') {
					this.$ShowToast('请完善信息');
					return;
				}
				if(teacherPassword != this.teacherPassworded) {
					this.$ShowToast('两次输入密码不正确');
					return;
				}
				this.$Post(this.$Api.ADD_TEACHER, {
					teacherNumber, teacherName, teacherPassword, teacherIdcard, sex, age,
				}).then(res => {
					if(res.code == 0) {
						this.$ShowToast('添加成功');
					} else {
						this.$ShowToast(res.msg);
					}
				})
			}
		},
		components: {},
	}
</script>

<style lang="stylus" scoped>
	.TeaAddCourse-box
		background: #fff;
		border-radius: 3px;
		padding: 15px;
		.input-item
			width: 325px;
			height: 40px;
			margin-bottom: 15px;
			.item-title
				width: 85px;
				height: 40px;
				line-height: 40px;
				text-align: center;
				float: left;
			.item-input
				float: left;
				width: 240px;
				height: 40px;
</style>