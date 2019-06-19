<template>
	<div class="OpenWindow-box">
		<div class="work-item">
			<div class="item-title">
				<div class="title">题目 : </div>
				<div class="item-input">
					<input type="text" v-model="OpenInfo.subjectTitle" class="layui-input" placeholder="请输入题目" />
				</div>
			</div>
			<div class="item-option">
				<div class="title">选项 : </div>
				<div class="option-list">
					<el-row :gutter="10">
						<el-col :span="6">
							<div class="option-item">
								<div class="option-number">A </div>
								<div class="option-input">
									<input type="text" v-model="OpenInfo.optionA" class="layui-input" placeholder="请输入选项A" />
								</div>
							</div>
						</el-col>
						<el-col :span="6">
							<div class="option-item">
								<div class="option-number">B </div>
								<div class="option-input">
									<input type="text" v-model="OpenInfo.optionB" class="layui-input" placeholder="请输入选项B" />
								</div>
							</div>
						</el-col>
						<el-col :span="6">
							<div class="option-item">
								<div class="option-number">C </div>
								<div class="option-input">
									<input type="text" v-model="OpenInfo.optionC" class="layui-input" placeholder="请输入选项C" />
								</div>
							</div>
						</el-col>
						<el-col :span="6">
							<div class="option-item">
								<div class="option-number">D </div>
								<div class="option-input">
									<input type="text" v-model="OpenInfo.optionD" class="layui-input" placeholder="请输入选项D" />
								</div>
							</div>
						</el-col>
					</el-row>
				</div>
			</div>
			<div class="answer-row">
				<div class="title">答案 : </div>
				<div class="answer-select">
					<layui-select :value.sync="OpenInfo.answer" :list="options" placeholder="请选择问题答案" />
				</div>
			</div>
		</div>
		<el-button type="primary" size="small" class="add-btn" @click="add">确认修改</el-button>
	</div>
</template>

<script>
	import moment from 'moment/moment'
	export default {
		name: 'OpenWindow',
		data() {
			return {
				options: [{ name: 'A', value: 'A' }, { name: 'B', value: 'B' }, { name: 'C', value: 'C' }, { name: 'D', value: 'D' }]
			}
		},
		methods: {
			add() {
				const { subjectId, subjectTitle, optionA, optionB, optionC, optionD, answer } = this.OpenInfo;
				if(subjectTitle == '' || optionA == '' || optionB == '' || optionC == '' || optionD == '' || answer == '') {
					this.$ShowToast('请完善信息');
					return;
				}
				this.$Post(this.$Api.CHANGE_AURAL_INFO, {
					subjectId, subjectTitle, optionA, optionB, optionC, optionD, answer
				}).then(res => {
					if(res.code == 0) {
						this.$ShowToast('添加成功');
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
		watch: {
			open() {
				this.optionCed = this.OpenInfo.optionC
			}
		},
	}
</script>

<style lang="stylus" scoped>
	.work-item
		background: #fff;
		border-radius: 3px;
		padding: 10px 20px 15px;
		margin-bottom: 15px;
		position: relative;
		.del-btn
			position: absolute;
			top: 0;
			right: 0;
			padding: 7px 13px;
			cursor: pointer;
		.item-title
			overflow: hidden;
			.title
				height: 40px;
				line-height: 40px;
				float: left;
			.item-input
				float: left;
				width: 350px;
				margin-left: 15px;
				.layui-input
					border: none;
					border-bottom: 1px solid #ccc;
		.item-option
			overflow: hidden;
			display: flex;
			height: 40px;
			margin-top: 10px;
			.title
				height: 40px;
				line-height: 40px;
				float: left;
			.option-list
				margin-left: 15px;
				flex: 1;
				.option-item
					height: 40px;
					overflow: hidden;
					display: flex;
					.option-number
						float: left;
						height: 40px;
						line-height: 40px;
					.option-input
						flex: 1;
						margin-left: 10px;
						.layui-input
							border: none;
							border-bottom: 1px solid #ccc;
		.answer-row
			height: 40px;
			margin-top: 10px;
			.title
				height: 40px;
				line-height: 40px;
				float: left;
			.answer-select
				margin-left: 15px;
				float: left;
				width: 150px;
	.OpenWindow-box >>> .el-form-item__content
		width: 205px;
	.OpenWindow-box >>> .el-date-editor.el-input,
	.OpenWindow-box >>> .el-date-editor.el-input__inner
		width: 205px !important;
	.OpenWindow-box
		width: 660px;
		height: 220px;
		background: #fff;
	.add-btn
		margin-right: 35px;
		margin-top: -10px;
		float: right;
</style>