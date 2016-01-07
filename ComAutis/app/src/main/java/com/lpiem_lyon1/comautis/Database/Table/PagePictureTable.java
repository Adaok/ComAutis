package com.lpiem_lyon1.comautis.Database.Table;

import android.content.ContentValues;
import android.database.Cursor;

import com.lpiem_lyon1.comautis.Models.Page;
import com.lpiem_lyon1.comautis.Models.Picture;

/**
 * Created by alexislp on 06/01/16.
 */
public class PagePictureTable {

    /**
     * The name of the table
     */
    public static final String TABLE_NAME = "page_picture";

    /**
     * Represents the unique Id of a {@link Page}
     */
    public static final String KEY_PAGE_ID = "id";

    /**
     * Represents the unique Id of a {@link Picture}
     */
    public static final String KEY_PICTURE_ID = "id";

    /**
     * The creation SQLite command of the association table

     */
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            KEY_PAGE_ID + AbstractTable.TYPE_TEXT + ", " +
            KEY_PICTURE_ID + AbstractTable.TYPE_TEXT + ")";
}