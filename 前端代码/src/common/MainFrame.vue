<template>
	<div class="main-frame-box">
		<el-container style="position: absolute; top: 0; left: 0; right: 0; bottom: 0;">
			<el-aside style="width: auto;">
				<LeftAside />
				<div class="side-bottom">
					<!-- 给予您最好的服务 -->
					<div class="logout" @click="Logout">
						<span class="iconfont">&#xe659;</span>注销
					</div>
				</div>
			</el-aside>
			<div class="main-container">
				<div class="main-header">
					<TopAside />
				</div>
				<div class="main-content">
					<router-view></router-view>
				</div>
			</div>
		</el-container>
	</div>
</template>

<script>
	import LeftAside from './LeftAside'
	import TopAside from './TopAside'
	export default {
		name: 'MainFrame',
		data() {
			return {}
		},
		methods: {
			Logout() {
				this.$router.replace('/login')
			}
		},
		computed: {},
		components: {
			LeftAside,
			TopAside
		},
		props: {}
	}
</script>

<style lang="stylus" scoped>
	.main-container
		position: fixed;
		top: 0;
		left: 200px;
		right: 0;
		bottom: 0;
		overflow-y: scroll;
	.main-header
		height: 75px;
		padding: 15px;
		box-sizing: border-box;
		background-color: #eee;
		border-radius: 3px;
	.el-aside
		background-color: #545c64;
		position: relative;
		overflow: hidden;
		.side-bottom
			height: 40px;
			line-height: 40px;
			position: absolute;
			bottom: 0;
			left: 0;
			right: 0;
			color: #ccc;
			box-sizing: border-box;
			padding-left: 10px;
			.logout
				float: right;
				width: 70px;
				cursor: pointer;
				text-align: center;
				background-color: #545c64;
				&:hover
					opacity: .8;
				.iconfont
					margin-right: 5px;
		.el-menu
			border-right: none;
	.el-container
		background-color: #eee;
		color: #333;
		padding: 0;
	.main-content
		padding: 0 15px 15px;
</style>