import moment from 'moment/moment'
/**
 * 生成ID
 * @return {string} id
 */
export const GetComponentsID = () => {
	const str = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
	let id = '';
	for (let i = 0; i < 8; i++) {
		id += str.charAt(Math.floor(Math.random() * str.length));
	}
	return id;
}
/**
 * 转换时间格式
 * @param  {string} time 传入的时间
 * @return {string}      输出正规时间
 */
export const ConvertTime = (time) => {
	return moment(time).format('YYYY-MM-DD HH:mm:ss');;
}
/**
 * 状态码转状态文字
 * @param  {string} now 当前状态
 * @param  {string} num 状态码
 * @param  {string} txt 状态文字
 * @return {string}     [description]
 */
export const ReplaceState = (now, num, txt) => {
	const nums = num.split('|');
	const txts = txt.split('|');
	const M = 99999;
	let i = M;
	nums.map((item, index) => {
		if(item == now) {
			i = index;
		}
	});
	let t = i == M ? 'null' : i;
	return txts[t];
}