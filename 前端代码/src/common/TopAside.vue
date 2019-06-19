<template>
	<div class="TopAside">
		<span class="list-item" v-for="item in Menu">
			<div class="title fenge">/</div>
			<div class="title menu">{{item}}</div>
		</span>
		<div class="back-btn" v-if="BackShow" @click="GoBack">返回</div>
	</div>
</template>

<script>
	import * as Api from '@/utils/ApiInterface'
	import Axios from 'axios'

	export default {
		name: "TopAside",
		methods: {
			GoBack() {
				this.$router.go(-1)
			}
		},
		computed: {
			Menu() {
				const { list } = this.$route.query
				return list.split('/');
			},
			BackShow() {
				const { bcs } = this.$route.query;
				if(bcs) {
					return true;
				} else {
					return false;
				}
			}
		}
	}
</script>

<style lang="stylus" scoped>
	.list-item
		&:first-child
			.fenge
				display: none;
	.TopAside
		background: #fff;
		height: 45px;
		line-height: 45px;
		padding: 0 15px;
		.fenge
			margin: 0 10px;
		.title
			float: left;
		.back-btn
			float: right;
			cursor: pointer;
</style>