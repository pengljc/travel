package com.jxd.travel.model;

import lombok.Data;

/**
 * @ClassName Emp
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@Data
public class Emp {
    //工号
    private Integer eno;
    //姓名
    private String ename;
    //密码
    private String password;
    //性别 0女1男
    private Integer sex;
    //电话
    private String phone;
    //身份
    private Integer postno;
}
