<template>
	<div class="AdminLookStudent-box">
		<layui-table :element.sync="table" :key="key_number" v-bind="$TableConfig.GET_ALL_AURAL_TABLE" @tool="TableTool" />
		<axc-container-popup :opacity="0.4" :open.sync="open" title="修改听力题目">
			<OpenWindow :OpenInfo="OpenInfo" @refresh="key_number++" :open.sync="open" />
		</axc-container-popup>
	</div>
</template>

<script>
	import OpenWindow from './OpenWindow.vue'
	export default {
		name: 'AdminLookAural',
		data() {
			return {
				table: null,
				OpenInfo: { id: '', subjectTitle: '', optionA: '', optionB: '', optionC: '', optionD: '', answer: ''},
				open: false,
				key_number: 0
			}
		},
		methods: {
			TableTool({ event, data }) {
				if(event == 'edit') {
					this.OpenInfo = JSON.parse(JSON.stringify(data));
					this.open = true;
				} else if(event == 'del') {
					this.$confirm('此操作将永久删除该听力题目, 是否继续?', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
					}).then(() => {
						this.$Post(this.$Api.DEL_AURAL, {
							id: data.subjectId
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
			}
		},
		computed: {},
		components: {
			OpenWindow
		}
	}
</script>

<style lang="stylus" scoped>
	.AdminLookStudent-box
		background: #fff;
		border-radius: 3px;
		padding: 5px 15px;
</style>