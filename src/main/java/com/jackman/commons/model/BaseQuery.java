package com.jackman.commons.model;

import java.util.Date;

/**
 * 基础查询组件
 * @Author shusheng
 * @Date 18/11/19 上午10:10
 */
public class BaseQuery {
    /**
     * 分页参数
     */
    private Integer pageNo;
    private Integer pageSize;
    /**
     * id > gtId
     */
    private Long gtId;
    /**
     * create_time >= egtCreateTime
     */
    private Date egtCreateTime;
    /**
     * create_time < ltCreateTime
     */
    private Date ltCreateTime;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if(null == pageNo) {
            pageNo = 0;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(null == pageSize) {
            pageSize = 100;
        }
        if(pageSize >= 5000) {
            pageSize = 5000;
        }
        this.pageSize = pageSize;
    }

    public Long getGtId() {
        return gtId;
    }

    public void setGtId(Long gtId) {
        this.gtId = gtId;
    }

    public Date getEgtCreateTime() {
        return egtCreateTime;
    }

    public void setEgtCreateTime(Date egtCreateTime) {
        this.egtCreateTime = egtCreateTime;
    }

    public Date getLtCreateTime() {
        return ltCreateTime;
    }

    public void setLtCreateTime(Date ltCreateTime) {
        this.ltCreateTime = ltCreateTime;
    }
}
