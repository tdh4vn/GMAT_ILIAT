package org.iliat.gmat.enitity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hungtran on 3/13/16.
 */
public class id {

    private String oid;

    public id() {
    }

    public id(String oid) {
        this.oid = oid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
