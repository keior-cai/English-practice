<template>
	<div class="layui-form" :lay-filter="formID">
		<select :lay-filter="selectID" v-html="optionList">
			<!-- <option value="">{{placeholder}}</option>
			<option v-for="item in list" :value="item.value">{{item.name}}</option> -->
		</select>
	</div>
</template>

<script>
	import { GetComponentsID } from '../lib/common'
	export default {
		name: 'layselect',
		data() {
			return {
				form: null
			}
		},
		methods: {},
		computed: {
			formID() {
				return GetComponentsID();
			},
			selectID() {
				return GetComponentsID()
			},
			optionList() {
				let list = `<option value="">${this.placeholder}</option>`;
				this.list.map(item => {
					list += `<option ${item.value === this.value ? 'selected' : ''} value="${item.value}">${item.name}</option>`
				})
				return list;
			}
		},
		components: {},
		props: {
			value: [String, Number],
			list: Array,
			placeholder: [String, Number]
		},
		mounted() {
			const { formID, selectID } = this;
			layui.use(['form'], () => {
				let form = layui.form;
				this.form = form;
				form.render('select', formID);
				form.on('select(' + selectID + ')', (data) => {
					this.$emit('select', data);
					this.$emit('update:value', data.value);
				});
			})
		},
		watch: {
			list() {
				setTimeout(() => {
					this.form.render('select', this.formID);
				}, 300);
			},
			value() {
				setTimeout(() => {
					this.form.render('select', this.formID);
				}, 300);
			}
		}
	}
</script>

<style lang="stylus" scoped></style>