
package com.lastfm;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("com.googlecode.jsonschema2pojo")
public class Album {

    @Expose
    private String artist;
    @Expose
    private String title;
    @Expose
    private String mbid;
    @Expose
    private String url;
    @Expose
    private List<Image> image = new ArrayList<Image>();
    @SerializedName("@attr")
    @Expose
    private com.lastfm._attr _attr;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public com.lastfm._attr get_attr() {
        return _attr;
    }

    public void set_attr(com.lastfm._attr _attr) {
        this._attr = _attr;
    }

}
