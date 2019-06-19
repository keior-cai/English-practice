import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/page/Login/Login'
import MainFrame from '@/common/MainFrame'
import TimeOut from '@/page/TimeOut/TimeOut'
import FourZeroFour from '@/page/FourZeroFour/FourZeroFour'

// 路由列表载体
let children = [];
// 列入路由载体方法
const PushRoutes = (item => {
  children = [...children, item]
})

// 控制台
import Console from '@/page/Console/Console'
PushRoutes({ path: 'console', component: Console })

// 管理员-添加学生
import AdminAddStudent from '@/page/AdminAddStudent/AdminAddStudent'
PushRoutes({ path: 'AdminAddStudent', component: AdminAddStudent })

// 管理员-查看学生
import AdminLookStudent from '@/page/AdminLookStudent/AdminLookStudent'
PushRoutes({ path: 'AdminLookStudent', component: AdminLookStudent })

// 管理员-添加教师
import AdminAddTeacher from '@/page/AdminAddTeacher/AdminAddTeacher'
PushRoutes({ path: 'AdminAddTeacher', component: AdminAddTeacher })

// 管理员-查看教师
import AdminLookTeacher from '@/page/AdminLookTeacher/AdminLookTeacher'
PushRoutes({ path: 'AdminLookTeacher', component: AdminLookTeacher })

// 管理员-查看班级
import AdminLookClass from '@/page/AdminLookClass/AdminLookClass'
PushRoutes({ path: 'AdminLookClass', component: AdminLookClass })

// 管理员-添加听力题目
import AdminAddAural from '@/page/AdminAddAural/AdminAddAural'
PushRoutes({ path: 'AdminAddAural', component: AdminAddAural })

// 管理员-查看听力题目
import AdminLookAural from '@/page/AdminLookAural/AdminLookAural'
PushRoutes({ path: 'AdminLookAural', component: AdminLookAural })

// 管理员-查看朗读题目
import AdminLookRead from '@/page/AdminLookRead/AdminLookRead'
PushRoutes({ path: 'AdminLookRead', component: AdminLookRead })

// 教师端-查看学生
import TeacherLookStudent from '@/page/TeacherLookStudent/TeacherLookStudent'
PushRoutes({ path: 'TeacherLookStudent', component: TeacherLookStudent })

// 教师端-添加口语题目
import TeacherAddRead from '@/page/TeacherAddRead/TeacherAddRead'
PushRoutes({ path: 'TeacherAddRead', component: TeacherAddRead })

// 教师端-布置听力作业
import TeacherAssignListen from '@/page/TeacherAssignListen/TeacherAssignListen'
PushRoutes({ path: 'TeacherAssignListen', component: TeacherAssignListen })

// 教师端-布置口语作业
import TeacherAssignRead from '@/page/TeacherAssignRead/TeacherAssignRead'
PushRoutes({ path: 'TeacherAssignRead', component: TeacherAssignRead })

// 教师端-修改密码
import TeacherPassword from '@/page/TeacherPassword/TeacherPassword'
PushRoutes({ path: 'TeacherPassword', component: TeacherPassword })

// 教师端-查看个人信息
import TeacherInfo from '@/page/TeacherInfo/TeacherInfo'
PushRoutes({ path: 'TeacherInfo', component: TeacherInfo })

// 教师端-口语作业导出EXCEL
import TeacherOralOut from '@/page/TeacherOralOut/TeacherOralOut'
PushRoutes({ path: 'TeacherOralOut', component: TeacherOralOut })

// 教师端-查看学生作业
import TeacherLookHomeWork from '@/page/TeacherLookHomeWork/TeacherLookHomeWork'
PushRoutes({ path: 'TeacherLookHomeWork', component: TeacherLookHomeWork })

// 学生端-查看个人信息
import StudentInfo from '@/page/StudentInfo/StudentInfo'
PushRoutes({ path: 'StudentInfo', component: StudentInfo })

// 学生端-查看作业
import StudentGetHomeWork from '@/page/StudentGetHomeWork/StudentGetHomeWork'
PushRoutes({ path: 'StudentGetHomeWork', component: StudentGetHomeWork })

// 学生端-查看历史听力训练
import StudentGetHistoryListen from '@/page/StudentGetHistoryListen/StudentGetHistoryListen'
PushRoutes({ path: 'StudentGetHistoryListen', component: StudentGetHistoryListen })

// 学生端-做听力
import StudentDoListen from '@/page/StudentDoListen/StudentDoListen'
PushRoutes({ path: 'StudentDoListen', component: StudentDoListen })

// 学生端-插看口语作业
import StudentGetHomeWorkRead from '@/page/StudentGetHomeWorkRead/StudentGetHomeWorkRead'
PushRoutes({ path: 'StudentGetHomeWorkRead', component: StudentGetHomeWorkRead })

// 学生端-查看口语历史作业
import StudentGetHistoryRead from '@/page/StudentGetHistoryRead/StudentGetHistoryRead'
PushRoutes({ path: 'StudentGetHistoryRead', component: StudentGetHistoryRead })

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/main',
      component: MainFrame,
      children
    },
    { path: '*', component: FourZeroFour },
    { path: '/login', component: Login },
    { path: '/timeout', component: TimeOut },
  ]
});

router.beforeEach((to, from, next) => {
  $('.layui-table-tips').remove();
  // console.log(to, from)
  next();
});

export default router;
