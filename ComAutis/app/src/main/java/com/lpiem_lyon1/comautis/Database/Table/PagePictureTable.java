package com.lpiem_lyon1.comautis.Database.Table;

import android.content.ContentValues;
import android.database.Cursor;

import com.lpiem_lyon1.comautis.Models.Folder;
import com.lpiem_lyon1.comautis.Models.Page;
import com.lpiem_lyon1.comautis.Models.PagePicture;
import com.lpiem_lyon1.comautis.Models.Picture;

/**
 * Created by alexislp on 06/01/16.
 */
public class PagePictureTable extends AbstractTable<PagePicture> {

    /**
     * The name of the table
     */
    public static final String TABLE_NAME = "page_picture";

    /**
     * Represents the unique Id of a {@link Page}
     */
    public static final String KEY_PAGE_ID = "id_page";

    /**
     * Represents the unique Id of a {@link Picture}
     */
    public static final String KEY_PICTURE_ID = "id_picture";

    /**
     * Represents the position of the picture in the page
     */

    public static final String KEY_ORDER = "order_picture";

    /**
     * The creation SQLite command of the association table

     */
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            KEY_PAGE_ID + AbstractTable.TYPE_TEXT + ", " +
            KEY_PICTURE_ID + AbstractTable.TYPE_TEXT + ", " + KEY_ORDER + AbstractTable.TYPE_SMALLTEXT + ")";


    @Override
    public ContentValues getContentValues(PagePicture object) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PAGE_ID, object.getPageId());
        contentValues.put(KEY_PICTURE_ID, object.getPictureId());
        contentValues.put(KEY_ORDER, object.getOrder());
        return contentValues;
    }

    @Override
    public PagePicture fromContentValues(ContentValues contentValues) {
        return null;
    }

    @Override
    public PagePicture fromCursor(Cursor cursor) {
        PagePicture pagePicture = new PagePicture();
        pagePicture.setPageId(cursor.getString(cursor.getColumnIndex(PagePictureTable.KEY_PAGE_ID)));
        pagePicture.setPictureId(cursor.getString(cursor.getColumnIndex(PagePictureTable.KEY_PICTURE_ID)));
        pagePicture.setOrder(cursor.getString(cursor.getColumnIndex(PagePictureTable.KEY_ORDER)));
        return pagePicture;    }
}



