<template>
	<span>
		<span v-for="(item, index) in list" :key="item.name">
			<span v-if="item.clist">
				<el-submenu :index="ItemIndex + '-' + index">
					<template slot="title">
						<i :class="'iconfont ' + item.icon" v-if="item.icon"></i>
						<span class="el-submenu-title">{{item.name}}</span>
					</template>
					<LeftAsideItem :list="item.clist" :MenuName="item.name" :ItemIndex="ItemIndex + '-' + index" />
				</el-submenu>
			</span>
			<el-menu-item v-if="!item.clist" :index="item.router" @click="SelectSide(item.router, item.name)">
				<i :class="'iconfont ' + item.icon" v-if="item.icon"></i>
				<span slot="title">{{item.name}}</span>
			</el-menu-item>
		</span>
	</span>
</template>

<script>
	import { createNamespacedHelpers } from 'vuex'
	import LeftAsideItem from './LeftAsideItem'
	export default {
		name: 'LeftAsideItem',
		props: {
			list: Array,
			ItemIndex: {
				type: String,
				default: "0"
			},
			MenuName: {
				type: String,
				default: ""
			}
		},
		components: {
			LeftAsideItem
		},
		methods: {
			SelectSide(router, title) {
				document.title = title;
				this.$router.push(`/main/${router}?list=${this.MenuName}/${title}`)
			}
		}
	}
</script>