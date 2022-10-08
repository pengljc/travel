<template>
	<div>
		<el-row>
			<el-col :span="19">
				差旅报销
			</el-col>
			<el-col :span="5" style="float: right">
				<el-button type="primary" @click="save">保存</el-button>
			</el-col>
		</el-row>
		<hr>
		<el-form ref="billForm" :model="billForm" label-width="100px" size="mini">
			<el-form-item style="margin: 3px">
				<span style="display:inline-block;width: 80px;margin: 0 10px 0 0px" align="right">单据序号</span>
				<el-input style="width: 200px;color: black" :value="billForm.bno" disabled></el-input>
				<span style="display:inline-block;width:80px;margin: 0 10px 0 80px" align="right">报销人</span>
				<el-input style="width: 200px;color: black" :value="billForm.ename" disabled></el-input>
			</el-form-item>
			<el-form-item style="margin: 3px">
				<span style="display:inline-block;width: 80px;margin: 0 10px 0 0px" align="right">出发时间</span>
				<el-input style="width: 200px;color: black" :value="billForm.startTime" disabled></el-input>
				<span style="display:inline-block;width:80px;margin: 0 10px 0 80px" align="right">返回时间</span>
				<el-input style="width: 200px;color: black" :value="billForm.endTime" disabled></el-input>
			</el-form-item>
			<el-form-item style="margin: 3px">
				<span style="display:inline-block;width: 80px;margin: 0 10px 0 0px" align="right">天数</span>
				<el-input style="width: 200px;color: black" :value="billForm.days" disabled></el-input>
				<span style="display:inline-block;width:80px;margin: 0 10px 0 80px" align="right">报销金额</span>
				<el-input style="width: 200px;color: black" :value="billForm.money" disabled></el-input>
			</el-form-item>
			<el-form-item style="margin: 3px">
				<span style="display:inline-block;width: 80px;margin: 0 10px 0 0px" align="right">出差原因</span>
				<el-input style="width: 200px;color: black" :value="billForm.description"></el-input>
				<span style="display:inline-block;width:80px;margin: 0 10px 0 80px" align="right">报销时间</span>
				<el-input style="width: 200px;color: black" :value="billForm.applicationTime" disabled></el-input>
			</el-form-item>
		</el-form>
		<el-row style="margin-top: 20px">
			<el-col :span="15">
				<span>差旅信息 </span><span style="color: orange;font-size: 13px">填写格式：济南 ——> 目的地...目的地 ——> 济南</span>
			</el-col>
			<el-col :span="7">
				<div style="float: right">
					<el-button-group>
						<el-button type="primary" icon="el-icon-plus" @click="openAddDialog">选择车票信息</el-button>
						<el-button type="primary" icon="el-icon-delete" @click="cleanList">清空</el-button>
					</el-button-group>
				</div>
			</el-col>
		</el-row>
		<div style="margin-top: 20px">
			<el-table
					:data="ticketSelList"
					border
					style="width: 100%;height: 380px"
					height="150"
					@selection-change="selectChange">
				<el-table-column
						type="index"
						label="序号"
						width="50"
						align="center">
				</el-table-column>
				<el-table-column
						prop="type"
						label="类型"
						align="center"
						:formatter="formatType">
				</el-table-column>
				<el-table-column
						prop="leavePlace"
						label="出发地点"
						align="center">
				</el-table-column>
				<el-table-column
						prop="arrivePlace"
						label="到达地点"
						align="center">
				</el-table-column>
				<el-table-column
						prop="vname"
						label="交通工具"
						align="center">
				</el-table-column>
				<el-table-column
						prop="leaveTime"
						label="出发时间"
						width="150"
						align="center"
						sortable>
				</el-table-column>
				<el-table-column
						prop="arriveTime"
						label="到达时间"
						align="center"
						width="150"
						sortable>
				</el-table-column>
				<el-table-column
						prop="ticketMoney"
						label="金额"
						align="center"
						sortable>
				</el-table-column>
			</el-table>
		</div>
		<div style="float: right;margin-top: 20px">
			<el-button-group>
				<el-button type="primary" icon="el-icon-plus" @click="openAddViews">计算补助</el-button>
			</el-button-group>
		</div>
		<!--新增或者编辑的弹出框-->
		<el-dialog :title="formTitle" :visible.sync="dialogFormVisible" width="70%" >
			<div style="float: right;margin-bottom: 20px">
				<el-button type="primary" @click="choice">确定</el-button>
			</div>
			<div>
				<el-table
						:data="ticketList"
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
					<el-table-column
							prop="type"
							label="类型"
							align="center"
							:formatter="formatType">
					</el-table-column>
					<el-table-column
							prop="leavePlace"
							label="出发地点"
							align="center">
					</el-table-column>
					<el-table-column
							prop="arrivePlace"
							label="到达地点"
							align="center">
					</el-table-column>
					<el-table-column
							prop="vname"
							label="交通工具"
							align="center">
					</el-table-column>
					<el-table-column
							prop="leaveTime"
							label="出发时间"
							width="150"
							align="center"
							sortable>
					</el-table-column>
					<el-table-column
							prop="arriveTime"
							label="到达时间"
							align="center"
							width="150"
							sortable>
					</el-table-column>
					<el-table-column
							prop="ticketMoney"
							label="金额"
							align="center"
							sortable>
					</el-table-column>
				</el-table>
			</div>
			<el-pagination
					background
					layout="prev, pager, next,sizes,total"
					:total="total"
					:page-size="pageMap.limit"
					:current-page="pageMap.page"
					:page-sizes='[5,10,15,20]'
					@current-change="handleCurrentChange"
					@size-change="handleSizeChange">
			</el-pagination>
		</el-dialog>
	</div>
</template>

<script>
    export default {
        name: "Reimbursement_add",
        data() {
            return {
                pageMap:{
                    eno:'',
                    page:1,
	                limit:5,
	                //作为标记，告诉数据库查询的是bno为null的所有车票信息
	                bno:1
                },
	            ticketList:[],
	            ticketSelList:[],
                checkedData:[],
                total:0,
                billForm: {
                    ename: '',
                    eno: '',
                    startTime: '',
                    endTime: '',
                    days: '',
                    money: '',
                    description: '',
                    applicationTime: ''
                },
                /*弹出框的标题*/
                formTitle: '',
                /*控制弹出框隐藏或者显示的布尔*/
                dialogFormVisible: false,
            }
        },
        methods: {
            /*打开新增员工的弹出框*/
            openAddDialog() {
                //打开弹出框
                this.dialogFormVisible = true
                //设置弹出框的标题
                this.formTitle = "旅程信息选择"
                //信息清空
                this.empForm = {
                    empno: '',
                    ename: '',
                    sex: '男',
                    job: '',
                    hiredate: '',
                    deptno: ''
                }
                //校验提示信息的清空
                //第一次打开弹框时，dialog这个元素还没有完全渲染到页面中
                //this.refs获取到的节点是undefined
                if (this.$refs['empForm'] != undefined) {
                    this.$refs['empForm'].resetFields()
                }
            },
            getTicketList(){
                // console.log(this.queryMap);
                this.$http.get('/tickets',{
                    params:this.pageMap
                }).then((res) => {
                    this.ticketList = res.data.data
                    this.total = res.data.total
                }).catch(() =>{
                    this.message.error("服务器出错")
                })
            },
            /*当页面发生变化时触发这个事件*/
            handleCurrentChange(val) {
                /*val代表当前页码*/
                this.pageMap.page = val
                //重新请求后台
                this.getTicketList()
            },
            /*当每页显示条数发生变化时触发*/
            handleSizeChange(val) {
                this.pageMap.limit = val
                this.pageMap.page = 1
                this.getTicketList()
            },
            formatType:function (row, column, cellValue){
                if (cellValue == 0) {
                    return "出差"
                } else if (cellValue == 1) {
                    return "返回"
                }
            },
            /*表格选中项发生变化时触发，就可以通过该方法得到当前的选中记录*/
            selectChange (val) {
                //console.log(val);
                this.checkedData = val
            },
            choice() {
                if (this.checkedData.length == 0) {
                    this.$message.warning("请选择要添加的旅程信息")
                    return
                }
                if (this.checkedData.length != 2) {
                    this.$message.warning("旅程信息必须达到闭环")
                    return
                }
                if (!(this.checkedData[0].arrivePlace === this.checkedData[1].leavePlace
	                && this.checkedData[0].leavePlace === this.checkedData[1].arrivePlace
                    && this.checkedData[0].arriveTime < this.checkedData[1].leaveTime)) {
                    this.$message.warning("旅程信息必须达到闭环")
                    return
                }

                this.ticketSelList = []
                this.ticketSelList.push(this.checkedData[0])
                this.ticketSelList.push(this.checkedData[1])
	            this.billForm.startTime = this.checkedData[0].leaveTime
	            this.billForm.endTime = this.checkedData[1].arriveTime
				this.dialogFormVisible = false
            },
            cleanList() {
                this.ticketSelList = []
            }
        },
        created() {
            //获取用户姓名
            this.billForm.ename = sessionStorage.getItem("ename")
            //获取用户工号
            this.billForm.eno = sessionStorage.getItem("eno")
            this.pageMap.eno = sessionStorage.getItem("eno")
	        this.ticketList = this.getTicketList()
        }
    }
</script>

<style scoped>
	/*设置disable的标签，的背景颜色和文字颜色*/
	.el-input.is-disabled /deep/ .el-input__inner {
		color: #606266;
		background-color: white;
	}

	/deep/ .el-dialog{
		display: flex;
		flex-direction: column;
		margin:0 !important;
		position:absolute;
		top:50%;
		left:50%;
		transform:translate(-50%,-50%);
		max-height:calc(100% - 30px);
		max-width:calc(100% - 30px);
	}
	/deep/ .el-dialog .el-dialog__body{
		flex:1;
		overflow: auto;
	}

</style>
