package com.lpiem_lyon1.comautis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.GridView;

import com.lpiem_lyon1.comautis.Adapters.GridPicturesAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChoosePictureActivity extends BaseActivity {

    private List<Bitmap> mlistPicture = new ArrayList<>();
    private List<String> mlistPictureName = new ArrayList<>();
    private GridView mGridPictures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_picture);

        mGridPictures = (GridView)findViewById(R.id.grid_choose_pictures);
        mGridPictures.setNumColumns(3);

        File files[] = PictureUtils.getPictures();
        int size = files.length;
        for(int i = 0 ; i < size ; i++){
            Bitmap bitmap = BitmapFactory.decodeFile(files[i].getAbsolutePath());
            mlistPicture.add(bitmap);
            mlistPictureName.add(files[i].getName());
        }

        GridPicturesAdapter gridPicturesAdapter = new GridPicturesAdapter(mlistPicture, mlistPictureName, getBaseContext());
        mGridPictures.setAdapter(gridPicturesAdapter);
    }
}
