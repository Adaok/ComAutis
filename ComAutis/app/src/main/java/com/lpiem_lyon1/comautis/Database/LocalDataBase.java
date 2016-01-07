package com.lpiem_lyon1.comautis.Database;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lpiem_lyon1.comautis.Database.Table.ChildTable;
import com.lpiem_lyon1.comautis.Models.Child;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexislp on 07/01/16.
 */
public class LocalDataBase implements ILocalDataBase {

    private static final String TAG = LocalDataBase.class.getSimpleName();

    private SQLiteDatabase mSQLiteDatabase;
    private SharedPreferences mPreferences;

    public LocalDataBase(SQLiteDatabase SQLiteDatabase, SharedPreferences preferences) {
        mSQLiteDatabase = SQLiteDatabase;
        mPreferences = preferences;
    }

    //region Request
    @Override
    public void requestChild(RequestCallback callback) {
        Cursor cursor = mSQLiteDatabase.query(ChildTable.TABLE_NAME, null, null, null, null, null, null, null);
        List<Child> childList = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Child child = new ChildTable().fromCursor(cursor);
                childList.add(child);
                cursor.close();
                return;
            }
        }
        cursor.close();
        if (callback != null)
            callback.onResult(childList);

    }

    @Override
    public void requestChildById(String id, RequestCallback callback) {
        if(id != "" && id != null) {
            Cursor cursor = mSQLiteDatabase.query(ChildTable.TABLE_NAME, null, ChildTable.KEY_ID + "=?", new String[]{id}, null, null, null, null);
            List<Child> childList = new ArrayList<>();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    Child child = new ChildTable().fromCursor(cursor);
                    childList.add(child);
                    cursor.close();
                    return;
                }
            }
            cursor.close();
            if (callback != null)
                callback.onResult(childList);
        }
        if (callback != null) {
            callback.onError(new IllegalArgumentException("id is null"));
        }
    }

    @Override
    public void requestChildByName(String name, RequestCallback callback) {

    }

    @Override
    public void requestPicture(RequestCallback callback) {

    }

    @Override
    public void requestPictureById(String id, RequestCallback callback) {

    }

    @Override
    public void requestPictureByName(String name, RequestCallback callback) {

    }

    @Override
    public void requestPictureByFolder(String folderId, RequestCallback callback) {

    }

    @Override
    public void requestFolder(RequestCallback callback) {

    }

    @Override
    public void requestFolderById(String id, RequestCallback callback) {

    }

    @Override
    public void requestFolderByName(String name, RequestCallback callback) {

    }

    @Override
    public void requestFolderByFolder(String folderId, RequestCallback callback) {

    }

    @Override
    public void requestPage(RequestCallback callback) {

    }

    @Override
    public void requestPageById(String id, RequestCallback callback) {

    }

    @Override
    public void requestPageByName(String name, RequestCallback callback) {

    }

    @Override
    public void requestPageByChild(String childId, RequestCallback callback) {

    }

    //endregion
}
