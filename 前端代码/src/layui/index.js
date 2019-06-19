import laydate from './laydate'
import laytable from './laytable'
import layselect from './layselect'
// import layformitem from './layformitem'
import layauthtree from './layauthtree'
import { ConvertTime, ReplaceState } from './lib/common'

const variable = {
	ConvertTime,
	ReplaceState
}

const myPlug = {
	install: function(Vue){
		Vue.component('layui-date',laydate);
		Vue.component('layui-table',laytable);
		Vue.component('layui-select',layselect);
		// Vue.component('layui-form-item',layformitem);
		Vue.component('layui-authtree',layauthtree);

		Vue.prototype.$layvue = variable;
	}
}

export default myPlug