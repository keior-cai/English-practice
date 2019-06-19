/**
 * 图片路径提取宽度和高度
 * @param  {string} path 图片路径
 * @return {object}      { path: 图片路径, width: 图片宽度, height: 图片高度}
 */
export const LeaveImgWidthHeight = (path) => {
	let pathArray = path.split(',');
	let re = { path };
	const wh = {'w': 'width', 'h': 'height'};
	for (let i = pathArray.length - 1; i >= pathArray.length -2; i--) {
		re[wh[pathArray[i].split('_')[0]]] = pathArray[i].split('_')[1];
	}
	return re;
}
/**
 * 获取url参数的值
 * @param  {[type]} name [description]
 * @return {[type]}      [description]
 */
export const GetQueryString = name => {
	return decodeURIComponent((new RegExp('[?|&]'+name+'='+'([^&;]+?)(&|#|;|$)').exec(location.href)||[,""])[1].replace(/\+/g,'%20'))||null;
	// var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	// var r = window.location.search.substr(1).match(reg);
	// if (r != null) return unescape(r[2]);
	// return null;
}
/**
 * 生成请求附带数据
 * @param  {object} info 需要传入的参数
 * @return {resources}      请求资源
 */
export const BuildRequestResources = (info) => {
	let params = new URLSearchParams();
	for(let i in info) {
		if(typeof info[i] == 'object') {
			params.append(i,JSON.stringify(info[i]));
		} else {
			params.append(i,info[i]);
		}
	}
	return params;
}
/**
 * 获取token
 * @return {[type]} [description]
 */
export const GetToken = () => {
	return localStorage.getItem('token')
}
/**
 * 设置信息
 * @param  {[type]} info [description]
 * @return {[type]}      [description]
 */
export const SetInfo = info => {
	localStorage.setItem('info', JSON.stringify(info))
}
/**
 * 获取信息
 * @return {[type]} [description]
 */
export const GetInfo = () => {
	return JSON.parse(localStorage.getItem('info'))
}