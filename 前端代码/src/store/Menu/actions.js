import * as Api from '@/utils/ApiInterface'
import { Get, Post } from '@/utils/Request';

export default {
	RefreshMenu({ commit }, type) {
		// const types = [
		// 	{ name: 'CourseTypeMenu', api: Api.COURSE_TYPE_MENU },
		// ];
		// const t = types.filter(item => type == item.name);
		// Get(t[0].api).then(res => {
		// 	if(res.data) {
		// 		commit('SaveMenu', { type, list: res.data.list })
		// 	}
		// });
	}
}