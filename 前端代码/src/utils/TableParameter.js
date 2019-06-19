import * as Api from './ApiInterface'
import { GetQueryString, GetInfo } from './DealFun.js'
// 管理员查看所有学生
export const GET_ALL_STUDENT_TABLE = {
	url: Api.GET_ALL_STUDENT,
	cols: [[
		{type: 'numbers', width: '60', title: '序号', fixed: 'left'},
		{field: 'studentNumber', width: '135', title: '学号'},
		{field: 'studentName', width: '150', title: '姓名'},
		{field: 'sex', width:100, title: '性别', FunDeal: {
			Fun: 'ReplaceState',
			StateNum: '1|0',
			StateText: '女|男'
		}},
		{field: 'studentIdcard', width: '180', title: '身份证号码'},
		{fixed: 'right', width:'80', align:'center', toolbarList: [{
			title: '编辑',
			style: 'layui-btn-normal',
			event: 'edit'
		}]}
	]],
};

// 管理员查看所有教师
export const GET_ALL_TEACHER_TABLE = {
	url: Api.GET_ALL_TEACHER,
	cols: [[
		{type: 'numbers', width: '60', title: '序号', fixed: 'left'},
		{field: 'teacherNumber', width: '135', title: '学号'},
		{field: 'teacherName', width: '150', title: '姓名'},
		{field: 'sex', width:100, title: '性别', FunDeal: {
			Fun: 'ReplaceState',
			StateNum: '1|0',
			StateText: '女|男'
		}},
		{field: 'teacherIdcard', width: '180', title: '身份证号码'},
		{field: 'age', width: '100', title: '年龄'},
		{fixed: 'right', width:'120', align:'center', toolbarList: [{
			title: '编辑',
			style: 'layui-btn-normal',
			event: 'edit'
		}, {
			title: '删除',
			style: 'layui-btn-danger',
			event: 'del'
		}]}
	]],
};

// 管理员查看所有班级
export const GET_ALL_CLASS_TABLE = {
	url: Api.GET_ALL_CLASS,
	cols: [[
		{type: 'numbers', width: '60', title: '序号', fixed: 'left'},
		{field: 'classNumber', width: '135', title: '班级编号'},
		{field: 'createTime', width: '180', title: '创建时间', FunDeal: 'ConvertTime'},
		{fixed: 'right', width:'220', align:'center', toolbarList: [{
			title: '编辑',
			style: 'layui-btn-normal',
			event: 'edit'
		}, {
			title: '删除',
			style: 'layui-btn-danger',
			event: 'del'
		}, {
			title: '添加教师学生',
			style: 'layui-btn-normal',
			event: 'add'
		}]}
	]],
};

// 管理员查看听力题目
export const GET_ALL_AURAL_TABLE = {
	url: Api.GET_ALL_AURAL,
	cols: [[
		{type: 'numbers', width: '60', title: '序号', fixed: 'left'},
		{field: 'subjectTitle', width: '135', title: '听力题目'},
		{field: 'optionA', width: '135', title: '选项A'},
		{field: 'optionB', width: '135', title: '选项B'},
		{field: 'optionC', width: '135', title: '选项C'},
		{field: 'optionD', width: '135', title: '选项D'},
		{field: 'answer', width: '135', title: '答案'},
		{fixed: 'right', width:'120', align:'center', toolbarList: [{
			title: '编辑',
			style: 'layui-btn-normal',
			event: 'edit'
		}, {
			title: '删除',
			style: 'layui-btn-danger',
			event: 'del'
		}]}
	]],
};

// 管理员查看口语题目
export const GET_ALL_READ_TABLE = {
	url: Api.GET_ALL_READ,
	cols: [[
		{type: 'numbers', width: '60', title: '序号', fixed: 'left'},
		{field: 'oralLanguageText', width: '400', title: '口语文本'},
		{field: 'createTime', width: '180', title: '添加时间', FunDeal: 'ConvertTime'},
		{fixed: 'right', width:'120', align:'center', toolbarList: [{
			title: '编辑',
			style: 'layui-btn-normal',
			event: 'edit'
		}, {
			title: '删除',
			style: 'layui-btn-danger',
			event: 'del'
		}]}
	]],
};

// 教师查看学生
export const GET_TEA_ALL_STUDENT_TABLE = {
	url: Api.GET_TEA_ALL_STUDENT,
	cols: [[
		{type: 'numbers', width: '60', title: '序号', fixed: 'left'},
		{field: 'studentNumber', width: '135', title: '学号'},
		{field: 'studentName', width: '150', title: '姓名'},
		{field: 'sex', width:100, title: '性别', FunDeal: {
			Fun: 'ReplaceState',
			StateNum: '1|0',
			StateText: '女|男'
		}},
		{field: 'studentIdcard', width: '180', title: '身份证号码'},
		{field: 'createTime', width: '180', title: '创建时间', FunDeal: 'ConvertTime'},
		{fixed: 'right', width:'80', align:'center', toolbarList: [{
			title: '删除',
			style: 'layui-btn-danger',
			event: 'del'
		}]}
	]],
};

// 教师查看口语作业
export const GET_TEACHER_ALL_ORAL_TABLE = {
	url: Api.GET_TEACHER_ALL_ORAL,
	cols: [[
		{type: 'numbers', width: '60', title: '序号', fixed: 'left'},
		{field: 'oralLanguageText', width: '400', title: '口语文本'},
		{field: 'createTime', width: '180', title: '添加时间', FunDeal: 'ConvertTime'},
		{fixed: 'right', width:'80', align:'center', toolbarList: [{
			title: '选择',
			style: 'layui-btn-normal',
			event: 'choose'
		}]}
	]],
};

// 教师查看学生作业情况——口语
export const GET_TEACHER_HOME_WORK_READ = {
	url: Api.GET_TEA_HOMEWORK,
	cols: [[
		{type: 'numbers', width: '60', title: '序号', fixed: 'left'},
		{field: 'sno', width: '135', title: '学号'},
		{field: 'name', width: '150', title: '学生姓名'},
		{field: 'sex', width:100, title: '性别', FunDeal: {
			Fun: 'ReplaceState',
			StateNum: '1|0',
			StateText: '女|男'
		}},
		{field: 'systempf', width: '120', title: '系统评分'},
		{field: 'teacherpf', width: '120', title: '教师评分'},
		{field: 'oralLanguageText', width: '400', title: '口语文本'},
		{field: 'longText', width: '400', title: '翻译文本'},
		{field: 'status', width:100, title: '完成状态', FunDeal: {
			Fun: 'ReplaceState',
			StateNum: '0|1',
			StateText: '未完成|已完成'
		}},
		{fixed: 'right', width:'120', align:'center', toolbarList: [{
			title: '评分',
			style: 'layui-btn-warm',
			event: 'pf'
		}, {
			title: '播放',
			style: 'layui-btn-normal',
			event: 'play'
		}]}
	]],
};

// 教师查看学生作业情况——听力
export const GET_TEACHER_HOME_WORK_LISTEN = {
	url: Api.GET_TEA_HOMEWORK,
	cols: [[
		{type: 'numbers', width: '60', title: '序号', fixed: 'left'},
		{field: 'sno', width: '135', title: '学号'},
		{field: 'name', width: '150', title: '学生姓名'},
		{field: 'sex', width:100, title: '性别', FunDeal: {
			Fun: 'ReplaceState',
			StateNum: '1|0',
			StateText: '女|男'
		}},
		{field: 'studentHomeWorkScore', width: '150', title: '成绩'},
		{field: 'status', width:100, title: '完成状态', FunDeal: {
			Fun: 'ReplaceState',
			StateNum: '0|1',
			StateText: '未完成|已完成'
		}},
	]],
};

// 学生端查看口语历史训练
export const GET_HISTORY_READ_TABLE = {
	url: Api.GET_HISTORY_READ,
	cols: [[
		{type: 'numbers', width: '60', title: '序号', fixed: 'left'},
		{field: 'oralLanguageText', width: '400', title: '口语文本原文'},
		{field: 'longText', width: '400', title: '识别文本'},
		{field: 'homeWorkPath', width: '150', title: '我的录音'},
		{fixed: 'right', width:'120', align:'center', toolbarList: [{
			title: '播放我的录音',
			style: 'layui-btn-normal',
			event: 'play'
		}]}
	]],
};