package com.example.b21yassine.groceries.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.b21yassine.groceries.R;
import com.example.b21yassine.groceries.adapters.RecyclerAdapter;
import com.example.b21yassine.groceries.database.DatabaseHandler;

public class ListActivity extends AppCompatActivity {

    private RecyclerView groceries_recycler;
    private RecyclerAdapter adapter;

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        databaseHandler =new DatabaseHandler(this);

        groceries_recycler =(RecyclerView) findViewById(R.id.groceries_recycler);

        adapter =new RecyclerAdapter(this, databaseHandler.getAllGroceries());
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);

        groceries_recycler.setAdapter(adapter);
        groceries_recycler.setLayoutManager(linearLayoutManager);

    }


}
