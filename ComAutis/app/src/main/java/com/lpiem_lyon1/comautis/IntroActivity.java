package com.lpiem_lyon1.comautis;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance(getString(R.string.title_slide1), getString(R.string.text_slide1), R.mipmap.ic_launcher, Color.parseColor("#03A9F4")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.title_slide2), getString(R.string.text_slide2), R.mipmap.ic_storage_mobile, Color.parseColor("#03A9F4")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.title_slide3), getString(R.string.text_slide3), R.mipmap.ic_tips, Color.parseColor("#03A9F4")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.title_slide4), getString(R.string.text_slide4), R.mipmap.ic_picture, Color.parseColor("#03A9F4")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.title_slide5), getString(R.string.text_slide5), R.mipmap.ic_computer, Color.parseColor("#03A9F4")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.title_slide6), getString(R.string.text_slide6), R.mipmap.ic_mac, Color.parseColor("#03A9F4")));

        askForPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);

        loadMainActivity();
        Toast.makeText(getApplicationContext(), getString(R.string.skip), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSlideChanged(Fragment oldFragment, Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        loadMainActivity();
    }

    public void loadMainActivity() {
        Intent intent = new Intent(this,ChooseChildActivity.class);
        startActivity(intent);
    }
}
