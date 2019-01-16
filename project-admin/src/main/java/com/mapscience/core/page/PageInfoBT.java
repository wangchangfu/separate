package com.mapscience.core.page;

import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果的封装
 *
 * @param <T>
 */
public class PageInfoBT<T> implements Serializable {
    // 结果集
    private List<T> data;

    private int code;
    // 总数
    private long count;

    private String msg;

    public PageInfoBT(Page<T> page) {
        this.data = page.getRecords();
        this.count = page.getTotal();
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
