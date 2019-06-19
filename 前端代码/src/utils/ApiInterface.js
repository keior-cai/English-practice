// const baseUrl = 'http://172.16.106.126:8080'
const baseUrl = 'http://127.0.0.1:8080'

// 学生登录
export const DO_LOGIN_STUDENT = baseUrl + '/api/student/login';
// 教师登陆
export const DO_LOGIN_TEACHER = baseUrl + '/api/teacher/login';
// 管理员登陆
export const DO_LOGIN_ADMIN = baseUrl + '/api/admin/login';
// ---------------管理员--------------- //
// 添加学生
export const ADD_STUDENT = baseUrl + '/api/admin/addStudent';
// 获取学生信息
export const GET_ALL_STUDENT = baseUrl + '/api/admin/selectAllStudent';
// 修改学生信息
export const CHANGE_STUDENT_INFO = baseUrl + '/api/admin/updateStudentInfo';
// 添加教师
export const ADD_TEACHER = baseUrl + '/api/admin/addTeacher';
// 获取教师信息
export const GET_ALL_TEACHER = baseUrl + '/api/admin/selectAllTeacher';
// 修改教师信息
export const CHANGE_TEACHER_INFO = baseUrl + '/api/admin/updateTeacherInfo';
// 添加班级
export const ADD_CLASS = baseUrl + '/api/admin/addClassInfo';
// 获取班级信息
export const GET_ALL_CLASS = baseUrl + '/api/admin/selectAllClassInfo';
// 修改班级信息
export const CHANGE_CLASS_INFO = baseUrl + '/api/admin/updateClassInfo';
// 添加听力题目
export const ADD_AURAL = baseUrl + '/api/admin/addListeningSubject';
// 获取所有听力题目
export const GET_ALL_AURAL = baseUrl + '/api/admin/getAllListeningSubject';
// 修改听力题目
export const CHANGE_AURAL_INFO = baseUrl + '/api/admin/updateListeningSubject';
// 添加朗读文本
export const ADD_READ = baseUrl + '/api/admin/addOralLanguage';
// 获取所有朗读题目
export const GET_ALL_READ = baseUrl + '/api/admin/selectAllOralLanguage';
// 修改朗读题目
export const CHANGE_READ_INFO = baseUrl + '/api/admin/updateOralLanguage';
// 添加老师和学生和科室的关系
export const CLASS_TEACHER_STUDENT = baseUrl + '/api/admin/addTeacherClassAndStudent';
// 删除教师
export const DEL_TEACHER = baseUrl + '/api/admin/deleteTeacher';
// 删除口语题目
export const DEL_READ = baseUrl + '/api/admin/deleteOralLanguage';
// 删除听力题目
export const DEL_AURAL = baseUrl + '/api/admin/deleteListeningSubject';
// 删除班级
export const DEL_CLASS = baseUrl + '/api/admin/deleteClass';
// 上传听力题目音频文件
export const UPLOAD_LISTEN_FILE = baseUrl + '/api/admin/uploadListeningSubject';


// ---------------教师--------------- //
// 获取班级
export const GET_TEA_ALL_CLASS = baseUrl + '/api/teacher/getClassAll';
// 获取学生
export const GET_TEA_ALL_STUDENT = baseUrl + '/api/teacher/getStudents';
// 添加阅读文本
export const ADD_TEACHER_READ = baseUrl + '/api/teacher/addOralLanguage';
// 布置听力作业
export const ADD_CLASS_WORK = baseUrl + '/api/teacher/addClassWork';
// 查看所有口语作业
export const GET_TEACHER_ALL_ORAL = baseUrl + '/api/teacher/getOralLanguageWork';
// 布置口语作业
export const ADD_CLASS_LANGUAGE = baseUrl + '/api/teacher/addOralLanguageWork';
// 修改密码
export const CHANGE_TEACHER_PASSWORD = baseUrl + '/api/teacher/updatePwd';
// 获取教师个人信息
export const GET_TEACHER_INFO = baseUrl + '/api/teacher/getInfo';
// 导出口语记录
export const EXPORT_HISTORY = baseUrl + '/api/teacher/dataOut';
// 查看学生作业情况
export const GET_TEA_HOMEWORK = baseUrl + '/api/teacher/getStudentHomeWork';
// 教师删除学生
export const DEL_TEA_STUDENT = baseUrl + '/api/admin/deleteStudent';
// 录入成绩
export const LURU_CHENGJI = baseUrl + '/api/teacher/teacherpf';


// ---------------学生--------------- //
// 获取学生个人信息
export const GET_STU_INFO = baseUrl + '/api/student/getStudentInfo';
// 查看作业信息
export const GET_STU_HOME_WORK_LIST = baseUrl + '/api/student/selectClassHomeWork';
// 查看历史听力训练
export const GET_HISTORY_LISTEN = baseUrl + '/api/student/getHistoryHearingTraining';
// 获取听力题目
export const GET_STU_LISTEN_LIST = baseUrl + '/api/student/getListeningSubjectProblem';
// 提交听力作业
export const SEND_LISTEN_WORK = baseUrl + '/api/student/submitTopic';
// 上传口语作业
export const UPLOAD_READ_FILE = baseUrl + '/api/student/uploadOralLanguage';
// 查看历史口语训练
export const GET_HISTORY_READ = baseUrl + '/api/student/getHistoryOralLanguage';