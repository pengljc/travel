import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        //存储工号
        eno:'',
        //存储姓名
        ename:'',
        //存储身份
        postno:''
    },
    getters: {},
    mutations: {
        setEno:function (state,eno) {
            state.eno = eno
        },
        setEname:function (state,ename) {
            state.ename = ename
        },
        setPostno:function (state,postno) {
            state.postno = postno
        }
    },
    actions: {
        saveEno:function (context,eno) {
            context.commit("setEno",eno)
        },
        saveEname:function (context,ename) {
            context.commit("setEname",ename)
        },
        savePostno:function (context,postno) {
            context.commit("setPostno",postno)
        }
    },
    modules: {}
})
