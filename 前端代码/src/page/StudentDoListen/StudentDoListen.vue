<template>
	<div class="StuExamDetail-box">
		<div :class="'exam-item ' + itemState(item.answered, item.answer)" v-for="(item, index) in list" :key="item.id">
			<div class="title">题目{{index + 1}}：<audio :src="item.subjectTitle" :id="'muse' + index" hidden=""></audio><span class="open-btn layui-btn layui-btn-sm" @click="Play(index)">点击播放</span></div>
			<div class="options">
				<el-row>
					<el-col :span="6">
						<mu-radio value="A" v-model="list[index].answered" color="#009688" :label="'A.' + item.optionA"></mu-radio>
					</el-col>
					<el-col :span="6">
						<mu-radio value="B" v-model="list[index].answered" color="#009688" :label="'B.' + item.optionB"></mu-radio>
					</el-col>
					<el-col :span="6">
						<mu-radio value="C" v-model="list[index].answered" color="#009688" :label="'C.' + item.optionC"></mu-radio>
					</el-col>
					<el-col :span="6">
						<mu-radio value="D" v-model="list[index].answered" color="#009688" :label="'D.' + item.optionD"></mu-radio>
					</el-col>
				</el-row>
			</div>
			<div class="answer" v-if="check">正确答案：{{item.answer}}</div>
		</div>
		<div class="check-btn" @click="Check">检查</div>
	</div>
</template>

<script>
	export default {
		name: 'StudentDoListen',
		data() {
			return {
				list: [],
				check: false
			}
		},
		methods: {
			GetData() {
				this.$Get(this.$Api.GET_STU_LISTEN_LIST).then(res => {
					if(res.code == 0) {
						res.data.map(item => {
							let { subjectId, subjectTitle, optionA, optionB, optionC, optionD, answer } = item
							this.list = [...this.list, { subjectId, subjectTitle, optionA, optionB, optionC, optionD, answer, answered: '' }]
						});
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
			Check() {
				let ok = this.list.filter(item => item.answered == '');
				if(ok.length > 0) {
					this.$ShowToast('请完成作业');
					return;
				}
				let list = JSON.parse(JSON.stringify(this.list));
				let send = list.map(item => {
					return {
						listeningId: item.subjectId,
						answer: item.answer,
						userAnswer: item.answered,
						isTure: item.answer == item.answered ? 0 : 1,
						classHomeWorkId: this.$route.query.homeworkId
					}
				});
				this.$Post(this.$Api.SEND_LISTEN_WORK, {
					jsonData: JSON.stringify(send)
				}).then(res => {
					if(res.code == 0) {
						this.$ShowToast('提交成功');
						this.check = true;
					} else {
						this.$ShowToast(res.msg);
					}
				})
			}
		},
		computed: {
			itemState() {
				return (answered, answer) => {
					if(!this.check) {
						return ''
					} else if(answered == answer){
						return ''
					} else {
						return 'false'
					}
				}
			}
		},
		components: {},
		mounted() {
			this.GetData()
		}
	}
</script>

<style lang="stylus" scoped>
	.exam-item >>> .mu-radio-icon
		transform: scale(0.8, 0.8)
	.mu-radio
		margin-right: 15px;
	.exam-item
		margin-bottom: 15px;
		background: #fff;
		border-radius: 3px;
		padding: 15px 20px;
		box-sizing: border-box;
		&.false
			border: 1px solid red;
		.title
			margin-bottom: 10px;
	.check-btn
		background: #2196f3;
		color: #fff;
		height: 40px;
		text-align: center;
		line-height: 40px;
		border-radius: 3px;
		margin-bottom: 15px;
		cursor: pointer;
		&:hover
			opacity: 0.8;
</style>