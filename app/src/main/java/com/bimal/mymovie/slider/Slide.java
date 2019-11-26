package com.bimal.mymovie.slider;

public class Slide {
    private int Image;
    private  String Title;

    public Slide(int image,String title){
        this.Image =image;
        this.Title=title;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
