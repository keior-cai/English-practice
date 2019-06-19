<template>
	<div class="lay-form-item-box">
		<div class="item-label" :style="'width: ' + labelWidth + 'px'">{{label}}</div>
		<div class="item-input">
			<slot></slot>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'layformitem',
		data() {
			return {}
		},
		methods: {},
		computed: {},
		props: {
			label: {
				type: String,
				default: ''
			},
			labelWidth: {
				type: Number,
				default: 100
			}
		}
	}
</script>

<style lang="stylus" scoped>
	.lay-form-item-box
		min-height: 38px;
		display: flex;
		margin-bottom: 20px;
		.item-label
			float: left;
			text-align: right;
			height: 38px;
			line-height: 38px;
			box-sizing: border-box;
			padding: 0 15px;
		.item-input
			flex: 1;
</style>