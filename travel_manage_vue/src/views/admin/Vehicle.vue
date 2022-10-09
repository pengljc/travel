<template>
	<div>
		<el-row>
			<el-col :span="17">交通方式管理</el-col>
			<el-col :span="7">
				<div style="float: right">
					<el-button-group style="margin-bottom: 20px">
						<el-button type="primary" icon="el-icon-plus" @click="openAddDialog">新增</el-button>
						<el-button type="primary" icon="el-icon-delete" @click="deleteVe">删除</el-button>
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
					:data="veList"
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
						prop="vno"
						label="交通工具编号"
						align="center"
						sortable>
				</el-table-column>
				<el-table-column
						prop="vname"
						label="交通工具"
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
		<el-dialog :title="formTitle" :visible.sync="dialogFormVisible">
			<el-form :model="veForm" :rules="rules" ref="veForm" style="margin-left: 120px">
				<el-form-item label="交通工具"  prop="vname">
					<el-input style="width: 300px" v-model="veForm.vname" autocomplete="off"></el-input>
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
        name: "vehicle",
        data() {
            return {
                queryMap: {
                    page: 1,
                    limit: 5
                },
                veList: [],
                //选中的记录数
                checkedData: [],
                total: 0,
                /*弹出框的标题*/
                formTitle: '',
                /*控制弹出框隐藏或者显示的布尔*/
                dialogFormVisible: false,
                veForm: {
                    vno: '',
                    vname: ''
                },
                /*表单验证对象*/
                rules: {
                    vname: [
                        {required: true, message: '请输入交通工具', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            getVeList() {
                this.$http.get('/vehicles/all', {
                    params: this.queryMap
                }).then((res) => {
                    this.veList = res.data.data
                    this.total = res.data.total
                }).catch(() => {
                    this.message.error("服务器出错")
                })
            },
            /*当页面发生变化时触发这个事件*/
            handleCurrentChange(val) {
                /*val代表当前页码*/
                this.queryMap.page = val
                //重新请求后台
                this.getVeList()
            },
            /*当每页显示条数发生变化时触发*/
            handleSizeChange(val) {
                this.queryMap.limit = val
                this.queryMap.page = 1
                this.getVeList()
            },
            /*表格选中项发生变化时触发，就可以通过该方法得到当前的选中记录*/
            selectChange(val) {
                this.checkedData = val
            },
            openAddDialog() {
                //打开弹出框
                this.dialogFormVisible = true
                //设置弹出框的标题
                this.formTitle = "新增交通工具"
                //信息清空
                this.veForm = {
                    vno:'',
                    vname: ''
                }
                //校验提示信息的清空
                //第一次打开弹框时，dialog这个元素还没有完全渲染到页面中
                //this.refs获取到的节点是undefined
                if (this.$refs['veForm'] !== undefined) {
                    this.$refs['veForm'].resetFields()
                }
            },
            openEditDialog() {
                /*判断用户是否只勾选了一条数据*/
                if (this.checkedData.length > 1 || this.checkedData.length === 0) {
                    this.$message.warning("请选择一条员工信息进行修改")
                    return
                }

                let vno = this.checkedData[0].vno
                //调用后台获取详细信息
                this.$http.get('/vehicles/' + vno).then(res => {
                    /*返回的员工对象赋值给veForm对象*/

                    this.veForm = res.data.data
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
                this.$refs['veForm'].validate(valid => {
                    //valid代表校验结果，是布尔类型
                    if (valid) {
                        if (this.veForm.vno !== '') {
                            //编辑员工信息
                            this.$http.put('/vehicles', this.veForm).then(res => {
                                if (res.data.state === 200) {
                                    this.dialogFormVisible = false
                                    this.getVeList()
                                    this.$message.success("交通工具修改成功")
                                } else {
                                    this.$message.error("交通工具修改失败，出现未知错误")
                                }
                            }).catch(() => {
                                this.$message.error("交通工具修改失败，出现未知错误")
                            })
                        } else {
                            //新增交通方式
                            //调用后台，保存数据ve
                            this.$http.post('/vehicles', this.veForm).then((res) => {
                                if (res.data.state === 200) {
                                    this.dialogFormVisible = false
                                    this.getVeList()
                                    this.$message.success("交通工具新增成功")
                                } else {
                                    this.$message.error("交通工具新增失败，出现未知错误")
                                }
                            }).catch(() => {
                                debugger
                                this.$message.error("交通工具新增失败，出现未知错误")
                            })
                        }
                    }
                })
            },
	        //批量删除
            deleteVe() {
                if (this.checkedData.length === 0) {
                    this.$message.warning("请选择要删除的记录")
                    return
                }

                this.$confirm('此操作将删除所选交通工具, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //获取选中的车票编号，放到数组中
                    let vnos = []

                    for (let i = 0;i < this.checkedData.length ; i++) {
                        vnos.push(this.checkedData[i].vno)
                    }

                    //调用后台
                    this.$http.delete('/vehicles/',{data:vnos}).then(res => {
                        if (res.data.state === 200) {
                            this.getVeList()
                            this.$message.success("删除成功")
                        } else {
                            //控制台打印错误码和错误信息
                            console.log(res.data.state);
                            console.log(res.data.message);
                            this.$message.error("交通工具删除出现未知错误，请稍后重试")
                        }
                    }).catch(() => {
                        this.$message.error("交通工具删除出现未知错误，请稍后重试")
                    })
                })
            }
        },
        created() {
            this.getVeList()
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
