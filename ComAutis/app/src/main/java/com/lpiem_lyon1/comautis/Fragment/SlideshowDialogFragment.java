package com.lpiem_lyon1.comautis.Fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lpiem_lyon1.comautis.Models.Picture;
import com.lpiem_lyon1.comautis.R;

import java.util.ArrayList;

/**
 * Created by marcoloiodice on 12/09/2017.
 */

public class SlideshowDialogFragment extends DialogFragment {

    private String TAG = SlideshowDialogFragment.class.getName();
    private ArrayList<Picture> pictures;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView lblCount, lblTitle;
    private int selectedPosition = 0;

    public static SlideshowDialogFragment newInstance() {
        SlideshowDialogFragment mFragment = new SlideshowDialogFragment();
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_slider, container, false);
        viewPager= (ViewPager) view.findViewById(R.id.viewpager);
        lblCount = (TextView) view.findViewById(R.id.lbl_count);
        lblTitle = (TextView) view.findViewById(R.id.title);

        pictures = (ArrayList<Picture>) getArguments().getSerializable("pictures");
        selectedPosition = getArguments().getInt("position");

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPageChangeListener);

        setCurrentPicture(selectedPosition);

        return view;
    }

    ViewPager.OnPageChangeListener viewPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            displayMetaInto(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void setCurrentPicture(int position) {
        viewPager.setCurrentItem(position, false);
        displayMetaInto(position);
    }

    private void displayMetaInto(int position){
        lblCount.setText((position + 1) + " of " + pictures.size());
        Picture picture = pictures.get(position);
        lblTitle.setText(picture.getName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    public MotionEvent swapXY(MotionEvent motionEvent){
        float width = getView().getWidth();
        float height = getView().getHeight();

        float newX = (motionEvent.getY() / height) * width;
        float newY = (motionEvent.getX() / width) * height;

        motionEvent.setLocation(newX, newY);
        return motionEvent;
    }

    public class MyViewPagerAdapter extends PagerAdapter implements ViewPager.PageTransformer {

        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.image_fullscreen_preview, container, false);

            ImageView imageViewPreview = (ImageView) view.findViewById(R.id.iv_fullscreen_viewer);

            Picture picture = pictures.get(position);

            Glide.with(getActivity()).load(picture.getPicturePath())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViewPreview);

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return pictures.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == ((View) obj);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        //Part for change swipe orientation

        @Override
        public void transformPage(View page, float position) {
            if (position < -1) {
                // This page is way off-screen to the left
                page.setAlpha(0);
            } else if (position <= 1) {
                page.setAlpha(1);
                page.setTranslationX(page.getWidth() * -position);
                float yPosition = position * page.getHeight();
                page.setTranslationY(yPosition);
            } else {
                // This page is way off screen to the right
                page.setAlpha(0);
            }
        }

        public MotionEvent swapXY(MotionEvent motionEvent){
            float width = getView().getWidth();
            float height = getView().getHeight();

            float newX = (motionEvent.getY() / height) * width;
            float newY = (motionEvent.getX() / width) * height;

            motionEvent.setLocation(newX, newY);
            return motionEvent;
        }
    }
}