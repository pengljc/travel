<template>
	<div>
		<el-row>
			<el-col :span="16">
				<div>
					<!--员工姓名和部门信息的查询条件及查询按钮-->
					<el-form :inline="true" :model="queryMap">
						<el-form-item label="出发地点">
							<el-input v-model="queryMap.leavePlace" placeholder="出发地点"></el-input>
						</el-form-item>
						<el-form-item label="到达地点">
							<el-input v-model="queryMap.arrivePlace" placeholder="到达地点"></el-input>
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
						<el-button type="primary" icon="el-icon-plus" @click="openAddViews">新增</el-button>
						<el-button type="primary" icon="el-icon-delete" @click="deleteEmp">删除</el-button>
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
				<!--prop的值对应的是map中的key值-->
				<el-table-column
						type="index"
						label="序号"
						width="50"
						align="center">
				</el-table-column>
<!--				<el-table-column
						prop="tno"
						label="车票号"
						align="center"
						width="100"
						sortable>
				</el-table-column>-->
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
				<el-table-column
						prop="photo"
						label="照片"
						align="center"
						:formatter="formatPhoto">
				</el-table-column>
				<el-table-column
						prop="description"
						label="出差说明"
						width="300"
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
	</div>
</template>

<script>
    export default {
        name: "Ticket",
        data() {
            return {
                queryMap: {
                    eno:'',
                    leavePlace: '',
                    arrivePlace: '',
	                page:1,
	                limit:5
                },
                ticketList:[],
                //选中的记录数
                checkedData:[],
	            total:0,
            }
        },
	    methods:{
            formatPhoto: function (row, column, cellValue){
                if (cellValue != null) {
                    return "有"
                } else {
                    return "无"
                }
            },
            formatType:function (row, column, cellValue){
                if (cellValue == 0) {
                    return "出差"
                } else if (cellValue == 1) {
                    return "返回"
                }
            },
		    getTicketList(){
                // console.log(this.queryMap);
                this.$http.get('/tickets',{
                    params:this.queryMap
                }).then((res) => {
                    this.ticketList = res.data.data
	                this.total = res.data.total
                }).catch(() =>{
                    this.message.error("服务器出错")
                })
		    },
		    /*查询按钮触发的事件*/
            query() {
                this.queryMap.page = 1
                this.getTicketList()
            },
            /*当页面发生变化时触发这个事件*/
            handleCurrentChange(val) {
                /*val代表当前页码*/
                this.queryMap.page = val
                //重新请求后台
                this.getTicketList()
            },
            /*当每页显示条数发生变化时触发*/
            handleSizeChange(val) {
                this.queryMap.limit = val
                this.queryMap.page = 1
                this.getTicketList()
            },
            /*表格选中项发生变化时触发，就可以通过该方法得到当前的选中记录*/
            selectChange (val) {
                //console.log(val);
                this.checkedData = val
            },
		    //批量删除车票
            deleteEmp() {
                if (this.checkedData.length == 0) {
                    this.$message.warning("请选择要删除的记录")
                    return
                }

                this.$confirm('此操作将删除所选车票, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //获取选中的车票编号，放到数组中
                    let tnos = []
					//获取选中的车票的图片url
	                let delImgUrls = []

                    for (let i = 0;i < this.checkedData.length ; i++) {
                        tnos.push(this.checkedData[i].tno)
                        delImgUrls.push(this.checkedData[i].photo)
                    }

                    //调用后台
                    this.$http.delete('/tickets/',{data:tnos}).then(res => {
                        if (res.data.state === 200) {
                            this.getTicketList()
                            this.$message.success("删除成功")
	                        //进行图片的删除
                            this.$http.delete('/files/', {data: delImgUrls})
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
                    path:'/ticket/add'
                })
            },
		    //打开编辑车票界面
            openEditViews() {
                //跳转页面
                this.$router.push({
                    path:'/ticket/edit'
                })
            },
            /*表格选中项发生变化时触发，就可以通过该方法得到当前的选中记录*/
            selectChange (val) {
                // console.log(val[0].tno);
                this.checkedData = val
            },
		    //打开编辑界面
            openEditViews() {
                /*判断用户是否只勾选了一条数据*/
                if (this.checkedData.length > 1 || this.checkedData.length == 0) {
                    this.$message.warning("请选择一条车票信息进行编辑")
                    return
                }
                //跳转页面
                this.$router.push({
                    path:'/ticket/edit/' + this.checkedData[0].tno
                })
            }
	    },
	    created() {
            this.queryMap.eno = sessionStorage.getItem("eno")
            this.getTicketList()
        }
    }
</script>

<style scoped>

</style>
