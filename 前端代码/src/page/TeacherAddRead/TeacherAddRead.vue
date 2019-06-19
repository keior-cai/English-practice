<template>
	<div class="TeacherAddRead-box">
		<textarea name="desc" v-model="oralLanguageText" style="resize: none;" placeholder="请输入口语文本" class="layui-textarea"></textarea>
		<el-button type="primary" size="small" class="add-btn" @click="add">确认添加</el-button>
	</div>
</template>

<script>
	export default {
		name: 'TeacherAddRead',
		data() {
			return {
				oralLanguageText: ''
			}
		},
		methods: {
			add() {
				if(this.oralLanguageText == '') {
					this.$ShowToast('请输入文本信息'); return;
				}
				this.$Post(this.$Api.ADD_TEACHER_READ, {
					oralLanguageText: this.oralLanguageText
				}).then(res => {
					if(res.code == 0) {
						this.$ShowToast('添加成功');
					} else {
						this.$ShowToast(res.msg);
					}
				})
			}
		},
		computed: {},
		components: {}
	}
</script>

<style lang="stylus" scoped>
	.TeacherAddRead-box
		padding: 15px;
		background: #fff;
		border-radius: 3px;
		overflow: hidden;
		.add-btn
			margin-top: 10px;
			float: right;
</style>