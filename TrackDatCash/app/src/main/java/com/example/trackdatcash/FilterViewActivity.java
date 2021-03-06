package com.example.trackdatcash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.List;

public class FilterViewActivity extends AppCompatActivity {
//private Spinner sprGroupAdd, sprMonthAdd, sprCategoriesAdd;
    private Spinner sprPrimaryFilter, sprSecondaryFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_view);

        Button btnFilterFilter = (Button) findViewById(R.id.btnFilterFilter);

        addToPrimarySpinner();
        addToSecondarySpinner(2);

        sprPrimaryFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sprPrimaryFilter.getSelectedItemPosition()==1)
                {
                    addToSecondarySpinner(1);
                }
                else if (sprPrimaryFilter.getSelectedItemPosition()==2)
                {
                    addToSecondarySpinner(0);
                }
                else if (sprPrimaryFilter.getSelectedItemPosition()==2)
                {
                    addToSecondarySpinner(2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnFilterFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String primary = sprPrimaryFilter.getSelectedItem().toString();
                String secondary;
                String baseURL = "http://largeproject-testing-app.herokuapp.com/expenses/";
                if (!primary.equals("All"))
                {
                    //Pull in the data from the selected drop down
                    secondary = sprSecondaryFilter.getSelectedItem().toString();

                    if(sprPrimaryFilter.getSelectedItemPosition()==1)
                    {
                        //User choosing to filter by month
                        baseURL.concat("monthMobile/");
                        baseURL.concat(secondary);

                    }

                    else
                    {
                        //User choosing to filter by category
                        baseURL.concat("categoryMobile/");
                        baseURL.concat(secondary);
                    }

                }
                else
                {

                }

                //Using data from spinners, create the URL to use
                String urlToSend = "Blah" + "Group String";

                //Using the selected value, create route & send to View Expenses Activity
                Intent todoIntent = new Intent(FilterViewActivity.this, ViewExpensesActivity.class);
                todoIntent.putExtra("url", urlToSend);
                FilterViewActivity.this.startActivity(todoIntent);
            }
        });


    }


    public void addToPrimarySpinner() {
        sprPrimaryFilter = (Spinner) findViewById(R.id.sprPrimaryFilter);
        List<String> primaryList = new ArrayList<String>();

        primaryList.add("All");
        primaryList.add("Month");
        primaryList.add("Category");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, primaryList);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        sprPrimaryFilter.setAdapter(dataAdapter);
    }

    public void addToSecondarySpinner(int mOc) {
        sprSecondaryFilter = (Spinner) findViewById(R.id.sprSecondaryFilter);
        List<String> secondaryList = new ArrayList<String>();

        //If primary filter is month, set secondary to months
        if (mOc == 1)
        {
            secondaryList.add("Jan");
            secondaryList.add("Feb");
            secondaryList.add("Mar");
            secondaryList.add("Apr");
            secondaryList.add("May");
            secondaryList.add("Jun");
            secondaryList.add("Jul");
            secondaryList.add("Aug");
            secondaryList.add("Sep");
            secondaryList.add("Oct");
            secondaryList.add("Nov");
            secondaryList.add("Dec");
        }

        //Categories were chosen in the primary filter, populate the fields
        else if (mOc==0)
        {
            secondaryList.add("Food");
            secondaryList.add("Bills");
            secondaryList.add("Entertainment");
            secondaryList.add("Other/Misc.");
        }
        else
        {
            secondaryList.add("");
        }


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, secondaryList);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        sprSecondaryFilter.setAdapter(dataAdapter);
    }

}
