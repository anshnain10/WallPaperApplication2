package com.example.wallpaperapplication;

public class Model {
    public String getUser() {
        return user;
    }

    public String getLikes() {
        return likes;
    }

    public String getDownloads() {
        return downloads;
    }

    private String user;
    private String likes;
    private String downloads;

    public String getWebformatURL() {
        return webformatURL;
    }

    private String webformatURL;

    public String getLargeImageURL() {
        return largeImageURL;
    }

    private String largeImageURL;

    public Model(String user, String likes, String downloads, String webformatURL,String largeImageURL) {
        this.user = user;
        this.likes = likes;
        this.downloads = downloads;
        this.webformatURL = webformatURL;
        this.largeImageURL=largeImageURL;

    }

    public boolean isIslked(boolean b) {
        return islked;
    }

    public void setIslked(boolean islked) {
        this.islked = islked;
    }

    private boolean islked;
}
