package com.lpiem_lyon1.comautis;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
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

    public static final String EXTRA_PAGE_ID = "pageId";

    private String childId;
    private EditText etNamePage;
    private ArrayList<Page> mListPage = new ArrayList<>();
    private ListView mPageListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_page);

        getSupportActionBar().setTitle(R.string.choose_page);
        if(getIntent() != null){
            if(getIntent().getExtras() != null) {
                childId = getIntent().getExtras().getString(ChooseChildActivity.EXTRA_CHILD_ID);
            }
        }

        mPageListView = findViewById(R.id.lv_pages);

        loadPages();

        mPageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext() , PageActivity.class);
                intent.putExtra(EXTRA_PAGE_ID, mListPage.get(position).getId());
                startActivity(intent);
            }
        });

        //long click for delete request
        mPageListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                //Get layout inflater for dialog for delete child
                LayoutInflater inflaterDeletePage = getLayoutInflater();

                //Associate view to the layout modal dialog
                View dialogDeleteLayout = inflaterDeletePage.inflate(R.layout.dialog_delete_page, null);
                //Create a builder for the dialog
                AlertDialog.Builder mDeleteBuilder = new AlertDialog.Builder(ChoosePageActivity.this);
                //Associate the view to the builder
                mDeleteBuilder.setView(dialogDeleteLayout);

                //Setting Buttons Yes or No
                // Setting Positive "Yes" Button
                mDeleteBuilder.setPositiveButton(R.string.btn_ad_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed YES button.
                        String idPageTarget = mListPage.get(position).getId();
                        mLocalDb.deletePageById(idPageTarget, null);
                        Toast.makeText(getApplicationContext(), R.string.toast_pagedeleted,
                                Toast.LENGTH_SHORT).show();
                        loadPages();
                    }
                });
                // Setting Negative "NO" Button
                mDeleteBuilder.setNegativeButton(R.string.btn_ad_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed No button.
                        Toast.makeText(getApplicationContext(), R.string.toast_cancel, Toast.LENGTH_SHORT).show();
                    }
                });

                //Creation AlertDialog and associate to the builder showed.
                AlertDialog alertDialogDeleteChild = mDeleteBuilder.show();

                return false;
            }
        });


        FloatingActionButton fabAddPage = findViewById(R.id.fab_create_page);
        fabAddPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED) {
                    // Get the layout inflater
                    LayoutInflater inflater = getLayoutInflater();

                    //Associate view to layout modal dialog
                    View dialogAddLayout = inflater.inflate(R.layout.dialog_add_page, null);
                    //Create a builder for the dialog
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(ChoosePageActivity.this);
                    //Associate the view to the builder
                    mBuilder.setView(dialogAddLayout);
                    etNamePage = dialogAddLayout.findViewById(R.id.et_ad_page_name);

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
                                long id = mLocalDb.insertPage(myPage, null);
                                myPage.setId(Long.toString(id));
                                Intent intent = new Intent(getBaseContext() , ChoosePictureActivity.class);
                                intent.putExtra(EXTRA_PAGE_ID, myPage.getId());
                                Toast.makeText(getApplicationContext(), R.string.toast_creationpage,
                                        Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), R.string.toast_retry_creation,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    // Setting Negative "NO" Button
                    mBuilder.setNegativeButton(R.string.btn_ad_negative, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // User pressed No button.
                            Toast.makeText(getApplicationContext(), R.string.toast_cancel, Toast.LENGTH_SHORT).show();
                        }
                    });

                    //Creation AlertDialog and associate to the builder showed.
                    AlertDialog alertDialogCreatePage = mBuilder.show();
                } else {
                    //todo go perms params.
                    final Intent intentPerms = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null));
                    intentPerms.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    AlertDialog.Builder builderPerms = new AlertDialog.Builder(ChoosePageActivity.this);
                    builderPerms.setMessage(R.string.tv_permissions_redirections)
                            .setCancelable(false)
                            .setPositiveButton(R.string.btn_ad_positive, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(intentPerms);
                                }
                            });
                    AlertDialog alert = builderPerms.create();
                    alert.show();
                }
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

    @Override
    protected void onResume() {
        super.onResume();
        loadPages();
    }
}
