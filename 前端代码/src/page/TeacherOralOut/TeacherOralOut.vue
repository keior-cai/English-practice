<template>
	<div class="TeacherLookStudent-box">
		<div class="tool-row">
			<div class="title">选择:</div>
			<el-select v-model="classId" placeholder="请选择班级" clearable>
				<el-option
					v-for="item in classList"
					:key="item.classId"
					:label="item.classNumber"
					:value="item.classId">
				</el-option>
			</el-select>
			<el-button icon="el-icon-search" type="primary" @click="Out">导出</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'TeacherOralOut',
		data() {
			return {
				classId: '',
				classList: []
			}
		},
		methods: {
			GetAllClass() {
				this.$Get(this.$Api.GET_TEA_ALL_CLASS).then(res => {
					if(res.code == 0) {
						this.classList = res.data
					} else {
						this.$ShowToast(res.msg);
					}
				})
			},
			Out() {
				if(this.classId == '') {
					this.$ShowToast('请选择班级');
					return;
				}
				window.open(`${this.$Api.EXPORT_HISTORY}?classId=${this.classId}`)
			}
		},
		mounted() {
			this.GetAllClass()
		}
	}
</script>

<style lang="stylus" scoped>
	.el-button
		margin-bottom: 10px;
	.el-input,
	.el-select
		float: left;
		width: 170px;
		margin-right: 10px;
		margin-bottom: 10px;
	.tool-row
		margin-bottom: 15px;
		background: #fff;
		border-radius: 3px;
		overflow: hidden;
		padding: 10px 15px 0;
		.title
			float: left;
			height: 40px;
			line-height: 40px;
			margin-right: 20px;
	.table-box
		padding: 10px 15px;
		background: #fff;
		border-radius: 3px;
</style>