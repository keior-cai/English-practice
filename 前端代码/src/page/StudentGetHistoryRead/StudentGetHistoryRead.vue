<template>
	<div class="StudentGetHistoryRead-box">
		<layui-table :element.sync="table" v-bind="$TableConfig.GET_HISTORY_READ_TABLE" @tool="TableTool" />
		<audio :src="url" id="muse" hidden=""></audio>
	</div>
</template>

<script>
	export default {
		name: 'StudentGetHistoryRead',
		data() {
			return {
				table: null,
				url: ''
			}
		},
		methods: {
			TableTool({ event, data }) {
				if(event == 'play') {
					this.url = data.homeWorkPath;
					setTimeout(() => {
						var audio = document.getElementById('muse');
						audio.pause();
						audio.currentTime = 0;
						audio.play();
					}, 100)
				}
			}
		}
	}
</script>

<style lang="stylus" scoped>
	.StudentGetHistoryRead-box
		background: #fff;
		border-radius: 3px;
		padding: 15px;
</style>