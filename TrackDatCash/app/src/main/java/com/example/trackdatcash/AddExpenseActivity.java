package com.example.trackdatcash;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddExpenseActivity extends AppCompatActivity {

    private Spinner sprGroupAdd, sprMonthAdd, sprCategoriesAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        addToCategorySpinner();
        addToMonthSpinner();
        addToGroupSpinner();

        //Automatically set the month, day and year to the current day
        Date date = new Date();
        String strDateFormat = "yyyyMMdd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        final EditText etDayAdd = findViewById(R.id.etDayAdd);
        final EditText etYearAdd = findViewById(R.id.etYearAdd);
        etDayAdd.setText(formattedDate.substring(6,8));
        etYearAdd.setText(formattedDate.substring(0,4));
        sprMonthAdd.setSelection(Integer.parseInt(formattedDate.substring(4,6))-1);

        //Init buttons
        Button btnExpenseAdd = (Button) findViewById(R.id.btnExpenseAdd);
        Button btnRtoMMfAE = (Button) findViewById(R.id.btnRtoMMfAE);

        //Toast popup extras
        final Context context = getApplicationContext();
        final CharSequence textAddSuccess = "Add Successful";
        final int duration = Toast.LENGTH_LONG;

        btnRtoMMfAE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent todoIntent = new Intent(AddExpenseActivity.this, MainMenuActivity.class);
                AddExpenseActivity.this.startActivity(todoIntent);
            }
        });

        btnExpenseAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Take in all data fields
                EditText etDescriptionAdd = (EditText) findViewById(R.id.etDescriptionAdd);
                EditText etAmountAdd = (EditText) findViewById(R.id.etAmountAdd);
                String decription = etDescriptionAdd.getText().toString();
                String amount = etAmountAdd.getText().toString();
                String month = sprMonthAdd.getSelectedItem().toString();
                String day = etDayAdd.getText().toString();
                String year = etYearAdd.getText().toString();
                String category = sprCategoriesAdd.getSelectedItem().toString();
                String group = sprGroupAdd.getSelectedItem().toString();

                if (decription.length()==0 || amount.length()==0 || day.length()==0 || year.length()==0)
                {
                    CharSequence textAddFail = "Add Failed - All fields required";
                    Toast toastRegFail = Toast.makeText(context, textAddFail, duration);
                    toastRegFail.show();
                    return;
                }


//Temporary success only for add button
                //Add was successful, return to main menu
                Toast toastRegSuccess = Toast.makeText(context, textAddSuccess, duration);
                toastRegSuccess.show();
                Intent todoIntent = new Intent(AddExpenseActivity.this, MainMenuActivity.class);
                AddExpenseActivity.this.startActivity(todoIntent);



            }
        });

    }

    public void addToMonthSpinner() {
        sprMonthAdd = (Spinner) findViewById(R.id.sprMonthAdd);
        List<String> monthList = new ArrayList<String>();

        monthList.add("Jan");
        monthList.add("Feb");
        monthList.add("Mar");
        monthList.add("Apr");
        monthList.add("May");
        monthList.add("Jun");
        monthList.add("Jul");
        monthList.add("Aug");
        monthList.add("Sep");
        monthList.add("Oct");
        monthList.add("Nov");
        monthList.add("Dec");

        ArrayAdapter<String> dataAdapterM = new ArrayAdapter<String>(this,
                R.layout.spinner_item, monthList);
        dataAdapterM.setDropDownViewResource(R.layout.spinner_item);
        sprMonthAdd.setAdapter(dataAdapterM);
    }

    public void addToGroupSpinner() {
        sprGroupAdd = (Spinner) findViewById(R.id.sprGroupAdd);
        List<String> groupList = new ArrayList<String>();

        groupList.add("None");

        //Iterate through user's groups, and add them to List

        ArrayAdapter<String> dataAdapterM = new ArrayAdapter<String>(this,
                R.layout.spinner_item, groupList);
        dataAdapterM.setDropDownViewResource(R.layout.spinner_item);
        sprGroupAdd.setAdapter(dataAdapterM);
    }

    public void addToCategorySpinner() {
        sprCategoriesAdd = (Spinner) findViewById(R.id.sprCategoriesAdd);
        List<String> catList = new ArrayList<String>();

        catList.add("Food");
        catList.add("Bills");
        catList.add("Entertainment");
        catList.add("Other/Misc.");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, catList);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        sprCategoriesAdd.setAdapter(dataAdapter);
    }

}
