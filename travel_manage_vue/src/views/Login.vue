<template>
	<div align="center" style="min-height: 95vh;margin: 0;padding: 0" :style="backgroundDiv">
		<h1 style="margin-left: 40px;color: white">差旅报销系统</h1>
		<div style="background-color:white;width: 500px">
			<h3 style="margin-top: 40px;margin-left: 40px">用户登录</h3>
			<div style="width: 500px;margin-top: 20px">
				<!--ref可以看作当前表单的唯一标识-->
				<el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="100px" class="demo-ruleForm">
					<!--prop属性用于绑定具体的校验规则-->
					<el-form-item label="工号" prop="eno">
						<el-input v-model="loginForm.eno" style="width: 360px"></el-input>
					</el-form-item>
					<el-form-item label="密码" prop="password">
						<el-input v-model="loginForm.password" style="width: 360px" show-password></el-input>
					</el-form-item>

					<el-form-item style="margin-right: 60px">
						<el-button type="primary" @click="login"
						           style="margin:20px 0px 30px ;width: 100px">登录
						</el-button>
					</el-form-item>
				</el-form>
			</div>
		</div>
	</div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                loginForm: {
                    eno: '',
                    password: ''
                },
                backgroundDiv: {
                    backgroundImage: 'url(' + require('../assets/bg.jpeg') + ')',
                    backgroundRepeat: 'no-repeat',
                    backgroundSize: '100% 100%',
                },
                rules: {
                    eno: [
                        {required: true, message: '请输入用户名', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            login() {
                this.$refs['loginForm'].validate((valid) => {
                    if (valid) {
                        this.$http.post('/emps', this.loginForm).then((res) => {

                            if (res.data.state == 200) {
                                sessionStorage.setItem("eno", res.data.data.eno)
                                sessionStorage.setItem("ename", res.data.data.ename)
                                sessionStorage.setItem("postno", res.data.data.postno)
                                //登录成功，跳转页面
                                this.$router.push({
                                    path: '/home'
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
