package com.hqyj.javaSpringBoot.modules.common.vo;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/12 13:23
 */
public class Result<T> {

    private int status;
    private String message;
    private T object;

    public Result() {
    }

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(int status, String message, T object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public enum ResultStatus{
        SUCCESS(200),FATLD(500);
        public int status;

        ResultStatus(int status) {
            this.status = status;
        }
    }

}
