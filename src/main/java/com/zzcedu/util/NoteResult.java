package com.zzcedu.util;

/**
 * @Author: Evan
 * @Date: 2020/8/21 9:25
 * 封装Json的响应结果
 */
public class NoteResult {
    //0是正确的，非0都是错误情况
    private int status;
    //返回给前台的信息
    private String msg;
    //返回给前台的数据
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
