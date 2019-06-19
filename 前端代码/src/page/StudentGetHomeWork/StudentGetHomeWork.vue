<template>
	<div class="StudentGetHomeWork-box">
		<div class="head-row">
			<el-row>
				<el-col :span="5">序号</el-col>
				<el-col :span="6">作业名称</el-col>
				<el-col :span="4">类型</el-col>
				<el-col :span="4">数量</el-col>
				<el-col :span="5">操作</el-col>
			</el-row>
		</div>
		<div class="course-item" v-if="listenList.length == 0">你暂无听力作业</div>
		<div class="course-item" v-for="(item, index) in listenList">
			<el-row>
				<el-col :span="5">{{index + 1}}</el-col>
				<el-col :span="6">作业{{index + 1}}</el-col>
				<el-col :span="4">听力</el-col>
				<el-col :span="4">{{item.num}}</el-col>
				<el-col :span="5">
					<span class="tool-btn" @click="OpenListen(item, index)">打开</span>
				</el-col>
			</el-row>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'StudentGetHomeWork',
		data() {
			return {
				listenList: []
			}
		},
		methods: {
			GetData() {
				this.$Get(this.$Api.GET_STU_HOME_WORK_LIST).then(res => {
					if(res.code == 0) {
						this.listenList = res.data.listeningHomeWorkVo
					}
				})
			},
			OpenListen(item, index) {
				const { list } = this.$route.query;
				this.$router.push(`/main/StudentDoListen?list=${list}/作业${index + 1}&bcs=1&homeworkId=${item.homeworkId}`)
			}
		},
		computed: {},
		components: {},
		mounted() {
			this.GetData()
		}
	}
</script>

<style lang="stylus" scoped>
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