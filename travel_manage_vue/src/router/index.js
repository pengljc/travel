import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from "../views/Login";
import Home from "../views/Home";
import Welcome from "../views/Welcome";
import Ticket from "../views/staff/Ticket";
import Reimbursement from "../views/staff/Reimbursement";
import Reimbursement_add from "../views/staff/Reimbursement_add";
import Ticket_add from "../views/staff/Ticket_add";



Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'login',
        component: Login,
        meta: {
            title: "登录"
        }
    },
    {
        path: '/home',
        name: 'home',
        redirect:'/welcome',
        component: Home,
        meta: {
            title: "差旅报销系统"
        },
        children:[
            {path:'/welcome',name:'welcome',component:Welcome,meta:{title:'主页'}},
            {path:'/ticket',name:'ticket',component:Ticket,meta:{title:'车票信息'}},
            {path:'/ticket/add',name:'ticket_add',component:Ticket_add,meta:{title:'新增车票'}},
            {path:'/ticket/edit/:tno',name:'ticket_add',component:Ticket_add,meta:{title:'编辑车票'}},
            {path:'/reimbursement',name:'reimbursement',component:Reimbursement,meta:{title:'报销信息'}},
            {path:'/reimbursement/add',name:'reimbursement_add',component:Reimbursement_add,meta:{title:'报销信息新增'}},
        ]
    }
]

const router = new VueRouter({
    routes
})

router.beforeEach((to, from, next) => {
    // console.log(to.name);
    if (to.name == 'login') {
        // console.log(sessionStorage.getItem('eno'));
        next()
    } else {
        next()
    }

    if (sessionStorage.getItem("eno") == null) {
        next({
            path: '/',
        })
    } else {
        next()
    }
})
router.afterEach((to, from) => {
    //获取每个路由的meta信息，赋值给浏览器的标题属性
    window.document.title = to.meta && (to.meta.title || '默认')
})

export default router
