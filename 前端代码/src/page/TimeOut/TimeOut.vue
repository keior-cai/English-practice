<template>
	<div class="time-out-box">
		<div class="time-container">
			<img src="@/assets/Images/timeout1.png" alt="timeout" class="img1" />
			<img src="@/assets/Images/timeout2.png" alt="timeout" class="img2" />
			<div class="time-text" style="margin-top: 310px;">登录超时，请重新登录</div>
			<div class="time-text" style="font-size: 18px;">
				正在为您自动跳转到
				<span class="go-login" @click="GoLogin">登录页面</span>
				...({{num}}秒)
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'TimeOut',
		data() {
			return {
				time: null,
				num: 3
			}
		},
		methods: {
			GoLogin() {
				this.$router.push('/login')
				clearInterval(this.time)
			}
		},
		computed: {},
		components: {},
		props: {},
		mounted() {
			this.time = setInterval(() => {
				this.num--;
				if(this.num == 0) {
					this.$router.push('/login')
					clearInterval(this.time)
				}
			}, 1000)
		}
	}
</script>

<style lang="stylus" scoped>
	@import '~@/assets/styles/varibles.styl'
	.time-out-box
		tlrb()
		background: #f8f8f8;
		.time-container
			width: 600px;
			height: 430px;
			position: relative;
			MiddleCenter()
			.img1
				width: 280px;
				top: 100px;
				left: 50px;
				position: absolute;
			.img2
				position: absolute;
				left: 350px;
			.time-text
				text-align: center;
				font-size: 20px;
				margin-bottom: 5px;
				.go-login
					text-decoration: underline;
					cursor: pointer;
</style>