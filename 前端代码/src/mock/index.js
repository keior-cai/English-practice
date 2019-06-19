const Mock = require('mockjs')
import * as Api from '@/utils/ApiInterface'
import { GetQueryString } from '@/utils/DealFun'
import * as MockHandle from './MockHandle'
const M = '999999999';

let AllSwitch = false;
if(GetQueryString("debug") == 2) {
	AllSwitch = true;
}

const Switch = (s) => {
	if(s && AllSwitch) {
		return "";
	} else {
		return M;
	}
}

Mock.setup({
	timeout: 200
});

// 学生登录
Mock.mock(MockHandle.RegularApi(Api.DO_LOGIN_STUDENT + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"shfajkfhafaklghklfjklfjlksahdasfhuaifhaljkhakj"}
});

// 教师登录
Mock.mock(MockHandle.RegularApi(Api.DO_LOGIN_TEACHER + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"shfajkfhafaklghklfjklfjlksahdasfhuaifhaljkhakj"}
});

// 管理员登录
Mock.mock(MockHandle.RegularApi(Api.DO_LOGIN_ADMIN + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"shfajkfhafaklghklfjklfjlksahdasfhuaifhaljkhakj"}
});

// ---------------管理员--------------- //
// 添加学生
Mock.mock(MockHandle.RegularApi(Api.ADD_STUDENT + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 获取学生信息
Mock.mock(MockHandle.RegularApi(Api.GET_ALL_STUDENT + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":[{"studentId":1,"sex":1,"studentNumber":"999","studentPassword":"123131312","studentIdcard":"123123213213213","studentName":"1231321","createTime":"2019-01-01","updateTime":"2019-01-01"},{"studentId":1,"sex":1,"studentNumber":"123132131","studentPassword":"123131312","studentIdcard":"123123213213213","studentName":"1231321","createTime":"2019-01-01","updateTime":"2019-01-01"}]}
});

// 修改学生信息
Mock.mock(MockHandle.RegularApi(Api.CHANGE_STUDENT_INFO + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 添加教师
Mock.mock(MockHandle.RegularApi(Api.ADD_TEACHER + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 删除教师
Mock.mock(MockHandle.RegularApi(Api.DEL_TEACHER + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 获取教师信息
Mock.mock(MockHandle.RegularApi(Api.GET_ALL_TEACHER + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":[{"teacherId":1,"sex":1,"teacherNumber":"123132131","teacherPassword":"123131312","teacherIdcard":"123123213213213","teacherName":"1231321","age":2,"createTime":"2019-01-01","updateTime":"2019-01-01"},{"teacherId":1,"sex":1,"teacherNumber":"123132131","teacherPassword":"123131312","teacherIdcard":"123123213213213","teacherName":"1231321","age":3,"createTime":"2019-01-01","updateTime":"2019-01-01"}]}
});

// 修改教师信息
Mock.mock(MockHandle.RegularApi(Api.CHANGE_TEACHER_INFO + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 添加班级
Mock.mock(MockHandle.RegularApi(Api.ADD_CLASS + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 获取班级信息
Mock.mock(MockHandle.RegularApi(Api.GET_ALL_CLASS + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":[{"classId":1,"classNumber":"123132","createTime":"2019-01-10 16:07:34","updateTime":"2019-01-01"},{"classId":1,"classNumber":"123132","createTime":"2019-01-10 16:07:34","updateTime":"2019-01-01"}]}
});

// 修改班级信息
Mock.mock(MockHandle.RegularApi(Api.CHANGE_CLASS_INFO + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 添加听力题目
Mock.mock(MockHandle.RegularApi(Api.ADD_AURAL + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 获取所有听力题目
Mock.mock(MockHandle.RegularApi(Api.GET_ALL_AURAL + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":[{"id":11,"optionA":"123132131","optionB":"123123","optionC":"123123213213213","optionD":"dddd","answer":"A","subjectTitle":"123131312"},{"id":11,"optionA":"123132131","optionB":"123123","optionC":"123123213213213","optionD":"dddd","answer":"C","subjectTitle":"123131312"}]}
});

// 修改听力题目
Mock.mock(MockHandle.RegularApi(Api.CHANGE_AURAL_INFO + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 添加朗读文本
Mock.mock(MockHandle.RegularApi(Api.ADD_READ + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 获取所有朗读题目
Mock.mock(MockHandle.RegularApi(Api.GET_ALL_READ + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":[{"oralLanguageId":10,"oralLanguageText":"123131312","createTime":"2019-01-01","updateTime":"2019-01-01"},{"oralLanguageId":10,"oralLanguageText":"123131312","createTime":"2019-01-01","updateTime":"2019-01-01"}]}
});

// 修改朗读题目
Mock.mock(MockHandle.RegularApi(Api.CHANGE_READ_INFO + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 添加老师和学生和科室的关系
Mock.mock(MockHandle.RegularApi(Api.CLASS_TEACHER_STUDENT + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 删除口语
Mock.mock(MockHandle.RegularApi(Api.DEL_READ + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 删除听力
Mock.mock(MockHandle.RegularApi(Api.DEL_AURAL + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 删除班级
Mock.mock(MockHandle.RegularApi(Api.DEL_CLASS + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});




// ---------------教师端--------------- //
// 获取所有班级
Mock.mock(MockHandle.RegularApi(Api.GET_TEA_ALL_CLASS + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":1,"data":[{"classId":1,"classNumber":"SS0023","createTime":"2018-12-31 22:54:48","updateTime":"2018-12-31 22:54:48"},{"classId":2,"classNumber":"SS0024","createTime":"2018-12-31 22:54:48","updateTime":"2018-12-31 22:54:48"},{"classId":3,"classNumber":"SS0025","createTime":"2018-12-31 22:54:48","updateTime":"2018-12-31 22:54:48"}]}
});

// 获取学生
Mock.mock(MockHandle.RegularApi(Api.GET_TEA_ALL_STUDENT + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":[{"studentId":1,"sex":0,"studentNumber":"1640129310","studentIdcard":"445224199811233078","studentName":"CCJ","createTime":"2018-12-31 21:50:35","updateTime":"2018-12-31 05:50:39"}]}
});

// 添加朗读题目
Mock.mock(MockHandle.RegularApi(Api.ADD_TEACHER_READ + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 布置听力作业
Mock.mock(MockHandle.RegularApi(Api.ADD_CLASS_WORK + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 查看所有口语作业
Mock.mock(MockHandle.RegularApi(Api.GET_TEACHER_ALL_ORAL + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":[{"oralLanguageId":1,"oralLanguageText":"生活就像海洋只有意志坚强的人才能到达彼岸","createTime":"2018-12-31 23:57:47","updateTime":null},{"oralLanguageId":3,"oralLanguageText":"kkjhhghgfgfggv","createTime":"2019-01-10 10:49:52","updateTime":null}]}
});

// 布置口语作业
Mock.mock(MockHandle.RegularApi(Api.ADD_CLASS_LANGUAGE + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 修改密码
Mock.mock(MockHandle.RegularApi(Api.CHANGE_TEACHER_PASSWORD + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 获取教师个人信息
Mock.mock(MockHandle.RegularApi(Api.GET_TEACHER_INFO + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":{"teacherId":1,"sex":0,"teacherNumber":"1001","teacherIdcard":"445224199811233078","teacherName":"CCJ","age":20,"createTime":null,"updateTime":"2018-12-31 22:51:07"}}
});

// 教师删除学生
Mock.mock(MockHandle.RegularApi(Api.DEL_TEA_STUDENT + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});




// ---------------学生端--------------- //
// 获取学生个人信息
Mock.mock(MockHandle.RegularApi(Api.GET_STU_INFO + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":{"studentId":1111,"sex":1,"studentNumber":"12313131","studentIdcard":"111","studentName":"dddd","createTime":null,"updateTime":null}}
});

// 查看作业信息
Mock.mock(MockHandle.RegularApi(Api.GET_STU_HOME_WORK_LIST + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":{"listeningHomeWorkVo":[{"homeworkId":1,"homeworkType":1,"num":10},{"homeworkId":2,"homeworkType":1,"num":10}],"oralLanguageIdVo":{"homeworkId":1,"homeworkType":0,"num":5,"list":[{"oralLanguageId":1,"oralLanguageText":"test","createTime":"2019-01-06 20:15:36","updateTime":null},{"oralLanguageId":1,"oralLanguageText":"test","createTime":"2019-01-06 20:15:36","updateTime":null}]}}}
});

// 查看历史听力训练
Mock.mock(MockHandle.RegularApi(Api.GET_HISTORY_LISTEN + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":{"id":1,"total":100,"scope":100,"listeningSubjectHistoryVos":[{"id":1,"optionA":"A题目内容","optionB":"B题目内容","optionC":"C题目内容","optionD":"D题目内容","answer":"A","subjectTitle":"题目","userAnswer":"A"},{"id":1,"optionA":"A题目内容","optionB":"B题目内容","optionC":"C题目内容","optionD":"D题目内容","answer":"A","subjectTitle":"题目","userAnswer":"A"}]}}
});

// 获取听力题目
Mock.mock(MockHandle.RegularApi(Api.GET_STU_LISTEN_LIST + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":[{"subjectId":6,"optionA":"ll","optionB":"的","optionC":"费规格","optionD":"核桃仁和","answer":"B","subjectTitle":"分隔符"},{"subjectId":8,"optionA":"电风扇","optionB":"水费","optionC":"水电费","optionD":"如若","answer":"A","subjectTitle":"覆盖"},{"subjectId":9,"optionA":"二等分","optionB":"地方","optionC":"二","optionD":"个工人房","answer":"B","subjectTitle":"水电费"},{"subjectId":13,"optionA":"但根据地三天假进估价师","optionB":"绝对佛山","optionC":"积分送","optionD":"积分送泼尼","answer":"V ","subjectTitle":"管控"},{"subjectId":14,"optionA":"三个","optionB":"水电费共同","optionC":"电话","optionD":"个","answer":"A","subjectTitle":"阿萨德"},{"subjectId":16,"optionA":"后台","optionB":"水电费","optionC":"人工的","optionD":"十多个","answer":"C","subjectTitle":"54"},{"subjectId":17,"optionA":"电控水平","optionB":"格式","optionC":"方式","optionD":"发","answer":"D","subjectTitle":"发"},{"subjectId":18,"optionA":"官方手机丢就发","optionB":" 看懂盘","optionC":"或","optionD":"大家啊送 ","answer":"D","subjectTitle":"反馈都"},{"subjectId":19,"optionA":"输入","optionB":"的饭盒","optionC":"十多个","optionD":"他","answer":"A","subjectTitle":"r6t4"},{"subjectId":20,"optionA":"发生家","optionB":"到可否","optionC":"发顺丰OK上午【","optionD":" 萨达","answer":"A ","subjectTitle":"个胶片"}]}
});

// 提交听力作业
Mock.mock(MockHandle.RegularApi(Api.SEND_LISTEN_WORK + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":"ok"}
});

// 口语历史
Mock.mock(MockHandle.RegularApi(Api.GET_HISTORY_READ + Switch(true)), (req, res) => {
	req = MockHandle.SplitOption(req);
	return {"msg":"success","code":0,"count":0,"data":[{"homeworkId":1,"homeworkType":1,"oralLanguageId":1,"oralLanguageText":"ddddddd","longText":"tttttttttttt","homeWorkPath":"http://localhost:8080/mp3/tse.mp3"},{"homeworkId":1,"homeworkType":1,"oralLanguageId":1,"oralLanguageText":"ddddddd","longText":"tttttttttttt","homeWorkPath":"http://localhost:8080/mp3/tse.mp3"}]}
});