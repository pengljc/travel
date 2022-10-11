<template>
	<div>
		<el-row>
			<el-col :span="16">
				<div>
					<!--员工姓名和部门信息的查询条件及查询按钮-->
					<el-form :inline="true" :model="queryMap">
						<el-form-item label="姓名">
							<el-input v-model="queryMap.ename" placeholder="姓名"></el-input>
						</el-form-item>
						<el-form-item label="工号">
							<el-input v-model="queryMap.eno" placeholder="工号"></el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="query">查询</el-button>
						</el-form-item>
					</el-form>
				</div>
			</el-col>
			<el-col :span="1">&nbsp;</el-col>
			<el-col :span="7">
				<div style="float: right">
					<el-button-group>
						<el-button type="primary" icon="el-icon-plus" @click="openAddDialog">新增</el-button>
						<el-button type="primary" icon="el-icon-edit" @click="openEditDialog">编辑</el-button>
					</el-button-group>
				</div>
			</el-col>
		</el-row>

		<!--数据表格-->
		<div>
			<!--员工信息的表格-->
			<!--data 代表表格要显示的数据-->
			<el-table
					:data="empList"
					border
					style="width: 100%;height: 380px"
					height="350px"
					@selection-change="selectChange">
				<el-table-column
						type="selection"
						width="55"
						fixed
						align="center">
				</el-table-column>
				<!--prop的值对应的是map中的key值-->
				<el-table-column
						type="index"
						label="序号"
						width="50"
						align="center">
				</el-table-column>
				<el-table-column
						prop="eno"
						label="工号"
						align="center"
						sortable>
				</el-table-column>
				<el-table-column
						prop="ename"
						label="姓名"
						align="center">
				</el-table-column>
				<el-table-column
						prop="password"
						label="密码"
						align="center">
				</el-table-column>
				<el-table-column
						prop="sex"
						label="性别"
						align="center"
						:formatter="formatSex">
				</el-table-column>
				<el-table-column
						prop="phone"
						label="联系方式"
						align="center">
				</el-table-column>
				<el-table-column
						prop="pname"
						label="职位"
						align="center">
				</el-table-column>
			</el-table>
			<!--分页组件-->
			<!--layout分页组件的布局，显示向前，向后，数字页面，每页可选显示条数，总条数-->
			<!--page-size每页显示条数，默认10-->
			<!--current-page当前页码，默认为1-->
			<!--page-sizes可选的每页显示条数-->
			<!--current-change 页面发生变化时触发的事件-->
			<el-pagination
					background
					layout="prev, pager, next,sizes,total"
					:total="total"
					:page-size="queryMap.limit"
					:current-page="queryMap.page"
					:page-sizes='[5,10,15,20]'
					@current-change="handleCurrentChange"
					@size-change="handleSizeChange">
			</el-pagination>
		</div>
		<!--新增或者编辑的弹出框-->
		<el-dialog :title="formTitle" :visible.sync="dialogFormVisible" >
			<el-form :model="empForm" :rules="rules" ref="empForm"   style="margin-left: 120px">
				<el-form-item  label="姓名" :label-width="formLabelWidth" prop="ename">
					<el-input  style="width: 300px" v-model="empForm.ename" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item  label="密码" :label-width="formLabelWidth" prop="password">
					<el-input  style="width: 300px" v-model="empForm.password" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="性别" :label-width="formLabelWidth" style="margin-left: 10px">
					<!--label对应的是单选钮的value值-->
					<el-radio v-model="empForm.sex" label="1" style="margin-left: 90px" >男</el-radio>
					<el-radio v-model="empForm.sex" label="0">女</el-radio>
				</el-form-item>
				<el-form-item  label="电话" :label-width="formLabelWidth" prop="phone">
					<el-input style="width: 300px" v-model="empForm.phone" autocomplete="off"></el-input>
				</el-form-item>

				<el-form-item label="职位" :label-width="formLabelWidth"  prop="postno">
					<el-select style="width: 300px" v-model="empForm.postno" placeholder="请选择职位">
						<el-option v-for="post in postList" :label="post.pname" :value="post.postno" :key="post.postno"></el-option>
					</el-select>
				</el-form-item>
			</el-form>
			<div align="center" class="dialog-footer">
				<el-button @click="dialogFormVisible = false">取 消</el-button>
				<el-button type="primary" @click="saveOrUpdateEmp">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
    export default {
        name: "emp",
        data() {
            const checkPhone = (rule, value, callback) => {
                // let phoneReg = /(^1[3|4|5|6|7|8|9]\d{9}$)|(^09\d{8}$)/;
                if (value === '') {
                    callback(new Error('请输入手机号'))
                } else if (!this.isCellPhone(value)) { // 引入methods中封装的检查手机格式的方法
                    callback(new Error('请输入正确的手机号!'))
                } else {
                    callback()
                }
            }
            return {
                queryMap: {
                    eno:'',
                    ename: '',
                    page:1,
                    limit:5
                },
                empList:[],
	            postList:[],
                //选中的记录数
                checkedData:[],
                total:0,
                /*弹出框的标题*/
                formTitle:'',
                /*控制弹出框隐藏或者显示的布尔*/
                dialogFormVisible: false,
	            empForm:{
                    eno:'',
		            ename:'',
		            sex: '',
		            password:'',
		            postno:'',
		            phone:''
	            },
                /*表单验证对象*/
                rules:{
                    ename:[
                        {required:true,message:'请输入员工姓名',trigger:'blur'}
                    ],
                    password:[
                        {required:true,message:'请输入员工密码',trigger:'blur'}
                    ],
                    postno: [
                        {required: true, message: '请选择员工职位', trigger: ['blur', 'change']}
                    ],
                    phone: [
                        {required: true, validator: checkPhone, trigger: 'blur'}
                    ]
                }
            }
        },
        methods:{
            getPostList() {
                this.$http.get('/posts').then((res) => {
                    this.postList = res.data.data
                }).catch(() =>{
                    this.message.error("服务器出错")
                })
            },
            formatSex: function (row, column, cellValue){
                if (cellValue === 0) {
                    return "女"
                } else {
                    return "男"
                }
            },
            getEmpList(){
                this.$http.get('/emps',{
                    params:this.queryMap
                }).then((res) => {
                    this.empList = res.data.data
                    this.total = res.data.total
                }).catch(() =>{
                    this.message.error("服务器出错")
                })
            },
            /*查询按钮触发的事件*/
            query() {
                this.queryMap.page = 1
                this.getEmpList()
            },
            /*当页面发生变化时触发这个事件*/
            handleCurrentChange(val) {
                /*val代表当前页码*/
                this.queryMap.page = val
                //重新请求后台
                this.getEmpList()
            },
            /*当每页显示条数发生变化时触发*/
            handleSizeChange(val) {
                this.queryMap.limit = val
                this.queryMap.page = 1
                this.getEmpList()
            },
            /*表格选中项发生变化时触发，就可以通过该方法得到当前的选中记录*/
            selectChange (val) {
                //console.log(val);
                this.checkedData = val
            },
            openAddDialog() {
                //打开弹出框
                this.dialogFormVisible = true
                //设置弹出框的标题
                this.formTitle = "新增员工"
                //信息清空
                this.empForm = {
                    eno:'',
                    ename:'',
                    sex:'1',
                    phone:'',
                    password:'',
                    postno:''
                }
                //校验提示信息的清空
                //第一次打开弹框时，dialog这个元素还没有完全渲染到页面中
                //this.refs获取到的节点是undefined
                if (this.$refs['empForm'] != undefined) {
                    this.$refs['empForm'].resetFields()
                }
            },
            openEditDialog() {
                /*判断用户是否只勾选了一条数据*/
                if (this.checkedData.length > 1 || this.checkedData.length == 0) {
                    this.$message.warning("请选择一条员工信息进行修改")
                    return
                }

                let eno = this.checkedData[0].eno
                //调用后台获取详细信息
                this.$http.get('/emps/' + eno).then(res => {
                    /*返回的员工对象赋值给empForm对象*/

                    this.empForm = res.data.data
                    console.log(this.empForm.sex);
                    //把数字转换为字符串
					this.empForm.sex += ''
                    console.log(this.empForm.sex);
                    this.dialogFormVisible = true

                    /*清空校验信息*/
                    if (this.$refs['empForm'] != undefined) {
                        this.$refs['empForm'].resetFields()
                    }
                    /*修改标题*/
                    this.formTitle = "修改员工信息"
                })
            },
            /*保存或更新员工信息*/
            saveOrUpdateEmp() {
                //首先进行表单验证，通过后才能调用后台，实现新增功能
                this.$refs['empForm'].validate(valid =>{
                    //valid代表校验结果，是布尔类型
                    if (valid) {
                        if (this.empForm.eno !== '') {
                            //编辑员工信息
                            this.$http.put('/emps',this.empForm).then(res => {
                                if (res.data.state === 200) {
                                    this.dialogFormVisible = false
                                    this.getEmpList()
                                    this.$message.success("员工修改成功")
                                } else {
                                    this.$message.error("员工修改失败，出现未知错误")
                                }
                            }).catch(() =>{
                                this.$message.error("员工修改失败，出现未知错误")
                            })
                        } else  {
                            //新增员工信息
                            //调用后台，保存数据
                            this.$http.post('/emps/add',this.empForm).then((res) => {
                                if (res.data.state === 200) {
                                    this.dialogFormVisible = false
                                    this.getEmpList()
                                    this.$message.success("员工新增成功")
                                } else {
                                    this.$message.error("员工新增失败，出现未知错误")
                                }
                            }).catch(() =>{
                                debugger
                                this.$message.error("员工新增失败，出现未知错误")
                            })
                        }
                    }
                })
            },
            // 检查手机号
            isCellPhone (val) {
                if (!/^1(3|4|5|6|7|8)\d{9}$/.test(val)) {
                    return false
                } else {
                    return true
                }
            }
        },
        created() {
            this.getPostList()
            this.getEmpList()
        }
    }
</script>

<style scoped>
	/*让弹出框在垂直方向保持居中*/
	/deep/ .el-dialog {
		display: flex;
		flex-direction: column;
		margin: 0 !important;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		max-height: calc(100% - 30px);
		max-width: calc(100% - 30px);
	}
	/*让弹出框在垂直方向保持居中*/
	/deep/ .el-dialog .el-dialog__body {
		flex: 1;
		overflow: auto;
	}
</style>
