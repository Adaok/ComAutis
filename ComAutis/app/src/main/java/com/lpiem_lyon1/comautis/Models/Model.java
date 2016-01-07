package com.lpiem_lyon1.comautis.Models;

/**
 * Created by alexislp on 06/01/16.
 */
public class Model {

    /**
     * Unique identifier
     */
    String mId;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    /**
     * Compare if an object define the same entity
     *
     * @param o An object
     * @return true if the object define the same entity
     */
    @Override
    public boolean equals(Object o) {
        // Same memory address, equal
        if (this == o) return true;
        //Null object or different classes, not equal
        if (o == null || getClass() != o.getClass()) return false;
        // If same ids, equal,
        // Else, not equal
        return ((Model) o).getId() != null && ((Model) o).getId().equals(mId);
    }
}
