package com.jxd.travel.controller;

import com.jxd.travel.controller.ex.FileStateException;
import com.jxd.travel.controller.ex.FileUploadIOException;
import com.jxd.travel.service.ex.*;
import com.jxd.travel.util.JsonResult;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
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
    public static final int ENO_NOT_FIND = 3001;
    //密码错误
    public static final int PWD_NOT_MATCH = 3002;
    //新增过程中产生未知异常
    public static final int INSERT_EX = 4001;
    //更新过程中产生的未知异常
    public static final int UPDATE_EX = 5001;
    //删除过程中产生的未知异常
    public static final int DELETE_EX = 6001;
    //上传文件的状态异常
    public static final int FILE_STATE_EX = 7001;
    //上传文件进行IO操作时的IO异常
    public static final int FILE_IO_EX = 7002;

    //拦截项目中的所有异常在这里集中处理
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>();

        //异常类型判断与处理
        if (e instanceof EnoNotFindException) {
            result.setState(ENO_NOT_FIND);
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(PWD_NOT_MATCH);
        } else if (e instanceof InsertException) {
            result.setState(INSERT_EX);
        } else if (e instanceof UpdateException) {
            result.setState(UPDATE_EX);
        } else if (e instanceof DeleteException) {
            result.setState(DELETE_EX);
        } else if (e instanceof FileStateException) {
            result.setState(FILE_STATE_EX);
        } else if (e instanceof FileUploadIOException) {
            result.setState(FILE_IO_EX);
        }

        //写入异常信息
        result.setMessage(e.getMessage());
        return result;
    }
}
