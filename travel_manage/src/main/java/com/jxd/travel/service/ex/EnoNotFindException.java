package com.jxd.travel.service.ex;

/**
 * @ClassName EnoNotFindException
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/27
 * @Version 1.0
 */
public class EnoNotFindException extends ServiceException {
    public EnoNotFindException() {
        super();
    }

    public EnoNotFindException(String message) {
        super(message);
    }

    public EnoNotFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnoNotFindException(Throwable cause) {
        super(cause);
    }

    protected EnoNotFindException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
