package com.example.b21yassine.groceries.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.b21yassine.groceries.R;

public class DetailsActivity extends AppCompatActivity {

    private TextView d_grocery_name;
    private TextView d_grocery_quantity;
    private TextView d_grocery_date;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        d_grocery_name =(TextView) findViewById(R.id.d_grocery_name);
        d_grocery_quantity =(TextView) findViewById(R.id.d_grocery_quantity);
        d_grocery_date =(TextView) findViewById(R.id.d_grocery_date);

        Bundle bundle =getIntent().getExtras();

        if(bundle != null){

            d_grocery_name.setText(bundle.getString("name"));
            d_grocery_quantity.setText(bundle.getString("quantity"));
            d_grocery_date.setText(bundle.getString("date"));

            id =bundle.getInt("id");

        }


    }


}
