package com.lpiem_lyon1.comautis.Database;

import com.lpiem_lyon1.comautis.Models.Child;
import com.lpiem_lyon1.comautis.Models.Folder;
import com.lpiem_lyon1.comautis.Models.Page;
import com.lpiem_lyon1.comautis.Models.Picture;

/**
 * Created by alexislp on 07/01/16.
 */
public interface ILocalDataBase {

    //region CHILD
    void requestChild(RequestCallback callback);
    void requestChildById(String id, RequestCallback callback);
    void requestChildByName(String name, RequestCallback callback);

    void insertChild(Child child, RequestCallback callback);
    //endregion

    //region PICTURE
    void requestPicture(RequestCallback callback);
    void requestPictureById(String id, RequestCallback callback);
    void requestPictureByName(String name, RequestCallback callback);
    void requestPictureByFolder(String folderId, RequestCallback callback);

    void insertPicture(Picture picture, RequestCallback callback);
    //endregion

    //region Folder
    void requestFolder(RequestCallback callback);
    void requestFolderById(String id, RequestCallback callback);
    void requestFolderByName(String name, RequestCallback callback);
    void requestFolderByFolder(String folderId, RequestCallback callback);

    void insertFolder(Folder folder, RequestCallback callback);
    //endregion

    //region Page
    void requestPage(RequestCallback callback);
    void requestPageById(String id, RequestCallback callback);
    void requestPageByName(String name, RequestCallback callback);
    void requestPageByChild(String childId, RequestCallback callback);

    void insertPage(Page page, RequestCallback callback);
    //endregion
}
