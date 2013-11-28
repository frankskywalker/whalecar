package com.whalecar.domain;

import java.io.Serializable;

/**
 * Created by ruihuang on 13-11-28.
 */
public class CarAddition implements Serializable {
    private Integer id;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
