<template>
	<div>
		<!--左侧导航栏-->
		<!--:router = "true"  代表开启路由模式，当点击菜单时，会将具体菜单的index属性，作为浏览器的访问路径-->
		<!--default-active="/emp" 让某个菜单高亮显示，路由无变化-->
		<el-menu

				class="el-menu-vertical-demo"
				:router="true"
				style="height: 500px">
			<el-menu-item index="/welcome">
				<i class="el-icon-s-home"></i>
				<span slot="title">首页</span>
			</el-menu-item>

			<el-menu-item v-for="(nav,index) in navs" :key="index" :index="nav.path">
				<i :class="nav.icon"></i>
				<span slot="title">{{nav.content}}</span>
			</el-menu-item>

			<!--	<el-menu-item index="/ticket">
					<i class="el-icon-s-ticket"></i>
					<span slot="title">车票信息</span>
				</el-menu-item>
				<el-menu-item index="/reimbursement" >
					<i class="el-icon-s-check"></i>
					<span slot="title">报销信息</span>
				</el-menu-item>-->
		</el-menu>
	</div>
</template>

<script>
    export default {
        name: "Aside",
        data() {
            return {
                navs: []
            }
        },
        created() {
            //把postno传入后台，动态获取导航栏
            this.$http.get('/navs/' + sessionStorage.getItem("postno")).then((res) => {
                this.navs = res.data.data
                // console.log(this.navs);
            })
        }
    }
</script>

<style scoped>

</style>
