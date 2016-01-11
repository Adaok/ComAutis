package com.lpiem_lyon1.comautis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.lpiem_lyon1.comautis.Adapters.ListChildAdapter;
import com.lpiem_lyon1.comautis.Database.LocalDataBase;
import com.lpiem_lyon1.comautis.Database.SQLDataBase;
import com.lpiem_lyon1.comautis.Models.Child;

import java.util.ArrayList;
import java.util.List;

public class ChooseChildActivity extends BaseActivity {

    private ListView mChildListView;
    SQLDataBase myDB;
    private SQLiteDatabase mDBLite;
    private SharedPreferences mSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_child);

        mChildListView = (ListView) findViewById(R.id.lv_child);


        //mDBLite = new SQLiteDatabase();

        //myDB = new SQLDataBase(getApplicationContext());
        //myDB.onCreate(mDBLite);
        //final LocalDataBase myLocDB = new LocalDataBase(mDBLite,mSharedPref);

        //init list child item
        List<Child> mChildItemList = new ArrayList<>();


        //init list view with list child items
        ListChildAdapter listChildAdapter = new ListChildAdapter(mChildItemList, getBaseContext());
        mChildListView.setAdapter(listChildAdapter);


        FloatingActionButton fabAddChild = (FloatingActionButton) findViewById(R.id.btn_add_child);
        fabAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();

                //Associate view to layout modal dialog
                View dialogAddLayout = inflater.inflate(R.layout.dialog_add_child, null);
                //Create a builder for the dialog
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ChooseChildActivity.this);
                //Associate the view to the builder
                mBuilder.setView(dialogAddLayout);

                //Setting Buttons Yes or No
                // Setting Positive "Yes" Button
                mBuilder.setPositiveButton(R.string.btn_ad_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed YES button.
                        String nameChild = String.valueOf(R.id.et_ad_child_name);
                        if (nameChild != null && nameChild.isEmpty()) {
                            //TODO
                            Child myChild = new Child();
                            myChild.setName(nameChild);
                            //myLocDB.insertChild(myChild, null);
                            //FIN DE TEST
                            Toast.makeText(getApplicationContext(), "Child created",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
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
}
