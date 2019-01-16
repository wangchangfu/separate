package com.mapscience.core.common.annotion.factory;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.core.common.constant.state.Order;
import com.mapscience.core.support.HttpKit;
import com.mapscience.core.util.ToolUtil;

import javax.servlet.http.HttpServletRequest;

public class PageFactory<T> {
    public Page<T> defaultPage(){
        HttpServletRequest request = HttpKit.getRequest();
        //每页多少条数据
        int limit = Integer.valueOf(request.getParameter("limit"));
        //当前页码
        int pageNum = Integer.valueOf(request.getParameter("page"));
        String sort = request.getParameter("sort");         //排序字段名称
        String order = request.getParameter("order");       //asc或desc(升序或降序)

        if (ToolUtil.isEmpty(sort)){
            Page<T> page=new Page<>(pageNum,limit);
            page.setOpenSort(false);
            return page;
        }else{
            Page<T> page=new Page<>(pageNum,limit,sort);
            if (Order.ASC.getDes().equals(order)) {
                page.setAsc(true);
            } else {
                page.setAsc(false);
            }
            return page;
        }

    }
}
