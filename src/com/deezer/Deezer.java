
package com.deezer;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("com.googlecode.jsonschema2pojo")
public class Deezer {

    @Expose
    private Integer id;
    @Expose
    private Boolean readable;
    @Expose
    private String title;
    @Expose
    private String isrc;
    @Expose
    private String link;
    @Expose
    private Integer duration;
    @Expose
    private Integer track_position;
    @Expose
    private Integer disk_number;
    @Expose
    private Integer rank;
    @Expose
    private Boolean explicit_lyrics;
    @Expose
    private String preview;
    @Expose
    private Double bpm;
    @Expose
    private Double gain;
    @Expose
    private List<String> available_countries = new ArrayList<String>();
    @Expose
    private Artist artist;
    @Expose
    private Album album;
    @Expose
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getReadable() {
        return readable;
    }

    public void setReadable(Boolean readable) {
        this.readable = readable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTrack_position() {
        return track_position;
    }

    public void setTrack_position(Integer track_position) {
        this.track_position = track_position;
    }

    public Integer getDisk_number() {
        return disk_number;
    }

    public void setDisk_number(Integer disk_number) {
        this.disk_number = disk_number;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Boolean getExplicit_lyrics() {
        return explicit_lyrics;
    }

    public void setExplicit_lyrics(Boolean explicit_lyrics) {
        this.explicit_lyrics = explicit_lyrics;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Double getBpm() {
        return bpm;
    }

    public void setBpm(Double bpm) {
        this.bpm = bpm;
    }

    public Double getGain() {
        return gain;
    }

    public void setGain(Double gain) {
        this.gain = gain;
    }

    public List<String> getAvailable_countries() {
        return available_countries;
    }

    public void setAvailable_countries(List<String> available_countries) {
        this.available_countries = available_countries;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
