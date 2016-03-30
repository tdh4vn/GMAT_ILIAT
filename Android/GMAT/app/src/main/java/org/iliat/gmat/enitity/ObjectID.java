package org.iliat.gmat.enitity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hungtran on 3/13/16.
 */
public class ObjectID {

    private static final String OID = "oid";

    @SerializedName(OID)
    private String oid;

    public String getOID() {
        return oid;
    }
}
