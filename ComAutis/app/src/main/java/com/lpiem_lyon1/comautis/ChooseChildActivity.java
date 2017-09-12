package com.lpiem_lyon1.comautis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.lpiem_lyon1.comautis.Adapters.ListChildAdapter;
import com.lpiem_lyon1.comautis.Database.RequestCallback;
import com.lpiem_lyon1.comautis.Models.Child;
import com.lpiem_lyon1.comautis.Models.Model;
import com.lpiem_lyon1.comautis.Models.Page;

import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.List;

public class ChooseChildActivity extends BaseActivity {
    public static final String EXTRA_CHILD_ID = "child_id";

    private ListView mChildListView;
    private EditText etNameChild;
    private List<Child> mListChild = new ArrayList<Child>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_choose_child);

        getSupportActionBar().setTitle(R.string.choose_child);
        PictureUtils.createPictureDirectory();

        mChildListView = (ListView) findViewById(R.id.lv_child);

        //init list child item
        loadChild();

        mChildListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentName = new Intent(getBaseContext(), ChoosePageActivity.class);
                intentName.putExtra(EXTRA_CHILD_ID, mListChild.get(position).getId());
                startActivity(intentName);
            }
        });

        //long click for delete request
        mChildListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                //Get layout inflater for dialog for delete child
                LayoutInflater inflaterDeleteChild = getLayoutInflater();

                //Associate view to the layout modal dialog
                View dialogDeleteLayout = inflaterDeleteChild.inflate(R.layout.dialog_delete_child, null);
                //Create a builder for the dialog
                AlertDialog.Builder mDeleteBuilder = new AlertDialog.Builder(ChooseChildActivity.this);
                //Associate the view to the builder
                mDeleteBuilder.setView(dialogDeleteLayout);

                //Setting Buttons Yes or No
                // Setting Positive "Yes" Button
                mDeleteBuilder.setPositiveButton(R.string.btn_ad_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed YES button.
                            //TODO
                            String idChildTarget = mListChild.get(position).getId();
                            if (checkPageInChild(idChildTarget) != 0){
                                mLocalDb.deletePageByChild(idChildTarget, null);
                            }
                            mLocalDb.deleteChildById(idChildTarget, null);
                        Toast.makeText(getApplicationContext(), "Child deleted",
                                    Toast.LENGTH_SHORT).show();
                            loadChild();
                    }
                });
                // Setting Negative "NO" Button
                mDeleteBuilder.setNegativeButton(R.string.btn_ad_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed No button.
                        Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                //Creation AlertDialog and associate to the builder showed.
                AlertDialog alertDialogDeleteChild = mDeleteBuilder.show();

                return false;
            }
        });

        FloatingActionButton fabAddChild = (FloatingActionButton) findViewById(R.id.btn_add_child);
        fabAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the layout inflater
                LayoutInflater inflaterAddChild = getLayoutInflater();

                //Associate view to layout modal dialog
                View dialogAddLayout = inflaterAddChild.inflate(R.layout.dialog_add_child, null);
                //Create a builder for the dialog
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ChooseChildActivity.this);
                //Associate the view to the builder
                mBuilder.setView(dialogAddLayout);
                etNameChild = (EditText) dialogAddLayout.findViewById(R.id.et_ad_child_name);

                //Setting Buttons Yes or No
                // Setting Positive "Yes" Button
                mBuilder.setPositiveButton(R.string.btn_ad_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed YES button.
                        String nameChild = etNameChild.getText().toString();

                        if (nameChild != null && !nameChild.isEmpty()) {
                            //TODO
                            Child myChild = new Child();
                            myChild.setName(nameChild);
                            mLocalDb.insertChild(myChild, null);
                            Toast.makeText(getApplicationContext(), "Child created",
                                    Toast.LENGTH_SHORT).show();
                            loadChild();
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
                AlertDialog alertDialogCreateChild = mBuilder.show();
            }
        });
    }

    private void loadChild(){
        mLocalDb.requestChild(new RequestCallback() {
            @Override
            public void onResult(List<? extends Model> entities) {
                mListChild.clear();
                for (int i = 0; i < entities.size(); i++) {
                    mListChild.add((Child) entities.get(i));
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        });

        //init list view with list child items
        ListChildAdapter listChildAdapter = new ListChildAdapter(mListChild, getBaseContext());
        mChildListView.setAdapter(listChildAdapter);
    }

    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }

    private int checkPageInChild(String idChildTarget) {
        List<Page> listPageInChild = new ArrayList<>();
        mLocalDb.requestPageByChild(idChildTarget, new RequestCallback() {
            @Override
            public void onResult(List<? extends Model> entities) {
                List listPageInChild = (List<Page>) entities;
            }

            @Override
            public void onError(Throwable error) {

            }
        });
        return listPageInChild.size();
    }

}
