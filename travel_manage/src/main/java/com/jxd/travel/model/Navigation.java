package com.jxd.travel.model;

import lombok.Data;

/**
 * @ClassName navigation
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
@Data
public class Navigation {
    //导航栏主键
    private Integer navno;
    //导航栏内容
    private String content;
    //路径
    private String path;
    //图标信息
    private String icon;
    //用户身份
    private Integer postno;
}
