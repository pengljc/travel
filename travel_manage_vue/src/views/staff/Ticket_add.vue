<template>
	<div>
		<el-row>
			<el-col :span="16">
				出差情况登记
			</el-col>
			<el-col :span="8">
				<el-button type="primary" @click="save">保存</el-button>
				<el-button type="primary" @click="submit">提交</el-button>
			</el-col>
		</el-row>
		<hr>
		<el-row>
			<el-col :span="12">
				<span>基本信息</span>
				<el-form ref="form" :model="ticketForm" label-width="100px"  size="mini">
					<el-form-item label="员工姓名">
						<el-input style="width: 400px;color: black" :value="ename" disabled></el-input>
					</el-form-item>
					<el-form-item label="出差类型">
						<el-input style="width: 400px;color: black" :value="typeLabel" disabled></el-input>
					</el-form-item>

					<el-form-item label="出发时间">
						<el-date-picker type="datetime"
						                placeholder="出发日期"
						                v-model="ticketForm.leaveTime"
						                format="yyyy-MM-dd HH:mm"
						                value-format="yyyy-MM-dd HH:mm"
						                @change="timeChange">
						</el-date-picker>
					</el-form-item>
					<el-form-item label="出发地点">
						<el-input style="width: 400px" v-model="ticketForm.leavePlace" :disabled="isLeave"></el-input>
					</el-form-item>
					<el-form-item label="到达时间">
						<el-date-picker type="datetime"
						                placeholder="到达日期"
						                v-model="ticketForm.arriveTime"
						                :picker-options="endTime"
						                format="yyyy-MM-dd HH:mm"
						                value-format="yyyy-MM-dd HH:mm"
						                @change="timeChange">
						</el-date-picker>
						<label style="padding: 0 10px 0 20px" >总耗时</label>
						<el-input style="width: 108px" :value="timeInterval" disabled></el-input>
					</el-form-item>
					<el-form-item  label="到达地点">
						<el-input style="width: 400px" v-model="ticketForm.arrivePlace" :disabled="isArrive"></el-input>
					</el-form-item>



					<el-form-item  label="交通工具">
						<!--clearable可清空-->
						<!--filterable可过滤，可查询-->
						<el-select style="width: 400px" v-model="ticketForm.vno" placeholder="交通工具"
						           clearable filterable>
							<el-option v-for="veh in vehList" :label="veh.vname"
							           :value="veh.vno" ></el-option>
						</el-select>
					</el-form-item>
					<el-form-item  label="车票金额">
						<el-input style="width: 400px" v-model="ticketForm.ticketMoney"></el-input>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="12">
				<span>车票照片</span>
			</el-col>
		</el-row>
	</div>
</template>

<script>
    export default {
        name: "Ticket_add",
        data() {
            return {
                //员工姓名
                ename: '',
	            //目的地文本框是否可用
                isArrive:false,
                //出发地文本框是否可用
                isLeave:false,

                ticketForm: {
                    //员工号
                    eno: '',
                    //交通工具类型号
                    vno: '',
                    //出差类型
                    type: '',
                    leaveTime: '',
                    leavePlace: '',
                    arriveTime: '',
                    arrivePlace: '',
                    ticketMoney: '',
                },
	            //出差类型文本
	            typeLabel:'',
	            //交通方式列表
	            vehList:[],
	            //时间间隔
                timeInterval:'',
	            //到达时间中，所有比出发时间早的时间都不可选
                endTime:{
                    disabledDate:time => {
                        // console.log(time.getTime());
                        // console.log(Date.parse(this.ticketForm.leaveTime));
                        return time.getTime() < Date.parse(this.ticketForm.leaveTime)
                    }
	            }
            };
        },
        methods: {
            onSubmit() {
                console.log('submit!');
            },
	        //得到所有交通方式
            getAllVehicle(){
                this.$http.get('/vehicles').then((res) => {
                    this.vehList = res.data.data
                    console.log(this.vehList);
                }).catch(() => {
                    this.message.error("服务器错误，数据获取异常")
                })
            },
	        //得到最新一条车票信息
            getNewTicket(){
                this.$http.get('/tickets/' + this.ticketForm.eno).then((res) => {
                    //如果最新一条车票类型为出差，那么此时回显返回，反之亦然
	                //0 出差  1 返回
	                if (res.data.data.type == 0) {
	                    this.ticketForm.type = 1;
						this.typeLabel = '返回'
		                //目的地必为济南
		                this.ticketForm.arrivePlace = '济南'
		                this.isArrive = true
	                } else {
	                    //type = 1 或者 无记录（没出差过） 设置出差
                        this.ticketForm.type = 0;
                        this.typeLabel = '出差'
		                //出发地必为济南
		                this.ticketForm.leavePlace = '济南'
		                this.isLeave = true
	                }
	            }).catch(() => {
	                this.message.error("服务器错误，数据获取异常")
                })
            },
	        //时间改变的操作
            timeChange(){
                //时间栏为‘’时，清空时间间隔框
	            //时间栏为 null 时，也清空，用于点了时间框的'x'时使用
                if (this.ticketForm.arriveTime == '' || this.ticketForm.leaveTime ==''
                    || this.ticketForm.arriveTime == null || this.ticketForm.leaveTime ==null) {
                    this.timeInterval = ''
                    return
                }
/*                console.log(this.ticketForm.arriveTime);
                console.log(this.ticketForm.leaveTime);*/
                this.dateDifference(this.ticketForm.arriveTime, this.ticketForm.leaveTime)

            },
	        //计算两个时间相差时间间隔
            dateDifference(sDate1, sDate2) {
                let dateSpan,tempDate,iDays,iHours,iMinutes
                sDate1 = Date.parse(sDate1)
                // console.log('sDate1 =>' + sDate1);
                sDate2 = Date.parse(sDate2)
                // console.log('sDate2 =>' + sDate2);
                dateSpan = sDate2 - sDate1
                dateSpan = Math.abs(dateSpan)
	            iMinutes = Math.floor(dateSpan / (60 * 1000))
                iDays = Math.floor(dateSpan / (24 * 3600 * 1000))
	            iHours = Math.floor(dateSpan / (3600 * 1000))
	            iHours = iHours - iDays * 24
	            iMinutes = iMinutes - iDays * 60 * 24 - iHours * 60
	            this.timeInterval = iDays + '天' + iHours + '时' + iMinutes + '分'
            },
	        save() {

	        },
	        submit() {

	        }
        },
        created() {
            this.ename = sessionStorage.getItem("ename")
            this.ticketForm.eno = sessionStorage.getItem("eno")
	        this.getNewTicket();
            this.getAllVehicle();
        },

    }
</script>

<style scoped>
	.el-input.is-disabled /deep/ .el-input__inner {
		color: #606266;
		background-color: white;
	}
</style>
