<template>
	<div class="TeaHomeWorkDetail-box">
		<div class="tool-row">
			题目总数：{{total}}
			总得分：{{scope}}
		</div>
		<div class="item" v-for="(item, index) in list">
			<div class="item-title" style="margin-bottom: 10px;">
				<div class="title">题目{{index + 1}} : <audio :src="item.subjectTitle" :id="'muse' + index" hidden=""></audio><span class="open-btn layui-btn layui-btn-sm" @click="Play(index)">点击播放</span></div>
			</div>
			<div class="item-options">
				<div class="title">选项 : </div>
				<div class="options-list">
					<el-row>
						<el-col :span="6">A. {{item.optionA}}</el-col>
						<el-col :span="6">B. {{item.optionB}}</el-col>
						<el-col :span="6">C. {{item.optionC}}</el-col>
						<el-col :span="6">D. {{item.optionD}}</el-col>
					</el-row>
				</div>
			</div>
			<div class="answer-row">答案 :  {{item.answer}}</div>
			<div class="answer-row">你选的是 :  {{item.userAnswer}}</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'TeaHomeWorkDetail',
		data() {
			return {
				list: [],
				total: 0,
				scope: 0
			}
		},
		methods: {
			GetData() {
				this.$Get(this.$Api.GET_HISTORY_LISTEN).then(res => {
					if(res.code == 0) {
						this.list = res.data.listeningSubjectHistoryVos;
						this.scope = res.data.scope;
						this.total = res.data.total;
					}
				})
			},
			Play(index) {
				this.list.map((item, key) => {
					var audio = document.getElementById('muse' + key);
					audio.pause();
				});
				var audio = document.getElementById('muse' + index);
				if(audio !== null) {
					audio.currentTime = 0;
					audio.play();
				}
			},
		},
		mounted() {
			this.GetData()
		}
	}
</script>

<style lang="stylus" scoped>
	.tool-row
		background: #fff;
		border-radius: 3px;
		margin-bottom: 15px;
		padding: 15px;
	.item
		background: #fff;
		padding: 15px;
		margin-bottom: 15px;
		border-radius: 3px;
		position: relative;
		.change
			position: absolute;
			top: 0;
			right: 40px;
			width: 40px;
			height: 35px;
			line-height: 35px;
			cursor: pointer;
		.del
			position: absolute;
			top: 0;
			right: 0;
			width: 40px;
			height: 35px;
			line-height: 35px;
			cursor: pointer;
		.item-options
			display: flex;
			overflow: hidden;
			height: 30px;
			line-height: 30px;
			.title
				float: left;
			.options-list
				flex: 1;
				margin-left: 10px;
</style>