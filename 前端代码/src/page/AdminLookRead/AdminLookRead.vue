<template>
	<div class="AdminLookStudent-box">
		<div class="tool-row">
			<div class="layui-btn layui-btn-normal layui-btn-sm" @click="AddRead">添加口语题库</div>
		</div>
		<layui-table :element.sync="table" :key="key_number" v-bind="$TableConfig.GET_ALL_READ_TABLE" @tool="TableTool" />
		<axc-container-popup :opacity="0.4" :open.sync="open" title="修改口语文本">
			<OpenWindow :OpenInfo="OpenInfo" @refresh="key_number++" :open.sync="open" />
		</axc-container-popup>
		<axc-container-popup :opacity="0.4" :open.sync="openAdd" title="添加口语文本">
			<OpenWindowAdd @refresh="key_number++" :open.sync="openAdd" />
		</axc-container-popup>
	</div>
</template>

<script>
	import OpenWindow from './OpenWindow.vue'
	import OpenWindowAdd from './OpenWindowAdd.vue'
	export default {
		name: 'AdminLookRead',
		data() {
			return {
				table: null,
				OpenInfo: { oralLanguageId: '', oralLanguageText: '' },
				open: false,
				openAdd: false,
				key_number: 0
			}
		},
		methods: {
			TableTool({ event, data }) {
				if(event == 'edit') {
					this.OpenInfo = JSON.parse(JSON.stringify(data));
					this.open = true;
				} else if(event == 'del') {
					this.$confirm('此操作将永久删除该口语题目, 是否继续?', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
					}).then(() => {
						this.$Post(this.$Api.DEL_READ, {
							id: data.oralLanguageId
						}).then(res => {
							if(res.code == 0) {
								this.$ShowToast('删除成功');
								this.key_number++;
							} else {
								this.$ShowToast(res.msg);
							}
						})
					}).catch(() => {});
				}
			},
			AddRead() {
				this.openAdd = true;
			}
		},
		computed: {},
		components: {
			OpenWindow,
			OpenWindowAdd
		}
	}
</script>

<style lang="stylus" scoped>
	.AdminLookStudent-box
		background: #fff;
		border-radius: 3px;
		padding: 10px 15px;
</style>