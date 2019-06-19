<template>
	<div>
		<div class="logo">
			<div class="logo-text">英语听说练习系统</div>
		</div>
		<el-menu
			class="left-side-menu-vertical"
			active-text-color="#ffd04b"
			background-color="#545c64"
			text-color="#fff">
			<LeftAsideItem :list="AsideList" />
		</el-menu>
	</div>
</template>

<script>
	import { createNamespacedHelpers } from 'vuex'
	const { mapState: CommonState, mapGetters: CommonGertters } = createNamespacedHelpers('Common')
	import LeftAsideItem from './LeftAsideItem.vue'
	export default {
		name: "LeftAside",
		data () {
			return {}
		},
		methods: {},
		computed: {
			...CommonGertters(['AsideList'])
		},
		components: {
			LeftAsideItem
		}
	}
</script>

<style lang="stylus" scoped>
	.logo
		color: #fff;
		width: 100%;
		overflow: none;
		.logo-text
			width: 200px;
			line-height: 60px;
			text-align: center;
			font-size: 16px;
	.left-side-menu-vertical
		border-right: none;
		width: 200px;
	.el-menu--collapse
		width: 64px !important;
	.el-menu--collapse >>> .el-submenu-title
		display: none;
	.el-menu--collapse >>> .el-submenu__icon-arrow
		display: none;
</style>