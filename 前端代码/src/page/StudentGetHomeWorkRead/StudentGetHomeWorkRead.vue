<template>
	<div class="StudentGetHomeWorkRead-box">
		<div class="head-row">
			<el-row>
				<el-col :span="4">序号</el-col>
				<el-col :span="14">朗读文本</el-col>
				<el-col :span="6">操作</el-col>
			</el-row>
		</div>
		<div class="course-item" v-if="readList.length == 0">你暂无口语作业</div>
		<div class="course-item" v-for="(item, index) in readList" v-if="item != null && item.list != null">
			<el-row>
				<el-col :span="4">{{index + 1}}</el-col>
				<el-col :span="14">{{item.list.oralLanguageText}}</el-col>
				<el-col :span="6"><span :id="'upload' + item.homeworkId" style="cursor: pointer;">上传</span></el-col>
			</el-row>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'StudentGetHomeWorkRead',
		data() {
			return {
				homeworkId: '',
				readList: ''
			}
		},
		methods: {
			GetData() {
				this.$Get(this.$Api.GET_STU_HOME_WORK_LIST).then(res => {
					if(res.code == 0) {
						this.readList = res.data.oralLanguageIdVo;
						this.readList.map(item => {
							setTimeout(() => {
								layui.use('upload', () => {
									let upload = layui.upload;
									var uploadInst = upload.render({
										elem: '#upload' + item.homeworkId,
										url: this.$Api.UPLOAD_READ_FILE,
										accept: 'file',
										field: 'file',
										headers: {
											token: this.$GetToken()
										},
										data: {
											homeWorkId: item.homeworkId
										},
										done: res => {
											if(res.code == 0) {
												this.$ShowToast('上传成功');
												this.GetData()
											} else {
												this.$ShowToast(res.msg);
											}
										}
									})
								})
							}, 500)
						})
					}
				})
			},
		},
		computed: {},
		components: {},
		mounted() {
			this.GetData()
			// setTimeout(() => {
			// 	layui.use('upload', () => {
			// 		let upload = layui.upload;
			// 		var uploadInst = upload.render({
			// 			elem: '#uploadBtn',
			// 			url: this.$Api.UPLOAD_READ_FILE,
			// 			accept: 'file',
			// 			field: 'file',
			// 			headers: {
			// 				token: this.$GetToken()
			// 			},
			// 			data: {
			// 				homeWorkId: () => {
			// 					return this.homeworkId;
			// 				}
			// 			},
			// 			done: res => {
			// 				if(res.code == 0) {
			// 					this.$ShowToast('上传成功');
			// 					this.GetData()
			// 				} else {
			// 					this.$ShowToast(res.msg);
			// 				}
			// 			}
			// 		})
			// 	})
			// }, 500)
		}
	}
</script>

<style lang="stylus" scoped>
	.tool-row
		background: #fff;
		padding: 15px;
		overflow: hidden;
		border-radius: 3px;
		margin-bottom: 15px;
	.head-row
		height: 40px;
		line-height: 40px;
		text-align: center;
		background: #fff;
		margin-bottom: 15px;
		border-radius: 3px;
	.course-item
		text-align: center;
		background: #fff;
		margin-bottom: 15px;
		border-radius: 3px;
		padding: 10px 0;
		.tool-btn
			margin: 0 5px;
			cursor: pointer;
			&:hover
				color: red;
</style>