package com.jxd.travel.model;

import lombok.Data;

/**
 * @ClassName Ticket
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/28
 * @Version 1.0
 */
@Data
public class Ticket {
    //车票号
    private Integer tno;
    //工号
    private Integer eno;
    //差旅信息号
    private Integer bno;
    //交通工具类型号
    private Integer vno;
    //类型 出发0 返回1
    private Integer type;
    //出发地点
    private String leavePlace;
    //到达地点
    private String arrivePlace;
    //出发时间
    private String leaveTime;
    //到达时间
    private String arriveTime;
    //车票价格
    private Double ticketMoney;
    //车票照片
    private String photo;
    //出差说明
    private String description;
}
