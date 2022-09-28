package com.jxd.travel.controller;

import com.jxd.travel.service.ex.EnoNotFindException;
import com.jxd.travel.service.ex.PasswordNotMatchException;
import com.jxd.travel.service.ex.ServiceException;
import com.jxd.travel.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
/*控制类的基类*/
public class BaseController {
    //响应成功
    public static final int OK = 200;
    //找不到工号
    public static final int ENO_NOT_FIND = 301;
    //密码错误
    public static final int PWD_NOT_MATCH = 302;

    //拦截项目中的所有异常在这里集中处理
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>();

        //异常类型判断与处理
        if (e instanceof EnoNotFindException) {
            result.setState(ENO_NOT_FIND);
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(PWD_NOT_MATCH);
        }

        //写入异常信息
        result.setMessage(e.getMessage());
        return result;
    }
}
