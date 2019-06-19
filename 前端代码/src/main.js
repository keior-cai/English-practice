import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/dataShare'
import './assets/styles/reset.css'
// import './assets/styles/iconfont/iconfont.css'
import './element-ui'
import './muse-ui'
import './mock'

import layui from './layui'
Vue.use(layui)

import custom from './custom'
Vue.use(custom)

// 全局配置过滤器
import moment from 'moment/moment'
Vue.filter('moment', value => { // 时间格式转换
	return moment(value).format('YYYY-MM-DD HH:mm:ss');
});

// layui表格配置
import * as TableParameter from './utils/TableParameter.js';
Vue.prototype.$TableConfig = TableParameter;

import { GetReturnCode } from './utils/ReturnCode'
Vue.prototype.$getCode = GetReturnCode;

import * as Api from './utils/ApiInterface.js';
Vue.prototype.$Api = Api;

import { Get, Post } from './utils/Request.js';
Vue.prototype.$Get = Get;
Vue.prototype.$Post = Post;

import { GetToken, SetInfo, GetInfo, GetQueryString } from './utils/DealFun.js';
Vue.prototype.$GetToken = GetToken;
Vue.prototype.$SetInfo = SetInfo;
Vue.prototype.$GetInfo = GetInfo;
Vue.prototype.$GetQueryString = GetQueryString;

import * as Interactive from './utils/Interactive.js'
Vue.prototype.$ShowToast = Interactive.ShowToast;

import Bus from './utils/Bus.js';
Vue.prototype.$Bus = Bus;

import * as EmitType from './utils/EmitType.js';
Vue.prototype.$EmitType = EmitType;

// 下来菜单刷新封装
import { createNamespacedHelpers } from 'vuex';
const { mapActions: MenuActions } = createNamespacedHelpers('Menu');
Vue.prototype.$RefreshMenu = MenuActions(['RefreshMenu']).RefreshMenu;

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
