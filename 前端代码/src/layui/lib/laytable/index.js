import { GetComponentsID, ConvertTime, ReplaceState } from '../common'

export const ConvertCols = (cols) => {
	let newCol = {
		cols: [],
		toolbar: [],
		switch: [],
		checkbox: []
	}
	cols[0].map((item, key) => {
		if(item.toolbarList) {
			let toolbarID = GetComponentsID()
			newCol.toolbar = [...newCol.toolbar, {
				id: toolbarID,
				bar: item.toolbarList
			}]
			cols[0][key].toolbar = '#' + toolbarID;
		}
		// 判断是否需要渲染表单组件
		if(item.formbar) {
			let formbarID = GetComponentsID()
			let style = item.formbar.style ? 'switch' : 'checkbox';
			newCol[style] = [...newCol[style], {
				id: formbarID,
				field: item.field
			}];
			cols[0][key].templet = (d) => {
				let inputStr = '<input type="checkbox" ';
				if(item.formbar.style) {
					inputStr += 'lay-skin="switch" ';
					inputStr += 'lay-text="' + item.formbar.text + '" ';
				} else {
					inputStr += 'title="' + item.formbar.text + '" ';
				}
				if(item.formbar.checked) {
					inputStr += d[item.field] == item.formbar.checked ? 'checked' : '';
				}
				inputStr += ' lay-filter="' + formbarID + '">'
				return inputStr;
			}
		}
		// 判断是否需要函数处理
		if(item.FunDeal) {
			let field = item.field;
			let fun = item.FunDeal;
			if(typeof item.FunDeal == 'object') {
				field = item.FunDeal.field ? item.FunDeal.field : item.field;
				fun = item.FunDeal.Fun
			}
			switch(fun) {
				case 'ConvertTime':
					cols[0][key].templet = (d) => {
						let r = d[field] != null ? ConvertTime(d[field]) : '';
						return r;
					}
					break;
				case 'ReplaceState':
					cols[0][key].templet = (d) => {
						let r = d[field] != null ? ReplaceState(d[field], item.FunDeal.StateNum, item.FunDeal.StateText) : '';
						return r;
					}
					break;
			}
		}
	});
	newCol.cols = cols;
	return newCol;
}