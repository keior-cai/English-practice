<template>
	<div class="login-box">
		<div class="login-container">
			<div class="login-title">英语听说练习系统</div>
			<div class="input-item">
				<div class="input-icon">
					<span class="iconfont">&#xe639;</span>
				</div>
				<div class="input-cin">
					<input type="text" v-model="form.account" placeholder="请输入帐号" />
				</div>
			</div>
			<div class="input-item">
				<div class="input-icon">
					<span class="iconfont">&#xe652;</span>
				</div>
				<div class="input-cin">
					<input type="password" v-model="form.password" placeholder="请输入密码" />
					<!-- <input type="file"  placeholder="请输入密码" /> -->
				</div>
			</div>
			<div class="select-info">
				<div class="select-title">身份选择</div>
				<mu-radio :value="1" v-model="radio" color="#009688" label="学生"></mu-radio>
				<mu-radio :value="2" v-model="radio" color="#009688" label="老师"></mu-radio>
				<mu-radio :value="3" v-model="radio" color="#009688" label="管理员"></mu-radio>
			</div>
			<div class="login-btn" @click="Login">登录</div>
		</div>
	</div>
</template>

<script>
	import { createNamespacedHelpers } from 'vuex';
	const { mapMutations: CommonMutations } = createNamespacedHelpers('Common');
	export default {
		name: 'Login',
		data() {
			return {
				radio: 1,
				form: {
					account: '',
					password: ''
				}
			}
		},
		methods: {
			Login() {
				// this.SaveProple(this.radio)
				// this.$router.push('/main/console?list=首页/控制台');
				// return;
				this.$Post(this.Request.url, this.Request.send).then(res => {
					if(res.code == 0) {
						this.$ShowToast('登陆成功')
						this.SaveProple(this.radio)
						localStorage.setItem("token", res.data)
						this.$router.push('/main/console?list=首页/控制台');
					} else {
						this.$ShowToast(res.msg)
					}
				})
			},
			...CommonMutations(['SaveProple'])
		},
		computed: {
			Request() {
				const { DO_LOGIN_STUDENT, DO_LOGIN_TEACHER, DO_LOGIN_ADMIN } = this.$Api
				switch (this.radio) {
					case 1:
						return {
							url: DO_LOGIN_STUDENT,
							send: {
								number: this.form.account,
								pwd: this.form.password
							}
						};
					case 2:
						return {
							url: DO_LOGIN_TEACHER,
							send: {
								number: this.form.account,
								pwd: this.form.password
							}
						};
					case 3:
						return {
							url: DO_LOGIN_ADMIN,
							send: {
								number: this.form.account,
								pwd: this.form.password
							}
						};
				}
			}
		}
	}
</script>

<style lang="stylus" scoped>
	@import '~@/assets/styles/varibles.styl'
	.login-box
		tlrb()
		background: #EBE5E5;
		.login-container
			width: 400px;
			height: 300px;
			MiddleCenter()
			.login-title
				text-align: center;
				font-size: 30px;
			.input-item
				width: 350px;
				margin: 15px auto;
				height: 45px;
				background: #fff;
				display: flex;
				border: 1px solid #ccc;
				border-radius: 2px;
				overflow: hidden;
				.input-icon
					float: left;
					width: 45px;
					height: 45px;
					text-align: center;
					line-height: 45px;
					.iconfont
						font-size: 18px;
				.input-cin
					flex: 1;
					input
						border: none;
						height: 45px;
						width: 100%;
			.select-info
				height: 24px;
				line-height: 24px;
				width: 350px;
				margin: 0 auto 15px;
				.select-title
					height: 24px;
					line-height: 24px;
					float: left;
					margin-right: 10px;
			.login-btn
				width: 350px;
				height: 40px;
				text-align: center;
				line-height: 40px;
				margin: 0 auto;
				color: #fff;
				border-radius: 2px;
				background: #009688;
				cursor: pointer;
				margin-bottom: 10px;
				&:hover
					opacity: .8;
			.register-btn
				width: 350px;
				height: 40px;
				text-align: center;
				line-height: 40px;
				margin: 0 auto;
				color: #fff;
				border-radius: 2px;
				background: #2196f3;
				cursor: pointer;
				&:hover
					opacity: .8;
</style>