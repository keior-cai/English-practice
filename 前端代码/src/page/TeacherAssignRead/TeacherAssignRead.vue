<template>
	<div class="TeacherAssignRead-box">
		<layui-table :element.sync="table" v-bind="$TableConfig.GET_TEACHER_ALL_ORAL_TABLE" @tool="TableTool" />
		<axc-container-popup :opacity="0.4" :open.sync="open" title="选择班级">
			<OpenWindow :open.sync="open" :oralLanguageId="oralLanguageId" />
		</axc-container-popup>
	</div>
</template>

<script>
	import OpenWindow from './OpenWindow.vue'
	export default {
		name: 'TeacherAssignRead',
		data() {
			return {
				table: null,
				open: false,
				oralLanguageId: ''
			}
		},
		methods: {
			TableTool({ event, data }) {
				this.oralLanguageId = data.oralLanguageId,
				this.open = true;
			}
		},
		computed: {},
		components: { OpenWindow }
	}
</script>

<style lang="stylus" scoped>
	.TeacherAssignRead-box
		padding: 10px 15px;
		background: #fff;
		overflow: hidden;
		border-radius: 3px;
</style>