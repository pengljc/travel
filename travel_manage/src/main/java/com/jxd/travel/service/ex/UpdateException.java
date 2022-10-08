package com.jxd.travel.service.ex;

/**
 * @ClassName InsertException
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/21
 * @Version 1.0
 */
/*数据在插入过程中所产生的一些未知异常*/
public class UpdateException extends ServiceException {
    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
