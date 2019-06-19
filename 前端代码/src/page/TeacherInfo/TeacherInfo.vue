<template>
	<div class="TeacherInfo-box">
		<div class="info-item">
			<div class="title">工号：</div>
			{{info.teacherNumber}}
		</div>
		<div class="info-item">
			<div class="title">姓名：</div>
			{{info.teacherName}}
		</div>
		<div class="info-item">
			<div class="title">性别：</div>
			{{info.sex == 0 ? '男' : '女'}}
		</div>
		<div class="info-item">
			<div class="title">身份证：</div>
			{{info.teacherIdcard}}
		</div>
		<div class="info-item">
			<div class="title">年龄：</div>
			{{info.age}}
		</div>
	</div>
</template>

<script>
	export default {
		name: 'TeacherInfo',
		data() {
			return {
				info: { teacherId: '', teacherNumber: '', teacherName: '', sex: 0, teacherIdcard: '', age: 0 }
			}
		},
		methods: {
			GetInfo() {
				this.$Get(this.$Api.GET_TEACHER_INFO).then(res => {
					if(res.code == 0) {
						this.info = res.data
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