package com.yotop.aipc.core;

import com.github.pagehelper.Page;

import java.util.List;

public class Pager {
    private Long total;
    private List<?> rows;

    public Pager() {
        super();
    }
    public Pager(List<?> list) {
        if (null==list){
            this.total = 0L;
            this.rows = null;
            return;
        }
        if (list instanceof Page) {
            Page page = (Page)list;
            this.total = page.getTotal();
            this.rows = page;
        } else{
            this.total = (long)list.size();
            this.rows = list;
        }
    }
    public Pager(Integer total, List<?> rows) {
        this.total = total.longValue();
        this.rows = rows;
    }

    public Pager(Long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
