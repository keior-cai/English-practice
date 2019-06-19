<template>
	<span>
		<slot></slot>
	</span>
</template>

<script>
	import { GetComponentsID } from '../lib/common'
	export default {
		name: 'laydate',
		data() {
			return {}
		},
		methods: {},
		computed: {
			inputID() {
				return GetComponentsID()
			}
		},
		props: {
			inputid: String,
			value: String, // 初始值
			range: {
				type: Boolean,
				default: false
			}, // 范围选择
			type: {
				type: String,
				default: 'date'
			}, // 控件类型
			format: {
				type: String,
				default: 'yyyy-MM-dd'
			}, // 日期格式
			show: {
				type: Boolean,
				default: false
			}, // 默认开启
			zIndex: {
				type: Number,
				default: 66666666
			}, // z-index的值
			showBottom: {
				type: Boolean,
				default: true
			}, // 是否显示底部控件
			btns: {
				type: Array,
				default: () => (['clear', 'now', 'confirm'])
			}, // 右下角显示的按钮，会按照数组顺序排列，内置可识别的值有：clear、now、confirm
			lang: {
				type: String,
				default: 'cn'
			}, // 语言
			theme: {
				type: String,
				default: 'default'
			}, // 主题
			mark: {
				type: Object,
				default: () => ({})
			}, // 标注重要日子
		},
		mounted() {
			const { inputID, value, range, type, format, show, zIndex, showBottom, btns, lang, theme, mark } = this
			this.$emit('update:inputid', inputID)
			layui.use(['laydate'], () => {
				let { laydate } = layui;

				setTimeout(() => {
					laydate.render({
						elem: '#' + inputID,
						range,
						value,
						type,
						format,
						show,
						zIndex,
						showBottom,
						btns,
						lang,
						theme,
						mark,
						ready: (date) => {
							this.$emit('ready', { date })
						},
						change: (value, data, endDate) => {
							this.$emit('change', { value, data, endDate })
						},
						done: (value, data, endDate) => {
							this.$emit('update:value', value)
							this.$emit('done', { value, data, endDate })
						}
					});
				}, 100);
			})
		}
	}
</script>

<style lang="stylus" scoped></style>