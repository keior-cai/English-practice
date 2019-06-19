let defaultPeople = '';
if(localStorage.getItem("People")) {
	defaultPeople = JSON.parse(localStorage.getItem("People"))
}
export default {
	People: defaultPeople,
	ManagerAsideList: [{
		name: "人员管理",
		icon: "icon-yonghu",
		clist: [{
			name: "学生管理",
			clist: [{
				name: "添加学生",
				router: "AdminAddStudent",
			}, {
				name: "查看学生",
				router: "AdminLookStudent"
			}]
		}, {
			name: "教师管理",
			clist: [{
				name: "添加教师",
				router: "AdminAddTeacher",
			}, {
				name: "查看教师",
				router: "AdminLookTeacher"
			}]
		}]
	}, {
		name: "班级管理",
		icon: "icon-kechengguanli",
		router: "AdminLookClass"
	}, {
		name: "听力题库管理",
		icon: "icon-jiantingfenxi",
		clist: [{
			name: "添加听力题目",
			router: "AdminAddAural",
		}, {
			name: "查看听力题目",
			router: "AdminLookAural"
		}]
	}, {
		name: "口语题库管理",
		icon: "icon-ziyuan",
		router: "AdminLookRead"
	}],
	StudentAsideList: [{
		name: "查看个人信息",
		icon: "icon-yonghu",
		router: "StudentInfo"
	}, {
		name: "听力",
		icon: "icon-qiehuan",
		clist: [{
			name: "查看听力作业",
			router: "StudentGetHomeWork"
		}, {
			name: "查看历史听力训练",
			router: "StudentGetHistoryListen"
		}]
	}, {
		name: "口语",
		icon: "icon-jiantingfenxi",
		clist: [{
			name: "查看口语训练",
			router: "StudentGetHomeWorkRead"
		}, {
			name: "查看历史口语训练",
			router: "StudentGetHistoryRead"
		}]
	}],
	TeacherAsideList: [{
		name: "口语",
		icon: "icon-jiantingfenxi",
		clist: [{
			name: "添加口语题目",
			router: "TeacherAddRead"
		}, {
			name: "布置口语作业",
			router: "TeacherAssignRead"
		}, {
			name: '导出学生口语记录',
			router: "TeacherOralOut"
		}]
	}, {
		name: "班级管理",
		icon: "icon-kechengguanli",
		router: "AdminLookClass"
	}, {
		name: "听力",
		icon: "icon-qiehuan",
		clist: [{
			name: "布置听力作业",
			router: "TeacherAssignListen"
		}]
	}, {
		name: "我的",
		icon: "icon-yonghu",
		clist: [{
			name: "修改密码",
			router: "TeacherPassword"
		}, {
			name: "个人信息",
			router: "TeacherInfo"
		}]
	}, {
		name: "学生",
		icon: "icon-yonghu",
		clist: [{
			name: "查看学生作业",
			router: "TeacherLookHomeWork"
		}, {
			name: "查看班级学生",
			router: "TeacherLookStudent"
		}, {
			name: "添加学生",
			router: "AdminAddStudent"
		}, {
			name: "管理所有学生",
			router: "AdminLookStudent"
		}]
	}]
}