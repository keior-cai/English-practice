<template>
	<div class="TeaAddCourse-box">
		<div class="input-item">
			<div class="item-title">旧密码</div>
			<div class="item-input">
				<input type="password" v-model="form.pwd1" class="layui-input" placeholder="请输入旧密码" />
			</div>
		</div>
		<div class="input-item">
			<div class="item-title">新密码</div>
			<div class="item-input">
				<input type="password" v-model="form.pwd2" class="layui-input" placeholder="请输入新密码" />
			</div>
		</div>
		<div class="input-item">
			<div class="item-title">确认密码</div>
			<div class="item-input">
				<input type="password" v-model="pwd2ed" class="layui-input" placeholder="请再次输入新密码" />
			</div>
		</div>
		<div class="input-item">
			<div class="item-title"></div>
			<div class="item-input">
				<button class="layui-btn" @click="OnSubmit">立即提交</button>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'TeacherPassword',
		data() {
			return {
				form: {
					pwd1: '',
					pwd2: '',
				},
				pwd2ed: '',
			}
		},
		methods: {
			OnSubmit() {
				const { pwd1, pwd2 } = this.form;
				if(pwd1 == '' || pwd2 == '') {
					this.$ShowToast('请完善信息');
					return;
				}
				if(pwd2 != this.pwd2ed) {
					this.$ShowToast('两次输入密码不正确');
					return;
				}
				this.$Post(this.$Api.CHANGE_TEACHER_PASSWORD, {
					pwd1, pwd2
				}).then(res => {
					if(res.code == 0) {
						this.$ShowToast('修改成功');
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