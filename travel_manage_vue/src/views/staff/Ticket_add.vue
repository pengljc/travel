<template>
	<div>
		<el-row>
			<el-col :span="21">
				出差情况登记
			</el-col>
			<el-col :span="2">
				<el-button type="primary" @click="submit">提交</el-button>
			</el-col>
		</el-row>
		<hr>

		<el-row>
			<el-col :span="12">
				<span>基本信息</span>
				<el-form ref="ticketForm" :model="ticketForm" label-width="100px" size="mini" :rules="rules">
					<el-form-item label="员工姓名">
						<el-input style="width: 400px;color: black" :value="ename" disabled></el-input>
					</el-form-item>
					<el-form-item label="出差类型" prop="type">
						<el-select style="width: 400px" v-model="ticketForm.type" placeholder="请选择出差类型"
						           :disabled="isType" @change="typeChange">
							<el-option v-for="type in typeList" :label="type.tname" :value="type.type"
							           :key="type.type"></el-option>
						</el-select>
					</el-form-item>

					<el-form-item label="出发时间" prop="" prop="leaveTime">
						<el-date-picker type="datetime"
						                placeholder="出发日期"
						                v-model="ticketForm.leaveTime"
						                format="yyyy-MM-dd HH:mm"
						                :picker-options="startTime"
						                value-format="yyyy-MM-dd HH:mm"
						                @change="timeChange">
						</el-date-picker>
					</el-form-item>
					<el-form-item label="出发地点" prop="leavePlace">
						<el-input style="width: 400px" v-model="ticketForm.leavePlace" :disabled="isLeave"></el-input>
					</el-form-item>
					<el-form-item label="到达时间" prop="arriveTime">
						<el-date-picker type="datetime"
						                placeholder="到达日期"
						                v-model="ticketForm.arriveTime"
						                :picker-options="endTime"
						                format="yyyy-MM-dd HH:mm"
						                value-format="yyyy-MM-dd HH:mm"
						                @change="timeChange">
						</el-date-picker>
						<label style="padding: 0 10px 0 20px">总耗时</label>
						<el-input style="width: 108px" :value="timeInterval" disabled></el-input>
					</el-form-item>
					<el-form-item label="到达地点" prop="arrivePlace">
						<el-input style="width: 400px" v-model="ticketForm.arrivePlace" :disabled="isArrive"></el-input>
					</el-form-item>


					<el-form-item label="交通工具" prop="vno">
						<!--clearable可清空-->
						<!--filterable可过滤，可查询-->
						<el-select style="width: 400px" v-model="ticketForm.vno" placeholder="交通工具"
						           clearable filterable>
							<el-option v-for="veh in vehList" :label="veh.vname"
							           :value="veh.vno"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="车票金额" prop="ticketMoney">
						<el-input style="width: 400px" v-model="ticketForm.ticketMoney"></el-input>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="12">
				<span>车票上传</span>
				<el-row>
					<el-col :span="6" style="height: 350px;line-height: 350px">
						<span style="font-size: 12px"><span style="font-size: 16px">照片</span>（报销前添加）</span>
					</el-col>
					<el-col :span="18">
						<div>
							<div class="avatar" style="border: solid;color: gray">
								<img v-if="imgShow" :src="ticketForm.photo" class="avatar">
							</div>
							<el-row style="margin-top: 10px;">
								<el-col :span="4">&nbsp;</el-col>
								<el-col :span="6">
									<el-upload
											action="http://localhost:8086/files/upload"
											:show-file-list="false"
											:on-success="handleAvatarSuccess"
											:before-upload="beforeAvatarUpload"
											style="display: inline-block">
										<el-button size="small" type="primary">点击上传
										</el-button>
									</el-upload>
								</el-col>
								<el-col :span="4">&nbsp;</el-col>
								<el-col :span="6">
									<el-button size="small" type="primary" @click="deleteImg">删除图片</el-button>
								</el-col>
							</el-row>
						</div>
					</el-col>
				</el-row>
			</el-col>
		</el-row>
	</div>
</template>

<script>
    export default {
        name: "Ticket_add",
        data() {
            //出发时间的表单验证
            const validateStartTime = (rule, value, callback) => {
                //出发时间值为空时触发
                if (value === undefined || value == '') {
                    callback(new Error('出发时间不能为空'))
                } else {
                    // 如果本次出差类型不是出差
                    if (this.newArriveTime !== '') {
                        // 出发时间不能比上一次到达时间早
                        if (new Date(this.ticketForm.leaveTime) < new Date(this.newArriveTime).getTime()) {
                            callback(new Error('出发时间不能比上一次到达时间早'))
                        }
                    }
                    //出发时间不为空，但是到达时间为空时，不进行时间校验验证
                    if (this.ticketForm.arriveTime == '' || this.ticketForm.arriveTime == undefined) {
                        callback()
                    }
                    //出发时间不为空，到达时间不为空时
                    //如果出发时间比到达时间短，正确并清除到达时间的表单验证
                    if (new Date(this.ticketForm.leaveTime).getTime() <= new Date(this.ticketForm.arriveTime).getTime()) {
                        this.$refs['ticketForm'].clearValidate(['arriveTime'])
                        callback()
                    } else {
                        callback(new Error('出发时间必须小于到达时间'))
                    }
                }
            }
            //到达时间的表单验证
            const validateEndTime = (rule, value, callback) => {
                //到达时间值为空时触发
                if (value === undefined || value == '') {
                    callback(new Error('到达时间不能为空'))
                } else {
                    //到达时间不为空，但是出发时间为空时，不进行时间校验验证
                    if (this.ticketForm.leaveTime == '' || this.ticketForm.leaveTime == undefined) {
                        callback()
                    }
                    //到达时间不为空，出发时间不为空时
                    //如果出发时间比到达时间短，正确并清除到达时间的表单验证
                    if (new Date(this.ticketForm.leaveTime).getTime() >= new Date(this.ticketForm.arriveTime).getTime()) {
                        callback(new Error('到达时间必须大于出发时间'))
                    } else {
                        // 如果本次出差类型不是出差
                        if (this.newArriveTime !== '') {
                            // 出发时间不能比上一次到达时间早
                            if (new Date(this.ticketForm.leaveTime).getTime() < new Date(this.newArriveTime).getTime()) {
                                callback()
                            } else {
                                this.$refs['ticketForm'].clearValidate(['leaveTime'])
                                callback()
                            }
                        }
                        callback()
                    }
                }
            }
            //输入金额的表单验证
            const validateMoney = (rule, value, callback) => {
                let reg = /^[+-]?(0|([1-9]\d*))(\.\d+)?$/g;
                if (value === '') {
                    callback(new Error('请输入金额'));
                } else if (!reg.test(value)) {
                    callback(new Error('输入金额必须为数字'));
                } else {
                    callback();
                }
            }
            return {
                //最新一条信息的到达地
                newArrivePlace: '',
                //最新一条信息的到达时间
                newArriveTime: '',
                //员工姓名
                ename: '',
                //目的地是否可填写
                isArrive: '',
                //出发地是否可填写
                isLeave: '',
                //出差类型是否可填写
                isType: '',
                //上传框是否可用
                uploadDisabled: false,
                //车票表单对象
                ticketForm: {
                    //车票号
                    tno: this.$route.params.tno,
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
                    photo: ''
                },
                //需要删除的图片url
                delImgUrl: '',
                //是否渲染图片
                imgShow: true,
                /*表单验证对象*/
                rules: {
                    leaveTime: [
                        {required: true, validator: validateStartTime, trigger: ['blur', 'change']}
                    ],
                    arriveTime: [
                        {required: true, validator: validateEndTime, trigger: ['blur', 'change']}
                    ],
                    leavePlace: [
                        {required: true, message: '出发地不可为空', trigger: 'blur'}
                    ],
                    arrivePlace: [
                        {required: true, message: '目的地不可为空', trigger: 'blur'}
                    ],
                    vno: [
                        {required: true, message: '交通工具不可为空', trigger: ['blur', 'change']}
                    ],
                    type: [
                        {required: true, message: '出差类型不可为空', trigger: ['blur', 'change']}
                    ],
                    ticketMoney: [
                        {required: true, validator: validateMoney, trigger: 'blur'}
                    ]
                },
                //出差类型数组
                typeList: [],
                //交通方式列表
                vehList: [],
                //时间间隔
                timeInterval: '',
                //到达时间中，所有比出发时间早的时间都不可选
                endTime: {
                    disabledDate: time => {
                        // console.log(time.getTime());
                        // console.log(Date.parse(this.ticketForm.leaveTime));
                        return time.getTime() < Date.parse(this.ticketForm.leaveTime) - 23 * 3600 * 1000 - 59 * 60 * 1000
                    }
                },
                //出发时间中，所有比到达时间晚的时间都不可选
                startTime: {
                    disabledDate: time => {
                        console.log("newArriveTime ==>" + this.newArriveTime);
                        if (this.newArriveTime === '') {
                            return time.getTime() > Date.parse(this.ticketForm.arriveTime)
                        } else {
                            let sHours, sMinutes
                            sHours = new Date(this.newArriveTime).getHours();
                            sMinutes = new Date(this.newArriveTime).getMinutes();
                            //出发时间中，所有比最新一条到达时间早的时间都不可选
                            return time.getTime() < Date.parse(this.newArriveTime) - sHours * 3600 * 1000 -
                                sMinutes * 60 * 1000
                        }
                    }
                }
            };
        },
        methods: {
            //得到所有出差方式
            getTypeList() {
                this.$http.get('/types').then((res) => {
                    this.typeList = res.data.data
                }).catch(() => {
                    this.$message.error("服务器错误，数据获取异常")
                })
            },
            //得到所有交通方式
            getAllVehicle() {
                this.$http.get('/vehicles').then((res) => {
                    this.vehList = res.data.data
                    // console.log(this.vehList);
                }).catch(() => {
                    this.$message.error("服务器错误，数据获取异常")
                })
            },
            typeChange() {
                //如果出差类型为返回
                if (this.ticketForm.type === 1) {
                    //目的地必为济南
                    this.ticketForm.arrivePlace = '济南'
                    //出发地为最新票据的到达地
                    this.ticketForm.leavePlace = this.newArrivePlace
                    //出发地不可选
                    this.isLeave = true
                    //目的地不可选
                    this.isArrive = true
                } else if (this.ticketForm.type === 2) {
                    //出发地为最新票据的到达地
                    this.ticketForm.leavePlace = this.newArrivePlace
                    //出发地不可选
                    this.isLeave = true
                    //清空到达地
                    this.ticketForm.arrivePlace = ''
                    //目的地可选
                    this.isArrive = false
                }

            },
            //得到最新一条车票信息
            getNewTicket() {

                this.$http.get('/tickets/eno/' + this.ticketForm.eno).then((res) => {
                    //0 出差  1 返回  2中转
                    //如果最新一条车票类型为返回，那么默认是出差
                    if (res.data.data === null || res.data.data.type === 1) {
                        //无记录（没出差过）
                        //出差类型设置为出差
                        this.ticketForm.type = 0;
                        //出差类型不可选
                        this.isType = true
                        //出发地必为济南
                        this.ticketForm.leavePlace = '济南'
                        //出发地不可选
                        this.isLeave = true
                        //目的地可选
                        this.isArrive = false

                    } else {
                        //如果出差类型不是出差，那么记录最新一条信息的到达时间
                        this.newArriveTime = res.data.data.arriveTime
                        //记录最新一条信息的到达地
                        this.newArrivePlace = res.data.data.arrivePlace
                        //删除出差这个选项
                        this.typeList.shift()
                        //出差类型可选
                        this.isType = false

                    }

                }).catch(() => {
                    this.$message.error("服务器错误，数据获取异常")
                })
            },
            getCurrentTicket() {
                this.$http.get('/tickets/tno/' + this.ticketForm.tno).then((res) => {
                    this.ticketForm = res.data.data
                    //如果为出差或者中转
                    if (this.ticketForm.type !== 1) {
                        //目的地可填
                        this.isArrive = false
                    }
                    this.dateDifference(this.ticketForm.arriveTime, this.ticketForm.leaveTime)
                })
            },
            //时间改变的操作
            timeChange() {
                //时间栏为‘’时，清空时间间隔框
                //时间栏为 null 时，也清空，用于点了时间框的'x'时使用
                if (this.ticketForm.arriveTime == '' || this.ticketForm.leaveTime == ''
                    || this.ticketForm.arriveTime == null || this.ticketForm.leaveTime == null) {
                    this.timeInterval = ''
                    return
                }
                //调用计算时间差并显示
                this.dateDifference(this.ticketForm.arriveTime, this.ticketForm.leaveTime)

            },
            //计算两个时间相差时间间隔
            dateDifference(sDate1, sDate2) {
                let dateSpan, iDays, iHours, iMinutes
                //把格式化的时间变成毫秒数
                //返回从 1970-1-1 00:00:00 UTC 到出发时间的毫秒数
                sDate1 = Date.parse(sDate1)
                //把格式化的时间变成毫秒数
                //返回从 1970-1-1 00:00:00 UTC 到到达时间的毫秒数
                sDate2 = Date.parse(sDate2)
                //时间差，单位：毫秒
                dateSpan = sDate2 - sDate1
                //计算绝对值，避免算出负数结果
                dateSpan = Math.abs(dateSpan)
                //计算分钟差
                iMinutes = Math.floor(dateSpan / (60 * 1000))
                //计算日期差
                iDays = Math.floor(dateSpan / (24 * 3600 * 1000))
                //计算小时差
                iHours = Math.floor(dateSpan / (3600 * 1000))
                //真实小时 = 小时差 - 日期差 * 24
                iHours = iHours - iDays * 24
                //真实分钟 = 分钟差 - 日期差 * 24 * 60 - 真实小时 * 60
                iMinutes = iMinutes - iDays * 60 * 24 - iHours * 60
                //给变量赋值并格式化
                this.timeInterval = iDays + '天' + iHours + '时' + iMinutes + '分'
            },
            //点击提交按钮之后的事件
            //判断新增操作还是编辑操作
            submit() {
                if (this.ticketForm.tno === undefined) {
                    //执行新增页面逻辑
                    this.add();
                } else {
                    //执行编辑逻辑
                    this.edit();
                }
            },
            //编辑事件
            edit() {
                //表单验证
                this.$refs['ticketForm'].validate(valid => {
                    //valid代表校验结果，是布尔类型
                    if (valid) {
                        //向后台查询当前用户下的最新一条车票信息，进行一些数据的初始化
                        this.$http.put('/tickets/whole', this.ticketForm).then((res) => {
                            //编辑成功
                            if (res.data.state === 200) {
                                this.$message.success("车票信息编辑成功")
                                //回到车票界面
                                this.$router.push({
                                    path: '/ticket'
                                })
                            } else {
                                //控制台打印错误码和错误信息
                                console.log(res.data.state);
                                console.log(res.data.message);
                                this.$message.error("车票编辑出现未知错误，请稍后重试")
                            }
                        }).catch(() => {
                            this.$message.error("车票新增出现未知错误，请稍后重试")
                        })
                    }
                })
            },
            //新增事件
            add() {
                //表单验证
                this.$refs['ticketForm'].validate(valid => {
                    //valid代表校验结果，是布尔类型
                    if (valid) {
                        //向后台查询当前用户下的最新一条车票信息，进行一些数据的初始化
                        this.$http.post('/tickets', this.ticketForm).then((res) => {
                            //查询成功
                            if (res.data.state === 200) {
                                this.$message.success("车票信息新增成功")
                                //回到车票界面
                                this.$router.push({
                                    path: '/ticket'
                                })
                            } else {
                                //控制台打印错误码和错误信息
                                console.log(res.data.state);
                                console.log(res.data.message);
                                this.$message.error("车票新增出现未知错误，请稍后重试")
                            }
                        }).catch(() => {
                            this.$message.error("车票新增出现未知错误，请稍后重试")
                        })
                    }
                })
            },
            //上传图片的触发事件
            uploadChange(file, fileList) {
                //上传的内容达到1，则隐藏上传框
                if (fileList.length >= 1) {
                    this.uploadDisabled = true;
                } else {
                    this.uploadDisabled = false;
                }
            },
            //上传图片成功的回调
            handleAvatarSuccess(res, file) {
                //图片的路径赋值给photo
                this.ticketForm.photo = res.data
                //渲染img
                this.imgShow = true
                //删除旧图片
                let param = []
                param.push(this.delImgUrl)
                this.$http.delete('/files/', {data: param})
            },
            //图片上传前进行校验
            beforeAvatarUpload(file) {
                //文件格式支持 jpeg、jpg、png
                const isJPEG = file.type === 'image/jpeg';
                const isJPG = file.type === 'image/jpg';
                const isPNG = file.type === 'image/png';
                //文件大小 10M以下
                const isLt10M = file.size / 1024 / 1024 < 10;
                //图片格式校验
                const imgFormat = isJPG || isJPEG || isPNG

                if (!imgFormat) {
                    this.$message.error('上传图片只能是 JPG或者PNG 格式!');
                }
                if (!isLt10M) {
                    this.$message.error('上传图片大小不能超过 10MB!');
                }
                if (isLt10M && imgFormat) {
                    this.delImgUrl = this.ticketForm.photo
                }
                return isLt10M && imgFormat;
            },
            deleteImg() {
                //取消渲染
                this.imgShow = false
                //把图片url存入待删除队列。提交之后就删除
                this.delImgUrl = this.ticketForm.photo
            }
        },
        created() {
            //获取用户姓名
            this.ename = sessionStorage.getItem("ename")
            //获取用户工号
            this.ticketForm.eno = sessionStorage.getItem("eno")
            //查询所有的交通方式
            this.getAllVehicle();
            //查询所有出差方式
            this.getTypeList();
            if (this.ticketForm.tno === undefined) {
                //执行新增页面逻辑
                //查询最新一条车票信息
                this.getNewTicket();
            } else {
                //执行编辑逻辑
                //根据车票号查询车票数据信息，并回显到页面
                this.getCurrentTicket();
            }
        },

    }
</script>

<style scoped>
	/*设置上传的图片自适应我的el-upload大小*/
	.avatar {
		width: 350px;
		height: 350px;
		display: block;
	}

	/*设置disable的标签，的背景颜色和文字颜色*/
	.el-input.is-disabled /deep/ .el-input__inner {
		color: #606266;
		background-color: white;
	}


</style>
