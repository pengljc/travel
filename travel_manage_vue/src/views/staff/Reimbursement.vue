<template>
	<div>
		<el-row>
			<el-col :span="17">
				<div>
					<!--单据序号和日期的查询及查询按钮-->
					<el-form :inline="true" :model="queryMap">
						<el-form-item label="单据序号">
							<el-input style="width: 100px" v-model="queryMap.bno" placeholder="单据序号"></el-input>
						</el-form-item>
						<el-form-item label="报销日期">
							<el-date-picker type="datetime"
							                placeholder="起始时间"
							                v-model="queryMap.stime"
							                format="yyyy-MM-dd HH:mm"
							                :picker-options="startTime"
							                value-format="yyyy-MM-dd HH:mm"
											style="width: 175px">
							</el-date-picker>
						</el-form-item>
						<el-form-item label="至">
							<el-date-picker type="datetime"
							                placeholder="截止时间"
							                v-model="queryMap.etime"
							                :picker-options="endTime"
							                format="yyyy-MM-dd HH:mm"
							                value-format="yyyy-MM-dd HH:mm"
							                style="width: 175px">
							</el-date-picker>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="query">查询</el-button>
						</el-form-item>
					</el-form>
				</div>
			</el-col>
<!--			<el-col :span="1">&nbsp;</el-col>-->
			<el-col :span="7">
				<div style="float: right">
					<el-button-group>
						<el-button type="primary" icon="el-icon-plus" @click="openAddViews">新增</el-button>
						<el-button type="primary" icon="el-icon-delete" @click="deleteBill">删除</el-button>
						<el-button type="primary" icon="el-icon-edit" @click="openEditViews">编辑</el-button>
					</el-button-group>
				</div>
			</el-col>
		</el-row>

		<!--数据表格-->
		<div>
			<!--员工信息的表格-->
			<!--data 代表表格要显示的数据-->
			<el-table
					:data="billList"
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
						prop="bno"
						label="单据序号"
						align="center"
						sortable>
				</el-table-column>
				<el-table-column
						prop="description"
						label="出差事由"
						align="center">
				</el-table-column>
				<el-table-column
						prop="money"
						label="报销金额"
						align="center"
						sortable>
				</el-table-column>
				<el-table-column
						prop="applicationTime"
						label="报销日期"
						align="center"
						sortable>
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
	</div>
</template>

<script>
    export default {
        name: "Reimbursement",
        data() {
            return {
                queryMap: {
                    bno: '',
                    stime:'',
	                etime:'',
                    page: 1,
                    limit: 5
                },
                billList: [],
                //选中的记录数
                checkedData: [],
                total: 0,
                //截止时间中，所有比开始时间早的时间都不可选
                endTime: {
                    disabledDate: time => {
                        // console.log(time.getTime());
                        // console.log(Date.parse(this.ticketForm.leaveTime));
                        return time.getTime() < Date.parse(this.ticketForm.leaveTime) - 23 * 3600 * 1000 - 59 * 60 * 1000
                    }
                },
                //开始时间中，所有比截止时间晚的时间都不可选
                startTime: {
                    disabledDate: time => {
                        return time.getTime() > Date.parse(this.ticketForm.arriveTime)
                    }
                }
            }
        },
        methods: {
	        //获取该用户下所有报销票据信息
            getBillList() {
                // console.log(this.queryMap);
                this.$http.get('/bills', {
                    params: this.queryMap
                }).then((res) => {
                    this.billList = res.data.data
                    this.total = res.data.total
                }).catch(() => {
                    this.message.error("服务器出错")
                })
            },
            /*查询按钮触发的事件*/
            query() {
                this.queryMap.page = 1
                this.getBillList()
            },
            /*当页面发生变化时触发这个事件*/
            handleCurrentChange(val) {
                /*val代表当前页码*/
                this.queryMap.page = val
                //重新请求后台
                this.getBillList()
            },
            /*当每页显示条数发生变化时触发*/
            handleSizeChange(val) {
                this.queryMap.limit = val
                this.queryMap.page = 1
                this.getBillList()
            },
            /*表格选中项发生变化时触发，就可以通过该方法得到当前的选中记录*/
            selectChange(val) {
                //console.log(val);
                this.checkedData = val
            },
            //批量删除报销单据
            deleteBill() {
                if (this.checkedData.length == 0) {
                    this.$message.warning("请选择要删除的单据")
                    return
                }

                this.$confirm('此操作将删除所选单据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //获取选中的车票编号，放到数组中
                    let bnos = []
                    for (let i = 0; i < this.checkedData.length; i++) {
                        bnos.push(this.checkedData[i].bno)
                    }

                    //调用后台
                    this.$http.delete('/bills/', {data: bnos}).then(res => {
                        if (res.data.state === 200) {
                            this.getBillList()
                            this.$message.success("删除成功")
                        } else {
                            //控制台打印错误码和错误信息
                            console.log(res.data.state);
                            console.log(res.data.message);
                            this.$message.error("车票删除出现未知错误，请稍后重试")
                        }
                    }).catch(() => {
                        this.$message.error("车票删除出现未知错误，请稍后重试")
                    })
                })
            },
            //打开新增车票界面
            openAddViews() {
                //跳转页面
                this.$router.push({
                    path: '/Reimbursement/add'
                })
            },
            /*表格选中项发生变化时触发，就可以通过该方法得到当前的选中记录*/
            selectChange(val) {
                // console.log(val[0].tno);
                this.checkedData = val
            },
            //打开编辑界面
            openEditViews() {
                /*判断用户是否只勾选了一条数据*/
                if (this.checkedData.length > 1 || this.checkedData.length == 0) {
                    this.$message.warning("请选择一条报销信息进行编辑")
                    return
                }
                //跳转编辑页面
                this.$router.push({
                    path: '/Reimbursement/edit/' + this.checkedData[0].bno
                })
            },

        },
        created() {
            //获取工号
            this.queryMap.eno = sessionStorage.getItem("eno")
	        //查询该工号下的所有报销票据列表
            this.getBillList()
        }
    }
</script>

<style scoped>

</style>
