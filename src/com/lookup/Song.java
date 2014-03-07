
package com.lookup;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("com.googlecode.jsonschema2pojo")
public class Song {

    @Expose
    private String title;
    @Expose
    private String artist_name;
    @Expose
    private List<Artist_foreign_id> artist_foreign_ids = new ArrayList<Artist_foreign_id>();
    @Expose
    private List<Track> tracks = new ArrayList<Track>();
    @Expose
    private String artist_id;
    @Expose
    private String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public List<Artist_foreign_id> getArtist_foreign_ids() {
        return artist_foreign_ids;
    }

    public void setArtist_foreign_ids(List<Artist_foreign_id> artist_foreign_ids) {
        this.artist_foreign_ids = artist_foreign_ids;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
