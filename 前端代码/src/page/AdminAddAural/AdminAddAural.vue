<template>
	<div class="TeaAddHomeWork-box">
		<div class="work-item" v-for="(item, index) in form">
			<!-- <div class="del-btn" @click="DelList(index)">删除</div> -->
			<div class="item-title">
				<div class="title">题目{{index + 1}} : </div>
				<div class="item-input">
					<input type="text" disabled="" v-model="form[index].subjectTitle" class="layui-input" :placeholder="'请输入题目' + (index + 1)" />
					<div style="line-height: 40px;" :id="'file' + index">上传音频文件</div>
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
									<input type="text" v-model="form[index].optionA" class="layui-input" placeholder="请输入选项A" />
								</div>
							</div>
						</el-col>
						<el-col :span="6">
							<div class="option-item">
								<div class="option-number">B </div>
								<div class="option-input">
									<input type="text" v-model="form[index].optionB" class="layui-input" placeholder="请输入选项B" />
								</div>
							</div>
						</el-col>
						<el-col :span="6">
							<div class="option-item">
								<div class="option-number">C </div>
								<div class="option-input">
									<input type="text" v-model="form[index].optionC" class="layui-input" placeholder="请输入选项C" />
								</div>
							</div>
						</el-col>
						<el-col :span="6">
							<div class="option-item">
								<div class="option-number">D </div>
								<div class="option-input">
									<input type="text" v-model="form[index].optionD" class="layui-input" placeholder="请输入选项D" />
								</div>
							</div>
						</el-col>
					</el-row>
				</div>
			</div>
			<div class="answer-row">
				<div class="title">答案 : </div>
				<div class="answer-select">
					<layui-select :value.sync="form[index].answer" :list="options" placeholder="请选择问题答案" />
				</div>
			</div>
		</div>
		<div class="add-btn" @click="AddList">添加一题</div>
		<div class="add-btn" @click="Send" style="background: green;">上传作业</div>
	</div>
</template>

<script>
	export default {
		name: 'AdminAddAural',
		data() {
			return {
				form: [{
					subjectTitle: "",
					answer: "",
					optionA: "",
					optionB: "",
					optionC: "",
					optionD: ""
				}],
				options: [{ name: 'A', value: 'A' }, { name: 'B', value: 'B' }, { name: 'C', value: 'C' }, { name: 'D', value: 'D' }]
			}
		},
		methods: {
			AddList() {
				this.form = [...this.form, {
					subjectTitle: "",
					answer: "",
					optionA: "",
					optionB: "",
					optionC: "",
					optionD: ""
				}];
				setTimeout(() => {
					layui.use('upload', () => {
						let upload = layui.upload;
						var uploadInst = upload.render({
							elem: '#file' + (this.form.length - 1),
							url: this.$Api.UPLOAD_LISTEN_FILE,
							accept: 'file',
							field: 'file',
							headers: {
								token: this.$GetToken()
							},
							done: res => {
								if(res.code == 0) {
									this.form[this.form.length - 1].subjectTitle = res.data[0];
									this.$ShowToast('上传成功');
								} else {
									this.$ShowToast(res.msg);
								}
							}
						})
					})
				}, 500)
			},
			DelList(i) {
				if(this.form.length == 1) {
					this.$ShowToast('至少要有一条题目');
					return;
				}
				this.form = this.form.filter((item, index) => index != i);
			},
			Send() {
				const ok = this.form.filter(item => item.subjectTitle != '' && item.answer != '' && item.optionA != '' && item.optionB != '' && item.optionC != '' && item.optionD != '' )
				if(ok.length < this.form.length) {
					this.$ShowToast('请完善题目信息');
					return;
				}
				this.$Post(this.$Api.ADD_AURAL, {
					list: JSON.stringify(this.form)
				}).then(res => {
					if(res.code == 0) {
						this.$ShowToast('添加成功');
					} else {
						this.$ShowToast(res.msg);
					}
				})
			}
		},
		components: {},
		mounted() {
			setTimeout(() => {
				layui.use('upload', () => {
					let upload = layui.upload;
					var uploadInst = upload.render({
						elem: '#file0',
						url: this.$Api.UPLOAD_LISTEN_FILE,
						accept: 'file',
						field: 'file',
						headers: {
							token: this.$GetToken()
						},
						done: res => {
							if(res.code == 0) {
								this.form[0].subjectTitle = res.data[0];
								this.$ShowToast('上传成功');
							} else {
								this.$ShowToast(res.msg);
							}
						}
					})
				})
			}, 500)
		}
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
	.add-btn
		background: #2196f3;
		color: #fff;
		line-height: 40px;
		text-align: center;
		cursor: pointer;
		border-radius: 3px;
		margin-bottom: 10px;
		&:hover
			opacity: 0.8;
</style>