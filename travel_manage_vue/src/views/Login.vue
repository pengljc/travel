<template>
	<div align="center">
		<h1>欢迎登录差旅系统</h1>
		<div  style="width: 500px">
			<!--ref可以看作当前表单的唯一标识-->
			<el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="100px" class="demo-ruleForm">
				<!--prop属性用于绑定具体的校验规则-->
				<el-form-item label="工号" prop="eno">
					<el-input v-model="loginForm.eno"></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="password">
					<el-input v-model="loginForm.password" show-password></el-input>
				</el-form-item>

				<el-form-item>
					<el-button type="primary" @click="login">登录</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
    export default {
        name: "Login",
	    data() {
           return {
               loginForm:{
                   eno:'',
	               password:''
               },
	           rules: {
                   eno: [
	                   {required:true, message:'请输入用户名',trigger:'blur'}
                   ],
		           password:[
                       {required:true, message:'请输入密码',trigger:'blur'}
		           ]
               }
           }
	    },
	    methods:{
            login() {
                this.$refs['loginForm'].validate((valid) => {
					if (valid) {
						this.$http.post('/emps',this.loginForm).then((res) => {
                            // console.log(res);
                            // console.log(res.data.message);
                            // console.log(res.data.state);
                            // console.log(res.data.data);
                            // console.log(sessionStorage.getItem("eno"));

							if (res.data.state == 200) {
                                // this.$message.success("登录成功")
                                sessionStorage.setItem("eno",res.data.data.eno)
                                sessionStorage.setItem("ename",res.data.data.ename)
                                sessionStorage.setItem("postno",res.data.data.postno)
                                //登录成功，跳转页面
                                this.$router.push({
                                    path:'/home'
                                })

                                // console.log(sessionStorage.getItem("eno"));
                                // console.log(sessionStorage.getItem("ename"));
                                // console.log(sessionStorage.getItem("postno"));
                            } else {
							    this.$message.error(res.data.message)
							}
                        }).catch(() => {
                            this.$message.error("服务器错误")
						})
					}
                })
            }
	    }

    }
</script>

<style scoped>

</style>
