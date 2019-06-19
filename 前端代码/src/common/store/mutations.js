export default {
	SaveProple(state, people) {
		localStorage.setItem("People", people)
		state.People = people
	}
}