
package com.spotify;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("com.googlecode.jsonschema2pojo")
public class Availability {

    @Expose
    private String territories;

    public String getTerritories() {
        return territories;
    }

    public void setTerritories(String territories) {
        this.territories = territories;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
