/**
 * [Mock模拟Get请求数据时url的正则处理	]
 * @param  {string} Api [请求Api的url]
 * @return {[type]}     [返回正则表达式]
 */
export const RegularApi = (Api) => {
	return new RegExp(Api);
}

/**
 * 处理Mock模拟的请求信息
 * @param  {object} option 请求信息
 * @return {object}        返回url，tpye，body（传递的数据）
 */
export const SplitOption = (option) => {
	let rs;
	if(option.type == "POST") {
		rs = option.body;
	} else {
		rs = option.url.split("?")[1];
	}
	if(!rs) {
		return option;
	}
	let arr = rs.split("&");
	let json = {};
	for(let i=0; i < arr.length; i++) {
		if(arr[i].indexOf("=")!=-1) {
			json[arr[i].split("=")[0]] = arr[i].split("=")[1];
		} else {
			json[arr[i]] = "undefined";
		}
	}
	option.url = option.url.split("?")[0];
	option.body = json;
	return option;
}