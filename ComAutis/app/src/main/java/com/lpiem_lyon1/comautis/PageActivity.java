package com.lpiem_lyon1.comautis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toolbar;

import com.lpiem_lyon1.comautis.Database.RequestCallback;
import com.lpiem_lyon1.comautis.Models.Model;
import com.lpiem_lyon1.comautis.Models.Picture;

import java.util.ArrayList;
import java.util.List;

public class PageActivity extends BaseActivity {
    private String idPage;
    private List<Picture> mListPictures = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        LinearLayout pageLayout = (LinearLayout)findViewById(R.id.linearLayoutPage);
        LinearLayout.LayoutParams pageLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT );

        if (getIntent() != null) {
            idPage = getIntent().getStringExtra(ChoosePageActivity.EXTRA_PAGE_ID);
        }
        loadPictures();

        ScrollView scrollView = new ScrollView(this);
        pageLayout.addView(scrollView , pageLayoutParams);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(View.TEXT_ALIGNMENT_CENTER);

        for (int i = 0; i < mListPictures.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(mListPictures.get(i).getBitmap());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
            layoutParams.setMargins(10,10,10,10);
            linearLayout.addView(imageView, layoutParams);
        }
        scrollView.addView(linearLayout,pageLayoutParams);
    }


    private void loadPictures(){
        mLocalDb.requestPictureFromPage(idPage, new RequestCallback() {
            @Override
            public void onResult(List<? extends Model> entities) {
                mListPictures = (ArrayList<Picture>) entities;
            }

            @Override
            public void onError(Throwable error) {

            }
        });

        int pagePictureSize = mListPictures.size();
        for(int i = 0 ; i < pagePictureSize ; i++){
                    Picture pic = mListPictures.get(i);
                    pic.setBitmap(PictureUtils.getBitmapFromPath(pic.getPicturePath()));
        }
    }
}
