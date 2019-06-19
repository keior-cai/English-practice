<template>
	<div>
		<!-- tableDom元素 -->
		<table class="layui-hide" :id="tableID" :lay-filter="tableID"></table>
		<!-- 头部工具栏元素 -->
		<div class="layui-btn-container layui-hide" :id="topToolBarID">
			<button
				v-for="(item, key) in topToolBar"
				:key="key"
				class="layui-btn layui-btn-sm"
				:lay-event="item.event">{{item.title}}</button>
		</div>
		<!-- 行工具栏元素 -->
		<div
		class="layui-hide"
		v-for="(item, key) in colsTool.toolbar"
		:key="item.id"
		:id="item.id">
			<a
			v-for="(citem, ckey) in item.bar"
			:key="key + ckey"
			:class="'layui-btn layui-btn-xs ' + citem.style"
			:lay-event="citem.event">{{citem.title}}</a>
		</div>
	</div>
</template>

<script>
	import { GetComponentsID } from '../lib/common'
	import { ConvertCols } from '../lib/laytable'
	import { GetToken } from'@/utils/DealFun'
	export default {
		name: 'laytable',
		data() {
			return {
				tabledata: []
			}
		},
		methods: {},
		computed: {
			tableID() {
				return GetComponentsID()
			},
			topToolBarID() {
				return GetComponentsID()
			},
			colsTool() {
				// let cols = JSON.parse(JSON.stringify(this.cols))
				let newCol = ConvertCols(this.cols)
				return newCol;
			}
		},
		props: {
			url: {
				type: String,
				required: true
			},
			method: {
				type: String,
				default: 'get'
			},
			contentType: {
				type: String,
				default: 'application/x-www-form-urlencoded'
			},
			cols: {
				type: Array,
				required: true
			},
			defaultToolbar: {
				type: Array,
				default: () => (['filter', 'print', 'exports'])
			},
			topToolBar: {
				type: [Array, Boolean],
				default: () => ([])
			},
			height: {
				type: String,
				default: ""
			},
			cellMinWidth: {
				type: Number,
				default: 60
			},
			totalRow: {
				type: Boolean,
				default: false
			},
			page: {
				type: [Boolean, Object],
				default: false
			},
			limit: {
				type: Number,
				default: 10
			},
			limits: {
				type: Array,
				default: () => ([10,20,30,40,50,60,70,80,90])
			},
			loading: {
				type: Boolean,
				default: true
			},
			title: {
				type: String,
				default: ""
			},
			text: {
				type: Object,
				default: () => ({
					none: '无数据'
				})
			},
			// initSort: {
			// 	type: Object,
			// 	default: () => ({ field: "id", type: 'desc'})
			// }, // 初始排序，此处有坑
			skin: {
				type: String,
				default: ""
			},
			even: {
				type: Boolean,
				default: false
			},
			size: {
				type: String,
				default: ""
			},
			where: {
				type: [Object, Array],
				default: () => ({})
			},
			headers: {
				type: Object,
				default: () => ({
					token: GetToken()
				})
			}
		},
		mounted() {
			const { tableID, topToolBarID, topToolBar, url, method, contentType, colsTool, defaultToolbar, height, cellMinWidth, totalRow, page, limit, limits, loading, title, text, initSort, skin, even, size, where, headers } = this;
			layui.use(['table', 'form'], () => {
				let { table, form } = layui;

				this.$emit('update:element', {
					reloadTable: (wheres = {}) => {
						table.reload('#' + tableID, { url, where: wheres })
					},
					getChooseCols:() => {
						let checkStatus = table.checkStatus('#' + tableID);
						return checkStatus;
					}
				});


				let ins = table.render({
					elem: '#' + tableID,
					url,
					method,
					contentType,
					where,
					headers,
					parseData: res => {
						return {
							"code": res.code,
							"msg": res.msg,
							"count": res.count,
							"data": res.data
						}
					},
					request: {
						pageName: 'page',
						limitName: 'limit'
					},
					response: {
						statusName: 'code',
						statusCode: 0,
						msgName: 'msg',
						countName: 'count',
						dataName: 'data'
					},
					defaultToolbar,
					toolbar: topToolBar.length == 0 ? false : '#' + topToolBarID,
					id: '#' + tableID,
					cols: colsTool.cols,
					height,
					cellMinWidth,
					totalRow,
					page,
					limit,
					limits,
					loading,
					title,
					text,
					initSort,
					skin,
					even,
					size,
					done: (res, curr, count) => {
						this.tabledata = res.data
						this.$emit('done', { res, curr, count })
					},
				});

				table.on('toolbar(' + tableID + ')', (obj) => {
					this.$emit('toolbarEvent', obj)
				});

				table.on('tool(' + tableID + ')', (obj) => {
					this.$emit('tool', obj)
				});

				table.on('checkbox(' + tableID + ')', (obj) => {
					this.$emit('checkbox', obj)
				});

				table.on('edit(' + tableID + ')', (obj) => {
					this.$emit('edit', obj)
				});

				table.on('row(' + tableID + ')', (obj) => {
					this.$emit('row', obj) // 原有双击事件
				});

				table.on('sort(' + tableID + ')', (obj) => {
					this.$emit('sort', obj)
				});

				colsTool.switch.map(item => {
					let { id, field } = item
					form.on('switch(' + id + ')', (obj) => {
						const colindex = obj.othis.parents('tr')[0].dataset.index;
						let data = this.tabledata[colindex]; // 坑，不是当前行的数据
						this.$emit('switchTool', { ...obj, data, field })
					});
				});

				colsTool.checkbox.map(item => {
					let { id, field } = item
					form.on('checkbox(' + id + ')', (obj) => {
						const colindex = obj.othis.parents('tr')[0].dataset.index;
						let data = this.tabledata[colindex]; // 坑，不是当前行的数据
						this.$emit('checkboxTool', { ...obj, data, field })
					});
				});
			})
		}
	}
</script>

<style lang="stylus" scoped></style>