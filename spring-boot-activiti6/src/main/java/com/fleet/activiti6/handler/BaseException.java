package com.fleet.activiti6.handler;

import com.fleet.activiti6.enums.ResultState;

/**
 * @author April Han
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 4253696777296748794L;

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BaseException() {
        super(ResultState.ERROR.getMsg());
        this.code = ResultState.ERROR.getCode();
        this.msg = ResultState.ERROR.getMsg();
    }

    public BaseException(String msg) {
        super(msg);
        this.code = ResultState.ERROR.getCode();
        this.msg = msg;
    }

    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(ResultState resultState) {
        super(resultState.getMsg());
        this.code = resultState.getCode();
        this.msg = resultState.getMsg();
    }

    public BaseException(ResultState resultState, String msg) {
        super(msg);
        this.code = resultState.getCode();
        this.msg = msg;
    }
}
