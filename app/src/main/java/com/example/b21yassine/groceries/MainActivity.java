package com.example.b21yassine.groceries;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.b21yassine.groceries.activities.ListActivity;
import com.example.b21yassine.groceries.database.DatabaseHandler;
import com.example.b21yassine.groceries.models.Grocery;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler =new DatabaseHandler(this);

        fab =(FloatingActionButton) findViewById(R.id.open_add_grocery_modal);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(getApplicationContext(), ListActivity.class);

                startActivity(intent);

                ///createAddGroceryModal();
            }
        });

    }


    public void createAddGroceryModal(){

        View view =getLayoutInflater().inflate(R.layout.add_grocery_modal, null);
        TextView modal_title =(TextView) view.findViewById(R.id.add_modal_main_text);
        final EditText grocery_name =(EditText) view.findViewById(R.id.add_grocery_name);
        final EditText grocery_quantity =(EditText) view.findViewById(R.id.add_grocery_quantity);
        Button save_grocery_btn =(Button) view.findViewById(R.id.add_grocery_save_btn);

        final Grocery grocery =new Grocery();

        save_grocery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                grocery.setName(grocery_name.getText().toString());
                grocery.setQuantity(grocery_quantity.getText().toString());

                createGrocery(grocery);

            }
        });

        builder =new AlertDialog.Builder(this);
        builder.setView(view);

        dialog =builder.create();
        dialog.show();

    }

    public void createGrocery(Grocery grocery){

        databaseHandler.createGrocery(grocery);

        Intent intent =new Intent(this, ListActivity.class);

        startActivity(intent);

    }



}
