package com.lpiem_lyon1.comautis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lpiem_lyon1.comautis.Adapters.GridPicturesAdapter;
import com.lpiem_lyon1.comautis.Models.Picture;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChoosePictureActivity extends BaseActivity {

    private List<Picture> mlistPictures = new ArrayList<>();
    private String pageId;
    private GridView mGridPictures;
    private List<Picture> mSelectedBitmap = new ArrayList<>();
    private List<Boolean> mIsSeleted = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_picture);

        getSupportActionBar().setTitle(R.string.choose_pictures);

        if (getIntent()!=null){
            pageId = getIntent().getStringExtra(ChoosePageActivity.EXTRA_PAGE_ID);
        }

        mGridPictures = (GridView)findViewById(R.id.grid_choose_pictures);

        File files[] = PictureUtils.getPictures();
        int size = files.length;
        for(int i = 0 ; i < size ; i++){
            Bitmap bitmap = BitmapFactory.decodeFile(files[i].getAbsolutePath());
            bitmap = PictureUtils.getResizedBitmap(bitmap, 180, 180);
            Picture picture = new Picture();
            picture.setName(files[i].getName());
            picture.setBitmap(bitmap);
            picture.setmPicturePath(files[i].getAbsolutePath());
            mlistPictures.add(picture);
            mIsSeleted.add(i, false);
        }

        loadGridPictures();

        mGridPictures.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!mIsSeleted.get(position)) {
                    mSelectedBitmap.add(mlistPictures.get(position));
                    mIsSeleted.set(position, true);
                    Picture picturePage = new Picture();
                    picturePage.setName(mlistPictures.get(position).getName());
                } else {
                    for (int i = 0; i < mSelectedBitmap.size(); i++) {
                        if (mlistPictures.get(position).getName().equals(mSelectedBitmap.get(i).getName())) {
                            mSelectedBitmap.remove(i);
                        }
                    }
                    mIsSeleted.set(position, false);
                }
                loadGridPictures();
                return;
            }
        });
    }

    public void loadGridPictures(){
        GridPicturesAdapter gridPicturesAdapter = new GridPicturesAdapter(mlistPictures, mIsSeleted, getBaseContext());
        mGridPictures.setAdapter(gridPicturesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.for_choose_picture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_validate_picture){
            if(mSelectedBitmap.size() != 0) {
                for (int j = 0; j < mSelectedBitmap.size(); j++) {
                    mSelectedBitmap.get(j).setPageId(pageId);
                    mSelectedBitmap.get(j).setOrder(Integer.toString(j));
                    mLocalDb.insertPicture(mSelectedBitmap.get(j), null);
                }
                Intent intentPage = new Intent(getBaseContext(), PageActivity.class);
                intentPage.putExtra(ChoosePageActivity.EXTRA_PAGE_ID, pageId);
                startActivity(intentPage);
                finish();
            }
        }else if(item.getItemId() == R.id.home){
            onBackPressed();
        }
        return false;
    }

    @Override
    protected boolean useDrawerToggle() {
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mLocalDb.deletePageById(pageId,null);
        finish();
    }
}
