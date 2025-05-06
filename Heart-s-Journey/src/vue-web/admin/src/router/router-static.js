import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import adminexam from '@/views/modules/exampaperlist/exam'
	import news from '@/views/modules/news/list'
	import ziyuanleixing from '@/views/modules/ziyuanleixing/list'
	import examquestion from '@/views/modules/examquestion/list'
	import scoredetermination from '@/views/modules/scoredetermination/list'
	import huizhangxinxi from '@/views/modules/huizhangxinxi/list'
	import syslog from '@/views/modules/syslog/list'
	import exampaper from '@/views/modules/exampaper/list'
	import huizhangduihuan from '@/views/modules/huizhangduihuan/list'
	import forum from '@/views/modules/forum/list'
	import forumtype from '@/views/modules/forumtype/list'
	import yonghu from '@/views/modules/yonghu/list'
	import forumreport from '@/views/modules/forumreport/list'
	import exampaperlist from '@/views/modules/exampaperlist/list'
	import xuexifankui from '@/views/modules/xuexifankui/list'
	import config from '@/views/modules/config/list'
	import examrecord from '@/views/modules/examrecord/list'
	import xuexiziyuan from '@/views/modules/xuexiziyuan/list'
	import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/news',
		name: '公告信息',
		component: news
	}
	,{
		path: '/ziyuanleixing',
		name: '资源类型',
		component: ziyuanleixing
	}
	,{
		path: '/examquestion',
		name: '试题管理',
		component: examquestion
	}
	,{
		path: '/scoredetermination',
		name: '分数判定',
		component: scoredetermination
	}
	,{
		path: '/huizhangxinxi',
		name: '徽章信息',
		component: huizhangxinxi
	}
	,{
		path: '/syslog',
		name: '系统日志',
		component: syslog
	}
	,{
		path: '/exampaper',
		name: '心理测试管理',
		component: exampaper
	}
	,{
		path: '/huizhangduihuan',
		name: '徽章兑换',
		component: huizhangduihuan
	}
	,{
		path: '/forum',
		name: '互动社区',
		component: forum
	}
	,{
		path: '/forumtype',
		name: '论坛分类',
		component: forumtype
	}
	,{
		path: '/yonghu',
		name: '用户',
		component: yonghu
	}
	,{
		path: '/forumreport',
		name: '举报记录',
		component: forumreport
	}
	,{
		path: '/exampaperlist',
		name: '心理测试列表',
		component: exampaperlist
	}
	,{
		path: '/xuexifankui',
		name: '学习反馈',
		component: xuexifankui
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/examrecord',
		name: '测试记录',
		component: examrecord
	}
	,{
		path: '/xuexiziyuan',
		name: '学习资源',
		component: xuexiziyuan
	}
	,{
		path: '/newstype',
		name: '公告信息分类',
		component: newstype
	}
	]
	},
	{
		path: '/adminexam',
		name: 'adminexam',
		component: adminexam,
		meta: {icon:'', title:'adminexam'}
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
