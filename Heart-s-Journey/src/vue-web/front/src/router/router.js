import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Forum from '../pages/forum/list'
import ForumAdd from '../pages/forum/add'
import ForumDetail from '../pages/forum/detail'
import MyForumList from '../pages/forum/myForumList'
import ExamPaper from '../pages/exam/examPaper'
import Exam from '../pages/exam/exam'
import ExamList from '../pages/exam/examList'
import ExamRecord from '../pages/exam/examRecord'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import ziyuanleixingList from '../pages/ziyuanleixing/list'
import ziyuanleixingDetail from '../pages/ziyuanleixing/detail'
import ziyuanleixingAdd from '../pages/ziyuanleixing/add'
import xuexiziyuanList from '../pages/xuexiziyuan/list'
import xuexiziyuanDetail from '../pages/xuexiziyuan/detail'
import xuexiziyuanAdd from '../pages/xuexiziyuan/add'
import xuexifankuiList from '../pages/xuexifankui/list'
import xuexifankuiDetail from '../pages/xuexifankui/detail'
import xuexifankuiAdd from '../pages/xuexifankui/add'
import huizhangxinxiList from '../pages/huizhangxinxi/list'
import huizhangxinxiDetail from '../pages/huizhangxinxi/detail'
import huizhangxinxiAdd from '../pages/huizhangxinxi/add'
import huizhangduihuanList from '../pages/huizhangduihuan/list'
import huizhangduihuanDetail from '../pages/huizhangduihuan/detail'
import huizhangduihuanAdd from '../pages/huizhangduihuan/add'
import scoredeterminationList from '../pages/scoredetermination/list'
import scoredeterminationDetail from '../pages/scoredetermination/detail'
import scoredeterminationAdd from '../pages/scoredetermination/add'
import forumtypeList from '../pages/forumtype/list'
import forumtypeDetail from '../pages/forumtype/detail'
import forumtypeAdd from '../pages/forumtype/add'
import forumreportList from '../pages/forumreport/list'
import forumreportDetail from '../pages/forumreport/detail'
import forumreportAdd from '../pages/forumreport/add'
import syslogList from '../pages/syslog/list'
import syslogDetail from '../pages/syslog/detail'
import syslogAdd from '../pages/syslog/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'forum',
					component: Forum
				},
				{
					path: 'forumAdd',
					component: ForumAdd
				},
				{
					path: 'forumDetail',
					component: ForumDetail
				},
				{
					path: 'myForumList',
					component: MyForumList
				},
				{
					path: 'examPaper',
					component: ExamPaper
				},
				{
					path: 'examList',
					component:ExamList
				},
				{
					path: 'examRecord/:type',
					component:ExamRecord
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'ziyuanleixing',
					component: ziyuanleixingList
				},
				{
					path: 'ziyuanleixingDetail',
					component: ziyuanleixingDetail
				},
				{
					path: 'ziyuanleixingAdd',
					component: ziyuanleixingAdd
				},
				{
					path: 'xuexiziyuan',
					component: xuexiziyuanList
				},
				{
					path: 'xuexiziyuanDetail',
					component: xuexiziyuanDetail
				},
				{
					path: 'xuexiziyuanAdd',
					component: xuexiziyuanAdd
				},
				{
					path: 'xuexifankui',
					component: xuexifankuiList
				},
				{
					path: 'xuexifankuiDetail',
					component: xuexifankuiDetail
				},
				{
					path: 'xuexifankuiAdd',
					component: xuexifankuiAdd
				},
				{
					path: 'huizhangxinxi',
					component: huizhangxinxiList
				},
				{
					path: 'huizhangxinxiDetail',
					component: huizhangxinxiDetail
				},
				{
					path: 'huizhangxinxiAdd',
					component: huizhangxinxiAdd
				},
				{
					path: 'huizhangduihuan',
					component: huizhangduihuanList
				},
				{
					path: 'huizhangduihuanDetail',
					component: huizhangduihuanDetail
				},
				{
					path: 'huizhangduihuanAdd',
					component: huizhangduihuanAdd
				},
				{
					path: 'scoredetermination',
					component: scoredeterminationList
				},
				{
					path: 'scoredeterminationDetail',
					component: scoredeterminationDetail
				},
				{
					path: 'scoredeterminationAdd',
					component: scoredeterminationAdd
				},
				{
					path: 'forumtype',
					component: forumtypeList
				},
				{
					path: 'forumtypeDetail',
					component: forumtypeDetail
				},
				{
					path: 'forumtypeAdd',
					component: forumtypeAdd
				},
				{
					path: 'forumreport',
					component: forumreportList
				},
				{
					path: 'forumreportDetail',
					component: forumreportDetail
				},
				{
					path: 'forumreportAdd',
					component: forumreportAdd
				},
				{
					path: 'syslog',
					component: syslogList
				},
				{
					path: 'syslogDetail',
					component: syslogDetail
				},
				{
					path: 'syslogAdd',
					component: syslogAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
		{
			path: '/exam',
			component: Exam
		}
	]
})
