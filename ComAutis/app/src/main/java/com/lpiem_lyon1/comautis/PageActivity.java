package com.lpiem_lyon1.comautis;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.lpiem_lyon1.comautis.Database.RequestCallback;
import com.lpiem_lyon1.comautis.Fragment.SlideshowDialogFragment;
import com.lpiem_lyon1.comautis.Models.Model;
import com.lpiem_lyon1.comautis.Models.Picture;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PageActivity extends BaseActivity {
    private String idPage;
    private ArrayList<Picture> mListPictures = new ArrayList<Picture>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        LinearLayout pageLayout = findViewById(R.id.linearLayoutPage);
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
            Glide.with(this).load(new File(mListPictures.get(i).getPicturePath())).asBitmap().placeholder(R.mipmap.ic_missing_picture).into(imageView);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
            layoutParams.setMargins(10,10,10,10);
            linearLayout.addView(imageView, layoutParams);

            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("pictures", mListPictures);
                    bundle.putInt("position", finalI);

                    android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    SlideshowDialogFragment mFragmentZoom = SlideshowDialogFragment.newInstance();
                    mFragmentZoom.setArguments(bundle);
                    mFragmentZoom.show(fragmentTransaction, "slideshow");
                }
            });
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
        }
    }
}
