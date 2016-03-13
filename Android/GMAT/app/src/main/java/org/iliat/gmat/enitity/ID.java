package org.iliat.gmat.enitity;

/**
 * Created by hungtran on 3/13/16.
 */
public class ID {
    private String $oid;

    public ID() {
    }

    public ID(String $oid) {
        this.$oid = $oid;
    }

    public String get$oid() {
        return $oid;
    }

    public void set$oid(String $oid) {
        this.$oid = $oid;
    }
}
