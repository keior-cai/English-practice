<template>
	<div class="AdminLookStudent-box">
		<layui-table :element.sync="table" :key="key_number" v-bind="$TableConfig.GET_ALL_STUDENT_TABLE" @tool="TableTool" />
		<axc-container-popup :opacity="0.4" :open.sync="open" title="修改学生信息">
			<OpenWindow :OpenInfo="OpenInfo" @refresh="key_number++" :open.sync="open" />
		</axc-container-popup>
	</div>
</template>

<script>
	import OpenWindow from './OpenWindow.vue'
	export default {
		name: 'AdminLookStudent',
		data() {
			return {
				table: null,
				OpenInfo: { studentId: '', studentNumber: '', studentName: '', sex: '', studentIdcard: '', studentPassword: ''},
				open: false,
				key_number: 0
			}
		},
		methods: {
			TableTool({ event, data }) {
				this.OpenInfo = JSON.parse(JSON.stringify(data));
				this.open = true;
			}
		},
		computed: {},
		components: {
			OpenWindow
		}
	}
</script>

<style lang="stylus" scoped>
	.AdminLookStudent-box
		background: #fff;
		border-radius: 3px;
		padding: 5px 15px;
</style>