package com.example.api;

import android.graphics.drawable.Icon;

import java.io.Serializable;

public class Persona implements Serializable {
    private String userId;
    private String id;
    private String title;
    private String body;
    private Icon iconImage;

    public Icon getIconImage() {
        return iconImage;
    }

    public void setIconImage(Icon iconImage) {
        this.iconImage = iconImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
