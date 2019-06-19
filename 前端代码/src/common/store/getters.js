export default {
	AsideList(state) {
		if(state.People == 3) {
			return state.ManagerAsideList
		} else if (state.People == 1) {
			return state.StudentAsideList
		} else {
			return state.TeacherAsideList
		}
	}
}