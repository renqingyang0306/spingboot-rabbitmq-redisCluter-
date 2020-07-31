package com.rqy.product.module;


import java.io.Serializable;

/**
 * 
 * @Author renqingyang
 * @create 2020/4/18 11:15 PM
 */
public class Order implements Serializable {
    private Integer id;

    private String name;

    private String messageId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", messageId=").append(messageId);
        sb.append("]");
        return sb.toString();
    }
}