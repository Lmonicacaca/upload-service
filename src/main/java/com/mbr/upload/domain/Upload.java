package com.mbr.upload.domain;

import java.util.Date;

public class Upload {

    private Long id;
    private String fileName;
    private String contentType;
    private String size;
    private String diskUrl;
    private String internetUrl;
    private Date createTime;
    private Date updateTime;
    private Long channel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDiskUrl() {
        return diskUrl;
    }

    public void setDiskUrl(String diskUrl) {
        this.diskUrl = diskUrl;
    }

    public String getInternetUrl() {
        return internetUrl;
    }

    public void setInternetUrl(String internetUrl) {
        this.internetUrl = internetUrl;
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

    public Long getChannel() {
        return channel;
    }

    public void setChannel(Long channel) {
        this.channel = channel;
    }
}
