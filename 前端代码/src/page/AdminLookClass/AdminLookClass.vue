<template>
	<div class="course-list-box">
		<div class="tool-row">
			<div class="layui-btn layui-btn-normal layui-btn-sm" @click="add">添加班级</div>
		</div>
		<layui-table :element.sync="table" v-bind="$TableConfig.GET_ALL_CLASS_TABLE" @tool="TableTool" />
		<axc-container-popup :opacity="0.4" :open.sync="open" title="添加教师和学生">
			<OpenWindow :open.sync="open" :classId="classId" :key="classId" />
		</axc-container-popup>
	</div>
</template>

<script>
	import OpenWindow from './OpenWindow.vue'
	export default {
		name: 'AdminLookClass',
		data() {
			return {
				table: null,
				open: false,
				classId: '',
			}
		},
		methods: {
			add() {
				this.$prompt('请输入班级编号', '提示', {
					confirmButtonText: '确定',
				}).then(({ value }) => {
					if(value === null) {
						this.$ShowToast('请输入班级编号');
						return;
					}
					this.$Post(this.$Api.ADD_CLASS, {
						classNumber: value
					}).then(res => {
						if(res.code == 0) {
							this.$ShowToast('添加成功');
							this.table.reloadTable()
						} else {
							this.$ShowToast(res.msg)
						}
					})
				}).catch(() => {})
			},
			TableTool({ event, data }) {
				if(event == 'edit') {
					this.$prompt('请输入班级编号', '提示', {
						confirmButtonText: '确定',
						inputValue: data.classNumber
					}).then(({ value }) => {
						if(value === null) {
							this.$ShowToast('请输入班级编号');
							return;
						}
						this.$Post(this.$Api.CHANGE_CLASS_INFO, {
							classId: data.classId,
							classNumber: value
						}).then(res => {
							if(res.code == 0) {
								this.$ShowToast('修改成功');
								this.table.reloadTable()
							} else {
								this.$ShowToast(res.msg)
							}
						})
					}).catch(() => {})
				} else if(event == 'add') {
					this.classId = data.classId;
					this.open = true;
				} else if(event == 'del') {
					this.$confirm('此操作将永久删除该班级, 是否继续?', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
					}).then(() => {
						this.$Post(this.$Api.DEL_CLASS, {
							id: data.classId
						}).then(res => {
							if(res.code == 0) {
								this.$ShowToast('删除成功');
								this.table.reloadTable()
							} else {
								this.$ShowToast(res.msg);
							}
						})
					}).catch(() => {});
				}
			}
		},
		components: { OpenWindow }
	}
</script>

<style lang="stylus" scoped>
	.course-list-box
		background: #fff;
		padding: 10px 17px;
		border-radius: 3px;
		overflow: hidden;
</style>