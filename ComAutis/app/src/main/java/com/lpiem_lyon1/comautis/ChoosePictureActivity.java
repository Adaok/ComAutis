package com.lpiem_lyon1.comautis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lpiem_lyon1.comautis.Adapters.GridPicturesAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChoosePictureActivity extends BaseActivity {

    private List<Bitmap> mlistPicture = new ArrayList<>();
    private List<String> mlistPictureName = new ArrayList<>();
    private GridView mGridPictures;
    private List<Bitmap> mSelectedBitmap = new ArrayList<>();
    private List<Boolean> mIsSeleted = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_picture);

        mGridPictures = (GridView)findViewById(R.id.grid_choose_pictures);

        File files[] = PictureUtils.getPictures();
        int size = files.length;
        for(int i = 0 ; i < size ; i++){
            Bitmap bitmap = BitmapFactory.decodeFile(files[i].getAbsolutePath());
            bitmap = PictureUtils.getResizedBitmap(bitmap, 180, 180);
            mlistPicture.add(bitmap);
            mlistPictureName.add(files[i].getName());
            mIsSeleted.add(i, false);
        }

        loadGridPictures();

        mGridPictures.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (!mIsSeleted.get(position)){
                    mSelectedBitmap.add(mlistPicture.get(position));
                    mIsSeleted.set(position, true);
                }
                else {
                    mIsSeleted.set(position, false);
                }
                loadGridPictures();
                return false;
            }
        });
    }

    public void loadGridPictures(){
        GridPicturesAdapter gridPicturesAdapter = new GridPicturesAdapter(mlistPicture, mlistPictureName, mIsSeleted, getBaseContext());
        mGridPictures.setAdapter(gridPicturesAdapter);
    }

}
