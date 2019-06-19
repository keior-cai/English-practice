<template>
	<div class="container-popup-box" :style="BoxStyle" v-show="open">
		<div class="bgClose" @click="close"></div>
		<mu-scale-transition>
			<div v-show="show" class="solt-container">
				<div class="container-title" v-if="title != ''">{{title}}</div>
				<slot></slot>
			</div>
		</mu-scale-transition>
	</div>
</template>

<script>
	export default {
		name: 'container-popup',
		data() {
			return {
				show: false
			}
		},
		methods: {
			close() {
				this.show = false;
				setTimeout(() => {
					this.$emit('update:open', false)
				}, 300)
			}
		},
		computed: {
			BoxStyle() {
				const { top, bottom, opacity } = this;
				return {
					top: top + 'px',
					bottom: bottom + 'px',
					background: 'rgba(0, 0, 0, ' + opacity + ')'
				}
			}
		},
		props: {
			top: {
				type: Number,
				default: 0
			},
			bottom: {
				type: Number,
				default: 0
			},
			opacity: {
				type: Number,
				default: 0.7
			},
			title: {
				type: String,
				default: ''
			},
			open: Boolean
		},
		watch: {
			open() {
				if(this.open) {
					this.show = true
				}
			}
		}
	}
</script>

<style lang="stylus" scoped>
	.container-popup-box
		position: fixed;
		left: 0;
		right: 0;
		top: 0;
		bottom: 0;
		z-index: 1000;
		.bgClose
			position: absolute;
			top: 0;
			left: 0;
			right: 0;
			bottom: 0;
			cursor: pointer;
		.solt-container
			position: absolute;
			top: 50%;
			left: 50%;
			box-shadow: 1px 1px 50px rgba(0,0,0,.3);
			transform: translate(-50%,-50%);
			.container-title
				height: 42px;
				line-height: 42px;
				padding: 0 80px 0 20px;
				background: #F8F8F8;
				border-bottom: 1px solid #e0e0e0;
</style>