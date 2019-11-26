package com.bimal.mymovie.moviedetail;

public class Series {
    private  String poster;
    private String episodeno;

    public Series(String poster, String episodeno) {

        this.poster = poster;
        this.episodeno = episodeno;
    }

    public Series(String episodeno, String poster, String streamlink) {
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getEpisodeno() {
        return episodeno;
    }

    public void setEpisodeno(String episodeno) {
        this.episodeno = episodeno;
    }
}
