package com.jxd.travel.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
/*以json格式的数据进行响应*/
@Data
public class JsonResult<E> {
    //响应状态码
    private Integer state;
    //描述信息
    private String message;
    //数据，不确定返回的泛型类型
    private E data;
    //数量
    private Integer total;


    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state, E data, Integer total) {
        this.state = state;
        this.data = data;
        this.total = total;
    }
}
