<template>
	<div class="OpenWindow-box">
		<el-select v-model="teacherId" placeholder="教师">
			<el-option
				v-for="item in teacherList"
				:key="item.teacherId"
				:label="item.teacherName"
				:value="item.teacherId">
			</el-option>
		</el-select>
		<el-select v-model="studentId" placeholder="学生">
			<el-option
				v-for="item in studentList"
				:key="item.studentId"
				:label="item.studentName"
				:value="item.studentId">
			</el-option>
		</el-select>
		<el-button type="primary" size="small" class="add-btn" @click="add">确认添加</el-button>
	</div>
</template>

<script>
	import moment from 'moment/moment'
	export default {
		name: 'OpenWindow',
		data() {
			return {
				teacherId: '',
				teacherList: [],
				studentId: '',
				studentList: [],
			}
		},
		methods: {
			GetAllTeacher() {
				this.$Get(this.$Api.GET_ALL_TEACHER).then(res => {
					if(res.code == 0) {
						this.teacherList = res.data
					} else {
						this.$ShowToast(res.msg);
					}
				})
			},
			GetAllStudent() {
				this.$Get(this.$Api.GET_ALL_STUDENT).then(res => {
					if(res.code == 0) {
						this.studentList = res.data
					} else {
						this.$ShowToast(res.msg);
					}
				})
			},
			add() {
				const { classId, teacherId, studentId } = this;
				if(teacherId === '' || studentId === '') {
					this.$ShowToast('请完善信息');
					return;
				}
				this.$Post(this.$Api.CLASS_TEACHER_STUDENT, {
					classId, teacherId, studentId
				}).then(res => {
					if(res.code == 0) {
						this.$ShowToast('添加成功');
						this.$emit('update:open', false);
					} else {
						this.$ShowToast(res.msg);
					}
				})
			}
		},
		computed: {},
		props: {
			open: Boolean,
			classId: [String, Number]
		},
		mounted() {
			this.GetAllTeacher();
			this.GetAllStudent();
		}
	}
</script>

<style lang="stylus" scoped>
	// .OpenWindow-box >>> .el-form-item__content
	// 	width: 205px;
	// .OpenWindow-box >>> .el-date-editor.el-input,
	// .OpenWindow-box >>> .el-date-editor.el-input__inner
	// 	width: 205px !important;
	.el-select
		float: left;
		margin-right: 10px;
	.OpenWindow-box
		width: 540px;
		height: 80px;
		background: #fff;
		padding: 15px;
		overflow: hidden;
	.add-btn
		float: right;
		margin-top: 4px;
</style>