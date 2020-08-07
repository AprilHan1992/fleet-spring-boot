package com.fleet.sentinel.json;

import com.fleet.sentinel.enums.ResultState;

public class R {

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
    public static R result(Integer code, Object data) {
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
        return result(ResultState.SUCCESS.getCode(), ResultState.SUCCESS.getMsg());
    }

    /**
     * 成功
     *
     * @param data 数据
     * @return R
     */
    public static R ok(Object data) {
        return result(ResultState.SUCCESS.getCode(), data);
    }

    /**
     * 成功
     *
     * @param code 返回码
     * @param data 数据（可以是返回码说明，也可以是结果数据）
     * @return
     */
    public static R ok(Integer code, Object data) {
        return result(code, data);
    }

    /**
     * 成功
     *
     * @param resultState 成功枚举
     * @return R
     */
    public static R ok(ResultState resultState) {
        return result(resultState.getCode(), resultState.getMsg());
    }

    /**
     * 错误
     *
     * @return
     */
    public static R error() {
        return result(ResultState.ERROR.getCode(), ResultState.ERROR.getMsg());
    }

    /**
     * 错误
     *
     * @param data 数据
     * @return R
     */
    public static R error(Object data) {
        return result(ResultState.ERROR.getCode(), data);
    }

    /**
     * 错误
     *
     * @param code 返回码
     * @param data 数据（可以是返回码说明，也可以是结果数据）
     * @return
     */
    public static R error(Integer code, Object data) {
        return result(code, data);
    }

    /**
     * 错误
     *
     * @param resultState 错误枚举
     * @return R
     */
    public static R error(ResultState resultState) {
        return result(resultState.getCode(), resultState.getMsg());
    }

    /**
     * 错误
     *
     * @param resultState 错误枚举
     * @param data        数据
     * @return R
     */
    public static R error(ResultState resultState, Object data) {
        return result(resultState.getCode(), data);
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
