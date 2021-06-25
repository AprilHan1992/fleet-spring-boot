package com.fleet.keycloak.json;

import com.fleet.keycloak.enums.ResultState;

import java.io.Serializable;

/**
 * @author April Han
 */
public class R implements Serializable {

    private static final long serialVersionUID = -6362815283450958495L;

    private Integer code;

    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 结果返回
     *
     * @param code 返回码
     * @param data 数据（可以是返回码说明，也可以是结果数据）
     * @return R
     */
    public static R r(Integer code, Object data) {
        R r = new R();
        r.setCode(code);
        r.setData(data);
        return r;
    }

    /**
     * 成功
     *
     * @return R
     */
    public static R ok() {
        return r(ResultState.SUCCESS.getCode(), ResultState.SUCCESS.getMsg());
    }

    /**
     * 成功
     *
     * @param resultState 成功枚举
     * @return R
     */
    public static R ok(ResultState resultState) {
        return r(resultState.getCode(), resultState.getMsg());
    }

    /**
     * 成功
     *
     * @param code 返回码
     * @param data 数据（可以是返回码说明，也可以是结果数据）
     * @return R
     */
    public static R ok(Integer code, Object data) {
        return r(code, data);
    }

    /**
     * 错误
     *
     * @param resultState 成功枚举
     * @param data        数据
     * @return R
     */
    public static R ok(ResultState resultState, Object data) {
        return r(resultState.getCode(), data);
    }

    /**
     * 成功
     *
     * @param data 数据
     * @return R
     */
    public static R ok(Object data) {
        return r(ResultState.SUCCESS.getCode(), data);
    }

    /**
     * 错误
     *
     * @return R
     */
    public static R error() {
        return r(ResultState.ERROR.getCode(), ResultState.ERROR.getMsg());
    }

    /**
     * 错误
     *
     * @param resultState 错误枚举
     * @return R
     */
    public static R error(ResultState resultState) {
        return r(resultState.getCode(), resultState.getMsg());
    }

    /**
     * 错误
     *
     * @param code 返回码
     * @param data 数据（可以是返回码说明，也可以是结果数据）
     * @return
     */
    public static R error(Integer code, Object data) {
        return r(code, data);
    }

    /**
     * 错误
     *
     * @param resultState 错误枚举
     * @param data        数据
     * @return R
     */
    public static R error(ResultState resultState, Object data) {
        return r(resultState.getCode(), data);
    }

    /**
     * 错误
     *
     * @param data 数据
     * @return R
     */
    public static R error(Object data) {
        return r(ResultState.ERROR.getCode(), data);
    }

    /**
     * 判断结果是否正确
     *
     * @param r
     * @return
     */
    public static Boolean isOk(R r) {
        if (r != null && r.getCode().equals(ResultState.SUCCESS.getCode())) {
            return true;
        }
        return false;
    }
}
