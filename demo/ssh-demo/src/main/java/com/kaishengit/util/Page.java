package com.kaishengit.util;

import java.util.List;

/**
 * @author Tonglin
 */
public class Page<T> {

    private Integer pageTotal;
    private Integer total;
    private Integer pageNum;
    private Integer startNum;
    private Integer pageSize;
    private List<T> items;

    public Page() {}

    public Page(Integer pageSize,Integer pageNum,Integer total) {
        this.total = total;

        pageTotal = total/pageSize;
        if (total % pageSize > 0){
            pageTotal++;
        }

        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageNum > pageTotal) {
            pageNum = pageTotal;
        }

        if (pageSize > total) {
            pageNum = 1;
        }

        this.startNum = pageSize * (pageNum - 1);

    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
