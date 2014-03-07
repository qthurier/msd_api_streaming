
package com.lastfm;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("com.googlecode.jsonschema2pojo")
public class Image {

    @SerializedName("#text")
    @Expose
    private String _text;
    @Expose
    private String size;

    public String get_text() {
        return _text;
    }

    public void set_text(String _text) {
        this._text = _text;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
