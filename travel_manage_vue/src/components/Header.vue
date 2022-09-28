<template>
	<div style="height: 50px">
		<el-row>
			<el-col :span="8">
				<div style="font-weight: bold">差旅报销系统</div>
			</el-col>
			<el-col :span="15">&nbsp;</el-col>
			<el-col :span="1">
				<el-dropdown trigger="click" @command="handleCommand">
                    <span class="el-dropdown-link">
                        {{ename}}<i class="el-icon-arrow-down el-icon--right"></i>
                    </span>
					<el-dropdown-menu slot="dropdown">
						<el-dropdown-item command="personal">个人信息</el-dropdown-item>
						<el-dropdown-item command="logout">退出系统</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</el-col>
		</el-row>


	</div>
</template>

<script>
    export default {
        name: "Header",
	    data(){
            return {
				ename:''
            }
	    },
	    //把库中的ename存到ename中
	    created() {
            this.ename = sessionStorage.getItem("ename")
        },
	    methods:{
            // 判断下拉菜单指令
            handleCommand(command) {
                // this.$message('click on item ' + command);
                if (command == "personal"){
                    this.personal()
                }else {
                    this.logout()
                }
            },
		    //退出
            logout() {
                /*清除用户信息并跳转回登录页面*/
                sessionStorage.clear()
                //清除vuex中的用户信息，
                this.$router.push({
                    path:'/'
                })
            },
		    //个人信息
		    personal() {

		    }
	    }
    }
</script>

<style scoped>

</style>
