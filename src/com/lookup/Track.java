
package com.lookup;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("com.googlecode.jsonschema2pojo")
public class Track {

    @Expose
    private String foreign_release_id;
    @Expose
    private String catalog;
    @Expose
    private String foreign_id;
    @Expose
    private String id;

    public String getForeign_release_id() {
        return foreign_release_id;
    }

    public void setForeign_release_id(String foreign_release_id) {
        this.foreign_release_id = foreign_release_id;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getForeign_id() {
        return foreign_id;
    }

    public void setForeign_id(String foreign_id) {
        this.foreign_id = foreign_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
