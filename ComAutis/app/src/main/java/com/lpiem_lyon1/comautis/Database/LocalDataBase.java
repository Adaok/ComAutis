package com.lpiem_lyon1.comautis.Database;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

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

    @Override
    public void requestChild(RequestCallback callback) {
        
    }

    @Override
    public void requestChildById(String id, RequestCallback callback) {

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
}
