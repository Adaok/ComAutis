package com.lpiem_lyon1.comautis.Database;

/**
 * Created by alexislp on 07/01/16.
 */
public interface ILocalDataBase {

    //region CHILD
    void requestChild(RequestCallback callback);
    void requestChildById(String id, RequestCallback callback);
    void requestChildByName(String name, RequestCallback callback);
    //endregion

    //region PICTURE
    void requestPicture(RequestCallback callback);
    void requestPictureById(String id, RequestCallback callback);
    void requestPictureByName(String name, RequestCallback callback);
    void requestPictureByFolder(String folderId, RequestCallback callback);
    //endregion

    //region Folder
    void requestFolder(RequestCallback callback);
    void requestFolderById(String id, RequestCallback callback);
    void requestFolderByName(String name, RequestCallback callback);
    void requestFolderByFolder(String folderId, RequestCallback callback);
    //endregion

    //region Page
    void requestPage(RequestCallback callback);
    void requestPageById(String id, RequestCallback callback);
    void requestPageByName(String name, RequestCallback callback);
    void requestPageByChild(String childId, RequestCallback callback);
    //endregion
}
