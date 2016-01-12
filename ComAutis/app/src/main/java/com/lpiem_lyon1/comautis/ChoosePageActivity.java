package com.lpiem_lyon1.comautis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.lpiem_lyon1.comautis.Adapters.ListPageAdapter;
import com.lpiem_lyon1.comautis.Database.RequestCallback;
import com.lpiem_lyon1.comautis.Models.Model;
import com.lpiem_lyon1.comautis.Models.Page;

import java.util.ArrayList;
import java.util.List;

public class ChoosePageActivity extends BaseActivity {

    private String childId;

    private EditText etNamePage;
    private ArrayList<Page> mListPage = new ArrayList<>();
    private ListView mPageListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_page);

        if(getIntent() != null){
            if(getIntent().getExtras() != null) {
                childId = getIntent().getExtras().getString(ChooseChildActivity.EXTRA_CHILD_ID);
            }
        }

        mPageListView = (ListView) findViewById(R.id.lv_pages);

        loadPages();


        FloatingActionButton fabAddPage = (FloatingActionButton) findViewById(R.id.fab_create_page);
        fabAddPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();

                //Associate view to layout modal dialog
                View dialogAddLayout = inflater.inflate(R.layout.dialog_add_page, null);
                //Create a builder for the dialog
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ChoosePageActivity.this);
                //Associate the view to the builder
                mBuilder.setView(dialogAddLayout);
                etNamePage = (EditText) dialogAddLayout.findViewById(R.id.et_ad_page_name);

                //Setting Buttons Yes or No
                // Setting Positive "Yes" Button
                mBuilder.setPositiveButton(R.string.btn_ad_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed YES button.
                        String namePage = etNamePage.getText().toString();

                        if (namePage != null && !namePage.isEmpty()) {
                            //TODO
                            Page myPage = new Page();
                            myPage.setName(namePage);
                            myPage.setChildId(childId);
                            mLocalDb.insertPage(myPage, null);
                            Intent intent = new Intent(getBaseContext() , ChoosePictureActivity.class);
                            Toast.makeText(getApplicationContext(), "Page created",
                                    Toast.LENGTH_SHORT).show();
                            //loadPages();
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Retry and please give a name",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                // Setting Negative "NO" Button
                mBuilder.setNegativeButton(R.string.btn_ad_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed No button.
                        Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                //Creation AlertDialog and associate to the builder showed.
                AlertDialog alertDialogCreatePage = mBuilder.show();
            }
        });
    }

    private void loadPages(){
        mLocalDb.requestPageByChild(childId,new RequestCallback() {
            @Override
            public void onResult(List<? extends Model> entities) {
                mListPage.clear();
                for (int i = 0; i < entities.size(); i++) {
                    mListPage.add((Page) entities.get(i));
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        });

        //init list view with list pages items
        ListPageAdapter listPageAdapter = new ListPageAdapter(mListPage, getBaseContext());
        mPageListView.setAdapter(listPageAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentListChild = new Intent(getBaseContext(), ChooseChildActivity.class);
        startActivity(intentListChild);
    }
}
