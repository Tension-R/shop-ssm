package com.tension.bean;

import java.sql.Date;

/**
 * 商品规格实体类
 */
public class ItemParam {
    private long id;
    private long itemCatId;
    private String paramData;
    private Date createTime;
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItemCatId() {
        return itemCatId;
    }

    public void setItemCatId(long itemCatId) {
        this.itemCatId = itemCatId;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ItemParam{" +
                "id=" + id +
                ", itemCatId=" + itemCatId +
                ", paramData='" + paramData + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
