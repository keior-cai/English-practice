import Vue from 'vue'
import Vuex from 'vuex'
import Common from '@/common/store'
import Menu from './Menu'

Vue.use(Vuex)

export default new Vuex.Store({
	modules: {
		Common,
		Menu
	}
})