package com.lpiem_lyon1.comautis;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lpiem_lyon1.comautis.Database.RequestCallback;
import com.lpiem_lyon1.comautis.Models.Model;
import com.lpiem_lyon1.comautis.Models.PagePicture;
import com.lpiem_lyon1.comautis.Models.Picture;

import java.util.ArrayList;
import java.util.List;

public class PageActivity extends BaseActivity {
    private String idPage;
    private List<PagePicture> mListPagePicture = new ArrayList<>();
    private List<Picture> mListPictures = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout pageLayout = new LinearLayout(this);
        LinearLayout.LayoutParams pageLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT );
        setContentView(pageLayout , pageLayoutParams);

        if (getIntent() != null) {
            idPage = getIntent().getStringExtra(ChoosePageActivity.EXTRA_PAGE_ID);
        }
        loadPictures();

        for (int i = 0; i < mListPictures.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(mListPictures.get(i).getBitmap());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
            layoutParams.setMargins(10,10,10,10);
            pageLayout.addView(imageView , layoutParams);
        }
    }


    private void loadPictures(){
        mLocalDb.requestPictureFromPage(idPage, new RequestCallback() {
            @Override
            public void onResult(List<? extends Model> entities) {
                mListPagePicture = (ArrayList<PagePicture>) entities;
            }

            @Override
            public void onError(Throwable error) {

            }
        });

        int pagePictureSize = mListPagePicture.size();
        for(int i = 0 ; i < pagePictureSize ; i++){
            String idPicture = mListPagePicture.get(i).getPictureId();
            mLocalDb.requestPictureById(idPicture, new RequestCallback() {
                @Override
                public void onResult(List<? extends Model> entities) {
                    Picture pic = (Picture) entities;
                    pic.setBitmap(PictureUtils.getBitmapFromPath(pic.getPicturePath()));
                    mListPictures.add(pic);
                }

                @Override
                public void onError(Throwable error) {

                }
            });
        }
    }
}
