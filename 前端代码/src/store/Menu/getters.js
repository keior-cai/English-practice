export default {
	CourseTypeMenu: state => {
		let list = [];
		state.CourseTypeMenu.map(item => { list = [...list, { name: item.typeName, value: item.typeName }] });
		return list;
	},
}