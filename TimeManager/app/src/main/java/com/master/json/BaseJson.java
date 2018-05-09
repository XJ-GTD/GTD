package com.master.json;

import java.util.List;

/**
 * create by wzy on 2018/05/07
 * 基础接收类
 */
public class BaseJson {
    private String message;
    private String code;
    private List<?> dataList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }
}
