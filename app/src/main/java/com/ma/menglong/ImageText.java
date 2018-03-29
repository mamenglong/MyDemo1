package com.ma.menglong;

/**
 * Created by Long on 2018/3/22.
 */

public class ImageText {
    private String text;
    private int imageId;
    public String getText() {
        return text;
    }


    public void setText(String name) {
        this.text = text;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public ImageText(String text, int imageId) {
        this.text = text;
        this.imageId = imageId;
    }
}
