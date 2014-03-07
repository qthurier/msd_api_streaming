
package com.echonest;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("com.googlecode.jsonschema2pojo")
public class Track {

    @Expose
    private String status;
    @Expose
    private List<String> foreign_release_ids = new ArrayList<String>();
    @Expose
    private String catalog;
    @Expose
    private String audio_md5;
    @Expose
    private String id;
    @Expose
    private String song_id;
    @Expose
    private String release_image;
    @Expose
    private String artist;
    @Expose
    private List<String> foreign_ids = new ArrayList<String>();
    @Expose
    private String title;
    @Expose
    private String preview_url;
    @Expose
    private String foreign_release_id;
    @Expose
    private String release;
    @Expose
    private String foreign_id;
    @Expose
    private Audio_summary audio_summary;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getForeign_release_ids() {
        return foreign_release_ids;
    }

    public void setForeign_release_ids(List<String> foreign_release_ids) {
        this.foreign_release_ids = foreign_release_ids;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getAudio_md5() {
        return audio_md5;
    }

    public void setAudio_md5(String audio_md5) {
        this.audio_md5 = audio_md5;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }

    public String getRelease_image() {
        return release_image;
    }

    public void setRelease_image(String release_image) {
        this.release_image = release_image;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<String> getForeign_ids() {
        return foreign_ids;
    }

    public void setForeign_ids(List<String> foreign_ids) {
        this.foreign_ids = foreign_ids;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }

    public String getForeign_release_id() {
        return foreign_release_id;
    }

    public void setForeign_release_id(String foreign_release_id) {
        this.foreign_release_id = foreign_release_id;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getForeign_id() {
        return foreign_id;
    }

    public void setForeign_id(String foreign_id) {
        this.foreign_id = foreign_id;
    }

    public Audio_summary getAudio_summary() {
        return audio_summary;
    }

    public void setAudio_summary(Audio_summary audio_summary) {
        this.audio_summary = audio_summary;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
