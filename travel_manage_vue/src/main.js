import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import qs from 'qs'
/*全局引入element-ui*/
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(ElementUI)

/*全局配置axios*/
//导入axios
import axios from 'axios';
//设置访问根路径
axios.defaults.baseURL='http://localhost:8086/';
//挂载axios
Vue.prototype.$http = axios;

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: function (h) { return h(App) }
}).$mount('#app')
