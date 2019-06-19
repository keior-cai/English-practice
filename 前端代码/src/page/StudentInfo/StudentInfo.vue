<template>
	<div class="TeacherInfo-box">
		<div class="info-item">
			<div class="title">工号：</div>
			{{info.studentNumber}}
		</div>
		<div class="info-item">
			<div class="title">姓名：</div>
			{{info.studentName}}
		</div>
		<div class="info-item">
			<div class="title">性别：</div>
			{{info.sex == 0 ? '男' : '女'}}
		</div>
		<div class="info-item">
			<div class="title">身份证：</div>
			{{info.studentIdcard}}
		</div>
		<div class="info-item">
			<div class="title">添加时间：</div>
			{{info.createTime}}
		</div>
	</div>
</template>

<script>
	export default {
		name: 'StudentInfo',
		data() {
			return {
				info: { studentId: '', studentNumber: '', studentName: '', sex: 0, studentIdcard: '', createTime: 0 }
			}
		},
		methods: {
			GetInfo() {
				this.$Get(this.$Api.GET_STU_INFO).then(res => {
					if(res.code == 0) {
						this.info = res.data
					} else {
						this.$ShowToast(res.msg);
					}
				})
			}
		},
		computed: {},
		components: {},
		mounted() {
			this.GetInfo()
		}
	}
</script>

<style lang="stylus" scoped>
	.TeacherInfo-box
		padding: 15px;
		background: #fff;
		border-radius: 3px;
		.info-item
			height: 35px;
			line-height: 35px;
			overflow: hidden;
			.title
				float: left;
				text-align: right;
				width: 70px;
				margin-right: 10px;
</style>