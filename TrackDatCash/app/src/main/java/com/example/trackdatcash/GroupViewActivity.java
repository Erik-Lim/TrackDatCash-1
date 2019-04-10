package com.example.trackdatcash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GroupViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_view);

        Button btnRtoMMfGV = (Button) findViewById(R.id.btnRtoMMfGV);
        Button btnGroupViewGV = (Button) findViewById(R.id.btnGroupViewGV);

        //Return to the Main Menu
        btnRtoMMfGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent todoIntent = new Intent(GroupViewActivity.this, MainMenuActivity.class);
                GroupViewActivity.this.startActivity(todoIntent);
            }
        });

        btnGroupViewGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Take in the Group code to create the url to find the expense data for that group
                EditText etGroupCodeGV = (EditText) findViewById(R.id.etGroupCodeGV);
                String urlToSend = "Blah" + "Group String";
                Intent todoIntent = new Intent(GroupViewActivity.this, ViewExpensesActivity.class);
                todoIntent.putExtra("url", urlToSend);
                GroupViewActivity.this.startActivity(todoIntent);
            }
        });
    }
}
