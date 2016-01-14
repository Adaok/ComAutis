package com.lpiem_lyon1.comautis.Database.Table;

import android.content.ContentValues;
import android.database.Cursor;


import com.lpiem_lyon1.comautis.Models.Picture;

/**
 * Created by alexislp on 06/01/16.
 */
public class PictureTable extends AbstractTable<Picture> {

    /**
     * The name of the table
     */
    public static final String TABLE_NAME = "picture";

    /**
     * Represents the unique Id of a {@link Picture}
     */
    public static final String KEY_ID = "id";

    /**
     * Represents the name of a {@link Picture}
     */
    public static final String KEY_NAME = "name";

    /**
     * Represents the path of a {@link Picture}
     */
    public static final String KEY_PICTURE_PATH = "picture_path";

    /**
     * To know if the page was a favorite
     */
    public static final String KEY_IS_FAVORITE = "is_favorite";

    /**
     * Id of folder owner of this {@link Picture}
     */
    public static final String KEY_FOLDER_ID = "child_id";

    public static final String KEY_PAGE_ID ="page_id";

    public static final String KEY_ORDER = "order_picture";

    /**
     * The creation SQLite command of {@link Picture}
     */
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            KEY_ID + " integer primary key autoincrement," +
            KEY_NAME + TYPE_TEXT + ", " +
            KEY_PICTURE_PATH + TYPE_TEXT + ", " +
            KEY_IS_FAVORITE + TYPE_BOOLEAN + ", " +
            KEY_FOLDER_ID + TYPE_SMALLTEXT + ", " +
            KEY_PAGE_ID + TYPE_SMALLTEXT + ", " +
            KEY_ORDER + TYPE_SMALLTEXT + ")";

    @Override
    public ContentValues getContentValues(Picture object) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, object.getId());
        contentValues.put(KEY_NAME, object.getName());
        contentValues.put(KEY_PICTURE_PATH, object.getPicturePath());
        contentValues.put(KEY_IS_FAVORITE, object.isFavorite());
        contentValues.put(KEY_FOLDER_ID, object.getFolderId());
        contentValues.put(KEY_PAGE_ID, object.getPageId());
        contentValues.put(KEY_ORDER, object.getOrder());
        return contentValues;
    }

    @Override
    public Picture fromContentValues(ContentValues contentValues) {
        return null;
    }

    @Override
    public Picture fromCursor(Cursor cursor) {
        Picture picture = new Picture();
        picture.setId(cursor.getString(cursor.getColumnIndex(PictureTable.KEY_ID)));
        picture.setName(cursor.getString(cursor.getColumnIndex(PictureTable.KEY_NAME)));
        picture.setmPicturePath(cursor.getString(cursor.getColumnIndex(PictureTable.KEY_PICTURE_PATH)));
        picture.setIsFavorite(cursor.getInt(cursor.getColumnIndex(PictureTable.KEY_IS_FAVORITE)));
        picture.setFolderId(cursor.getString(cursor.getColumnIndex(PictureTable.KEY_FOLDER_ID)));
        picture.setFolderId(cursor.getString(cursor.getColumnIndex(PictureTable.KEY_PAGE_ID)));
        picture.setFolderId(cursor.getString(cursor.getColumnIndex(PictureTable.KEY_ORDER)));
        return picture;
    }
}
