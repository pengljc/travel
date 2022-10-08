package com.jxd.travel.model;

import lombok.Data;

/**
 * @ClassName Bill
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/10/8
 * @Version 1.0
 */
@Data
public class Bill {
    //单据序号
    private Integer bno;
    //工号
    private Integer eno;
    //姓名
    private String ename;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //报销申请时间
    private String applicationTime;
    //出差时间
    private Double days;
    //报销金额
    private Double money;
    //出差描述
    private String description;
}
