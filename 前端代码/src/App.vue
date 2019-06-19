<template>
	<div id="app">
		<router-view />
	</div>
</template>

<script>
	import Axios from 'axios';
	import qs from 'qs'
	export default {
		name: 'App',
		data() {
			return {
				loading: null
			}
		},
		methods: {
			ShowLoading() {
				if(this.loading != null) return;
				this.loading = this.$loading();
			},
			CloseLoading() {
				if(this.loading == null) return;
				this.loading.close();
				this.loading = null;
			},
		},
		mounted() {
			this.$RefreshMenu('CourseTypeMenu');
			this.$Bus.$on(this.$EmitType.ShowLoading, this.ShowLoading)
			this.$Bus.$on(this.$EmitType.CloseLoading, this.CloseLoading)
		}
	}
</script>

<style lang="stylus" scoped>
	#app
		position: fixed;
		top: 0;
		width: 100%;
		bottom: 0;
		overflow: hidden;
</style>
