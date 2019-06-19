<template>
	<span class="layui-form">
		<div :id="treeId"></div>
	</span>
</template>

<script>
	import { GetComponentsID } from '../lib/common'
	export default {
		name: 'layauthtree',
		data() {
			return {
				authtree: null
			}
		},
		methods: {
			InitTree() {
				this.authtree.render(`#${this.treeId}`, this.list, {inputname: `${this.treeId}[]`, layfilter: this.treeId, openall: false});
				form.on(`checkbox(${this.treeId})`, (data) => {
					setTimeout(() => {
						let leaf = this.authtree.getLeaf(`#${this.treeId}`);
						this.$emit('updata:value', leaf)
					}, 100)
				})
			}
		},
		computed: {
			treeId() {
				return GetComponentsID()
			}
		},
		props: {
			list: [Array],
			value: [Array],
		},
		mounted() {
			layui.use(['layer','form','authtree'], () => {
				this.authtree = layui.authtree;
				this.InitTree();
			})
		},
		watch: {
			list() {
				this.InitTree()
			}
		}
	}
</script>

<style lang="stylus" scoped></style>