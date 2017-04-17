package com.app.joyfulkitchen.model;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/4/5.
 */
public class Message {
    private int id;

    private Bitmap img;

    private String menuName;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
