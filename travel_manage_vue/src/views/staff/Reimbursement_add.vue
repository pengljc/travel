<template>
	<div>
		<el-row>
			<el-col :span="21">
				差旅报销
			</el-col>
			<el-col :span="2" style="float: right">
				<el-button type="primary" @click="submit">提交</el-button>
			</el-col>
		</el-row>
		<hr>
		<el-form ref="billForm" :model="billForm" label-width="100px" size="mini" :rules="rules">
			<el-form-item style="margin: 3px">
				<span style="display:inline-block;width: 80px;margin: 0 10px 0 0px" align="right">单据序号</span>
				<el-input style="width: 200px;color: black" :value="billForm.bno" disabled></el-input>
				<span style="display:inline-block;width:80px;margin: 0 10px 0 80px" align="right">报销人</span>
				<el-input style="width: 300px;color: black" :value="billForm.ename" disabled></el-input>
			</el-form-item>
			<el-form-item style="margin: 3px">
				<span style="display:inline-block;width: 80px;margin: 0 10px 0 0px" align="right">出发时间</span>
				<el-input style="width: 200px;color: black" :value="billForm.startTime" disabled></el-input>
				<span style="display:inline-block;width:80px;margin: 0 10px 0 80px" align="right">返回时间</span>
				<el-input style="width: 300px;color: black" :value="billForm.endTime" disabled></el-input>
			</el-form-item>
			<el-form-item style="margin: 3px">
				<span style="display:inline-block;width: 80px;margin: 0 10px 0 0px" align="right">天数</span>
				<el-input style="width: 200px;color: black" :value="billForm.days" disabled></el-input>
				<span style="display:inline-block;width:80px;margin: 0 10px 0 80px" align="right">报销金额</span>
				<el-input style="width: 100px;color: black" :value="billForm.money" disabled></el-input>
				<span style="display:inline-block;width:40px;margin: 0 10px 0 0px" align="right">大写</span>
				<el-input style="width: 150px;color: black" :value="chineseStr" disabled></el-input>
			</el-form-item>
			<el-form-item style="margin: 3px" prop="description">
				<span style="display:inline-block;width: 80px;margin: 0 10px 0 0px" align="right">出差原因</span>
				<el-input style="width: 200px;color: black" v-model="billForm.description"></el-input>
				<span style="display:inline-block;width:80px;margin: 0 10px 0 80px" align="right">报销时间</span>
				<el-input style="width: 300px;color: black" :value="billForm.applicationTime" disabled></el-input>
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
				<el-button type="primary" icon="el-icon-plus" @click="moneyCal">计算补助</el-button>
			</el-button-group>
		</div>
		<!--新增或者编辑的弹出框-->
		<el-dialog :title="formTitle" :visible.sync="dialogFormVisible" width="70%" destroy-on-close="true">
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
    import moment from "moment";

    export default {
        name: "Reimbursement_add",
        data() {
            return {
                rules: {
                    description: [
                        {required: true, message: '出差原因不可为空', trigger: ['blur', 'change']}
                    ]
                },
                //金额大写
                chineseStr: '',
	            //报销页面的搜索和分页
                pageMap: {
                    eno: '',
                    page: 1,
                    limit: 5,
                    //作为标记，告诉数据库查询的是bno为null的所有车票信息
                    bno: 1
                },
	            //车票列表，显示没有关联票据的所有车票
                ticketList: [],
	            //车票列表，显示没有关联票据的车票，形成闭合回路，显示在新增页面
                ticketSelList: [],
	            //选中，在新增页面的弹出框中起作用
                checkedData: [],
	            //没有关联票据的车票的数量，进行分页使用
                total: 0,
	            //要新增的车票号数组
                tnos: [],
	            //要删除的车票号的数组
                delTnos:[],
	            //新增/编辑 报销页面中的表单
                billForm: {
                    bno: this.$route.params.bno,
                    ename: '',
                    eno: '',
                    startTime: '',
                    endTime: '',
                    days: '',
                    money: '',
                    description: '',
                    applicationTime: '',

                },
                /*弹出框的标题*/
                formTitle: '',
                /*控制弹出框隐藏或者显示的布尔*/
                dialogFormVisible: false,
            }
        },
        methods: {
            //从后台查询选中的报销信息
            getSelBill() {
                this.$http.get('/bills/bno/' + this.billForm.bno).then((res) => {
                    this.billForm = res.data.data
                    //把金额转成大写
                    this.menoyToUppercase()
                })
                //根据单号查询所有车票
                this.$http.get('/tickets/bno/' + this.billForm.bno).then((res) => {
                    this.ticketSelList = []
                    this.ticketSelList = res.data.data
                    //把车票号push到tnos中，为了以后的编辑操作
	                this.delTnos = []
	                //防止用户不改变关联车票直接提交
	                this.tnos = this.delTnos
                    for (let i = 0; i < this.ticketSelList.length; i++) {
                        this.delTnos.push(this.ticketSelList[i].tno)
                    }
                })
            },
            /*打开管理车票的弹出框*/
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
                if (this.$refs['empForm'] !== undefined) {
                    this.$refs['empForm'].resetFields()
                }
            },
	        //车票列表，得到所有没有关联票据的车票信息·
            getTicketList() {
                // console.log(this.queryMap);
                this.$http.get('/tickets', {
                    params: this.pageMap
                }).then((res) => {
                    this.ticketList = res.data.data
                    this.total = res.data.total
                }).catch(() => {
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
	        //格式化，对后台取出出差类型值的0和1进行格式化
            formatType: function (row, column, cellValue) {
                if (cellValue == 0) {
                    return "出差"
                } else if (cellValue == 1) {
                    return "返回"
                }
            },
            /*表格选中项发生变化时触发，就可以通过该方法得到当前的选中记录*/
            selectChange(val) {
                //console.log(val);
                this.checkedData = val
            },
            //进行新增还是编辑逻辑的判断
            submit() {
                if (this.billForm.bno !== undefined) {
                    this.edit()
                } else {
                    this.add()
                }
            },
	        //编辑的提交动作
            edit() {
                if (this.billForm.days == null || this.billForm.days == '') {
                    this.$message.warning("请关联车票")
                    return
                }
                if (this.billForm.money == null || this.billForm.money == '') {
                    this.$message.warning("请计算金额")
                    return
                }

                this.$refs['billForm'].validate(valid => {
                    //valid代表校验结果，是布尔类型
                    if (valid) {
                        this.$http.put('/bills',{"bill": this.billForm, "tnos": this.tnos,"delTnos" : this.delTnos}).
                        then((res) => {
                            if (res.data.state === 200) {
                                this.$message.success("编辑成功")
                                //回到报销界面
                                this.$router.push({
                                    path: '/reimbursement'
                                })
                            } else {
                                //控制台打印错误码和错误信息
                                console.log(res.data.state);
                                console.log(res.data.message);
                                this.$message.error("报销信息编辑出现未知错误，请稍后重试")
                            }
                        }).catch(() => {
                            this.$message.error("报销信息编辑出现未知错误，请稍后重试")
                        })
                    }
                })
            },
	        //新增的提交动作
            add() {
                if (this.billForm.days == null || this.billForm.days == '') {
                    this.$message.warning("请关联车票")
                    return
                }
                if (this.billForm.money == null || this.billForm.money == '') {
                    this.$message.warning("请计算金额")
                    return
                }

                this.$refs['billForm'].validate(valid => {
                    //valid代表校验结果，是布尔类型
                    if (valid) {
                        this.$http.post('/bills', {"bill": this.billForm, "tnos": this.tnos}).then((res) => {
                            //新增成功
                            if (res.data.state === 200) {
                                this.$message.success("报销信息新增成功")
                                //回到报销界面
                                this.$router.push({
                                    path: '/reimbursement'
                                })
                            } else {
                                //控制台打印错误码和错误信息
                                console.log(res.data.state);
                                console.log(res.data.message);
                                this.$message.error("报销信息新增出现未知错误，请稍后重试")
                            }
                        }).catch(() => {
                            this.$message.error("报销信息新增出现未知错误，请稍后重试")
                        })
                    }
                })
            },
	        //弹出框中的确认按钮，用于对选中的车票信息进行判定
            choice() {
                console.log(this.checkedData);
                if (this.checkedData.length == 0) {
                    this.$message.warning("请选择要添加的旅程信息")
                    return
                }
                if (this.checkedData.length != 2) {
                    this.$message.warning("旅程信息必须达到闭环")
                    return
                }
                if (!(this.checkedData[0].arrivePlace === this.checkedData[1].leavePlace
                    && this.checkedData[0].leavePlace === this.checkedData[1].arrivePlace)) {
                    this.$message.warning("旅程信息必须达到闭环")
                    return
                }
                //两条信息不能都为出差或者返回
	            if (this.checkedData[0].type === this.checkedData[1].type) {
                    this.$message.warning("旅程信息必须达到闭环")
                    return
	            }
                //如果是出差，那么到达时间必须比返回的出发时间早
                if (this.checkedData[0].type === 0) {
                    if (this.checkedData[0].arriveTime >= this.checkedData[1].leaveTime) {
                        this.$message.warning("旅程信息必须达到闭环")
                        return
                    }
                    this.billForm.startTime = this.checkedData[0].leaveTime
                    this.billForm.endTime = this.checkedData[1].arriveTime
                } else {
                    if (this.checkedData[1].arriveTime >= this.checkedData[0].leaveTime) {
                        this.$message.warning("旅程信息必须达到闭环")
                        return
                    }
                    this.billForm.startTime = this.checkedData[1].leaveTime
                    this.billForm.endTime = this.checkedData[0].arriveTime
                }
                //存入车票号
	            //清空新增的车票组
	            this.tnos = []
                this.tnos.push(this.checkedData[0].tno)
                this.tnos.push(this.checkedData[1].tno)
                //清空ticketSelList
                this.ticketSelList = []
                //存入新选择的内容
                this.ticketSelList.push(this.checkedData[0])
                this.ticketSelList.push(this.checkedData[1])

                //计算时间差，出发时间12点之前一天，出发时间12点之后半天
                //到达时间12点之前半天，到达时间12点之后一天
                this.timeCal()
	            //清空金额
	            this.billForm.money = ''
	            this.chineseStr = ''
                this.dialogFormVisible = false
            },
	        //清空按钮，清空车票信息
            cleanList() {
                this.ticketSelList = []
                this.billForm.startTime = ''
                this.billForm.endTime = ''
                this.billForm.money = ''
                this.billForm.days = ''
                this.chineseStr = ''
            },
            //计算时间差，出发时间12点之前一天，出发时间12点之后半天
            //到达时间12点之前半天，到达时间12点之后一天
            timeCal() {
                let sDate, eDate, iDays, sHour, eHour
                //先计算天数差距
                sDate = Date.parse(this.billForm.startTime)
                eDate = Date.parse(this.billForm.endTime)
                //忽略掉时分秒的影响
                sDate = Math.floor(sDate / (24 * 3600 * 1000))
                eDate = Math.floor(eDate / (24 * 3600 * 1000))
                //日期差
                iDays = eDate - sDate

                //计算半天和一天
                //全为半天，则相差时间为iDays，如果有一天则加0.5天
                sHour = new Date(this.billForm.startTime).getHours();
                if (sHour < 12) {
                    iDays += 0.5
                }
                eHour = new Date(this.billForm.endTime).getHours();
                if (eHour > 12) {
                    iDays += 0.5
                }
                this.billForm.days = iDays
            },
            //出差金额计算，车票加补贴
            moneyCal() {
                this.billForm.money = this.billForm.days * 200 +
                    this.checkedData[0].ticketMoney + this.checkedData[1].ticketMoney
                //把金额转成大写
                this.menoyToUppercase()
            },
            //金额转大写
            menoyToUppercase() {
                var money = this.billForm.money
                var cnNums = new Array('零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖');	 //汉字的数字
                var cnIntRadice = new Array('', '拾', '佰', '仟'); //基本单位
                var cnIntUnits = new Array('', '万', '亿', '兆');  //对应整数部分扩展单位
                var cnDecUnits = new Array('角', '分', '毫', '厘'); //对应小数部分单位
                var cnInteger = '整';	//整数金额时后面跟的字符
                var cnIntLast = '元';	//整数完以后的单位
                //最大处理的数字
                var maxNum = 999999999999999.9999;
                var integerNum;	 //金额整数部分
                var decimalNum;	 //金额小数部分
                //输出的中文金额字符串
                var chineseStr = '';
                var parts;		//分离金额后用的数组，预定义
                if (money == '') {
                    return '';
                }

                money = parseFloat(money);
                if (money >= maxNum) {
                    //超出最大处理数字
                    return '超出最大处理数字';
                }
                if (money == 0) {
                    chineseStr = cnNums[0] + cnIntLast + cnInteger;
                    return chineseStr;
                }

                //四舍五入保留两位小数,转换为字符串
                money = Math.round(money * 100).toString();
                integerNum = money.substr(0, money.length - 2);
                decimalNum = money.substr(money.length - 2);

                //获取整型部分转换
                if (parseInt(integerNum, 10) > 0) {
                    var zeroCount = 0;
                    var IntLen = integerNum.length;
                    for (var i = 0; i < IntLen; i++) {
                        var n = integerNum.substr(i, 1);
                        var p = IntLen - i - 1;
                        var q = p / 4;
                        var m = p % 4;
                        if (n == '0') {
                            zeroCount++;
                        } else {
                            if (zeroCount > 0) {
                                chineseStr += cnNums[0];
                            }
                            //归零
                            zeroCount = 0;
                            chineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
                        }
                        if (m == 0 && zeroCount < 4) {
                            chineseStr += cnIntUnits[q];
                        }
                    }
                    chineseStr += cnIntLast;
                }
                //小数部分
                if (decimalNum != '') {
                    var decLen = decimalNum.length;
                    for (var i = 0; i < decLen; i++) {
                        var n = decimalNum.substr(i, 1);
                        if (n != '0') {
                            chineseStr += cnNums[Number(n)] + cnDecUnits[i];
                        }
                    }
                }
                if (chineseStr == '') {
                    chineseStr += cnNums[0] + cnIntLast + cnInteger;
                } else if (decimalNum == '' || /^0*$/.test(decimalNum)) {
                    chineseStr += cnInteger;
                }
                this.chineseStr = chineseStr
            }
        },
        created() {
            //获取用户姓名
            this.billForm.ename = sessionStorage.getItem("ename")
            //获取用户工号
            this.billForm.eno = sessionStorage.getItem("eno")
            this.pageMap.eno = sessionStorage.getItem("eno")
	        //获取所有没关联报销的车票列表
            this.ticketList = this.getTicketList()
            //获取当前时间
            this.billForm.applicationTime = moment().format("yyyy-MM-DD HH:mm")
			//判断此时的页面执行的是新增还是编辑逻辑
            if (this.billForm.bno !== undefined) {
                //执行编辑页面逻辑
                //查询选中的报销信息
                this.getSelBill();
            }
        }
    }
</script>

<style scoped>
	/*设置disable的标签，的背景颜色和文字颜色*/
	.el-input.is-disabled /deep/ .el-input__inner {
		color: #606266;
		background-color: white;
	}

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
