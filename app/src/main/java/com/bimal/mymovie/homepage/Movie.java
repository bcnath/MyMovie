package com.bimal.mymovie.homepage;

public class Movie {
    private String title;
    private String desc;
    private String thumbnail;
    private String cover;
    private String streamlink;

    public Movie(){}

    public Movie(String title, String desc, String thumbnail, String cover, String streamlink) {
        this.title = title;
        this.desc = desc;
        this.thumbnail = thumbnail;
        this.cover = cover;
        this.streamlink = streamlink;
    }

    public String getStreamlink() {
        return streamlink;
    }

    public void setStreamlink(String streamlink) {
        this.streamlink = streamlink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}