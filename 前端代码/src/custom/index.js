import containerpopup from './container-popup'

const myPlug = {
	install: function(Vue){
		Vue.component('axc-container-popup',containerpopup);
	}
}

export default myPlug