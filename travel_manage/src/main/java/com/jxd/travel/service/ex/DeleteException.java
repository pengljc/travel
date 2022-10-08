package com.jxd.travel.service.ex;

/**
 * @ClassName InsertException
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/21
 * @Version 1.0
 */
/*数据在删除过程中所产生的一些未知异常*/
public class DeleteException extends ServiceException {
    public DeleteException() {
        super();
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

    protected DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
