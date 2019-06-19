<template>
	<div class="OpenWindow-box">
		<el-select v-model="classId" placeholder="班级">
			<el-option
				v-for="item in classList"
				:key="item.classId"
				:label="item.classNumber"
				:value="item.classId">
			</el-option>
		</el-select>
		<el-button type="primary" size="small" class="add-btn" @click="add">确认布置</el-button>
	</div>
</template>

<script>
	import moment from 'moment/moment'
	export default {
		name: 'OpenWindow',
		data() {
			return {
				classId: '',
				classList: [],
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
			add() {
				const { oralLanguageId, classId } = this;
				if(oralLanguageId == '' || classId == '') {
					this.$ShowToast('请完善信息');
					return;
				}
				this.$Post(this.$Api.ADD_CLASS_LANGUAGE, {
					oralLanguageId, classId
				}).then(res => {
					if(res.code == 0) {
						this.$ShowToast('布置成功');
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
			oralLanguageId: [String, Number]
		},
		mounted() {
			this.GetAllClass()
		}
	}
</script>

<style lang="stylus" scoped>
	// .OpenWindow-box >>> .el-form-item__content
	// 	width: 205px;
	// .OpenWindow-box >>> .el-date-editor.el-input,
	// .OpenWindow-box >>> .el-date-editor.el-input__inner
	// 	width: 205px !important;
	.OpenWindow-box
		width: 320px;
		height: 80px;
		background: #fff;
		padding: 15px;
	.add-btn
		float: right;
		margin-top: 4px;
</style>