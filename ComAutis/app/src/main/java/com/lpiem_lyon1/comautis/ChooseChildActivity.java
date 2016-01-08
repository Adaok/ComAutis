package com.lpiem_lyon1.comautis;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.lpiem_lyon1.comautis.Adapters.ListChildAdapter;
import com.lpiem_lyon1.comautis.Models.Child;

import java.util.ArrayList;
import java.util.List;

public class ChooseChildActivity extends BaseActivity {

    private ListView mChildListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_child);

        mChildListView = (ListView) findViewById(R.id.lv_child);

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
                mBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed YES button.
                        //TODO
                        //Call InsertChild()
                        Toast.makeText(getApplicationContext(), "Child created",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                // Setting Negative "NO" Button
                mBuilder.setNegativeButton("CANCELLED", new DialogInterface.OnClickListener() {
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
