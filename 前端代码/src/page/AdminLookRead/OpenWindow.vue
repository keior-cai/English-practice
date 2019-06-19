<template>
	<div class="OpenWindow-box">
		<textarea name="desc" v-model="OpenInfo.oralLanguageText" style="resize: none;" placeholder="请输入朗读文本" class="layui-textarea"></textarea>
		<el-button type="primary" size="small" class="add-btn" @click="add">确认添加</el-button>
	</div>
</template>

<script>
	export default {
		name: 'OpenWindow',
		methods: {
			add() {
				const { oralLanguageId, oralLanguageText } = this.OpenInfo;
				if(oralLanguageText == '') {
					this.$ShowToast('请输入朗读文本');
					return;
				}
				this.$Post(this.$Api.CHANGE_READ_INFO, {
					oralLanguageId, oralLanguageText
				}).then(res => {
					if(res.code == 0) {
						this.$ShowToast('修改成功');
						this.$emit('refresh');
						this.$emit('update:open', false);
					} else {
						this.$ShowToast(res.msg);
					}
				})
			}
		},
		computed: {},
		props: {
			OpenInfo: [Object],
			open: Boolean
		},
	}
</script>

<style lang="stylus" scoped>
	.OpenWindow-box >>> .el-form-item__content
		width: 205px;
	.OpenWindow-box >>> .el-date-editor.el-input,
	.OpenWindow-box >>> .el-date-editor.el-input__inner
		width: 205px !important;
	.OpenWindow-box
		width: 660px;
		height: 170px;
		background: #fff;
		padding: 15px;
	.add-btn
		margin-top: 10px;
		float: right;
</style>