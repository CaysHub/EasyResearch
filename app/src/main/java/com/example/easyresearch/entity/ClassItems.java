package com.example.easyresearch.entity;

/**
 * Created by lenovo on 2018/6/24.
 */

public class ClassItems {
    private int resourceId;
    private String name;
    public ClassItems(String name,int resourceId){
        this.name=name;this.resourceId=resourceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public int getResourceId() {
        return resourceId;
    }
}
