package com.example.b21yassine.groceries.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.b21yassine.groceries.R;
import com.example.b21yassine.groceries.activities.DetailsActivity;
import com.example.b21yassine.groceries.database.DatabaseHandler;
import com.example.b21yassine.groceries.models.Grocery;
import com.example.b21yassine.groceries.viewHolders.GroceryViewHolder;

import java.util.List;
import java.util.zip.Inflater;

public class RecyclerAdapter extends RecyclerView.Adapter<GroceryViewHolder> implements View.OnClickListener{

    public List<Grocery> groceries_list;
    public Context context;

    private DatabaseHandler databaseHandler;

    public RecyclerAdapter(Context context, List<Grocery> groceries_list) {
        this.context = context;
        this.groceries_list = groceries_list;
    }

    private AlertDialog.Builder delete_builder;
    private AlertDialog delete_dialog;


    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_grocery_layout, parent, false);

        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {

        final Grocery grocery =groceries_list.get(position);

        holder.grocery_name.setText(grocery.getName());
        holder.grocery_quantity.setText(grocery.getQuantity());
        holder.grocery_date.setText(grocery.getDate_added());

        holder.id =grocery.getId();

        holder.edit_grocery_btn.setOnClickListener(this);
        holder.delete_grocery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDeleteGroceryDialog(grocery.getId());
            }
        });

        holder.single_grocery_cotainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent details_activity =new Intent(context, DetailsActivity.class);
                details_activity.putExtra("id", grocery.getId());
                details_activity.putExtra("name", grocery.getName());
                details_activity.putExtra("quantity", grocery.getQuantity());
                details_activity.putExtra("date", grocery.getDate_added());

                context.startActivity(details_activity);

            }
        });

    }

    @Override
    public int getItemCount() {
        return groceries_list.size();
    }


    @Override
    public void onClick(View view) {

    }


    public void createDeleteGroceryDialog(int id){

        final int clicked_item_id =id;

        View view=LayoutInflater.from(context).inflate(R.layout.delete_dialog, null);
        Button delete_yes_btn =view.findViewById(R.id.dm_yes);
        Button delete_no_btn =view.findViewById(R.id.dm_no);

        delete_no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_dialog.dismiss();
            }
        });

        delete_yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseHandler =new DatabaseHandler(context);

                databaseHandler.deleteGrocery(clicked_item_id);

            }
        });

        delete_builder =new AlertDialog.Builder(context);


        delete_builder.setView(view);

        delete_dialog =delete_builder.create();
        delete_dialog.show();

    }


}
