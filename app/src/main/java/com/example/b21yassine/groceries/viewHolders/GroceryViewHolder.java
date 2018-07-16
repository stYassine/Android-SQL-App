package com.example.b21yassine.groceries.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.b21yassine.groceries.R;

public class GroceryViewHolder extends RecyclerView.ViewHolder {

    public int id;
    public TextView grocery_name;
    public TextView grocery_quantity;
    public TextView grocery_date;

    public Button edit_grocery_btn;
    public Button delete_grocery_btn;

    public CardView single_grocery_cotainer;


    public GroceryViewHolder(@NonNull View itemView) {
        super(itemView);

        grocery_name =(TextView) itemView.findViewById(R.id.grocery_name);
        grocery_quantity =(TextView) itemView.findViewById(R.id.grocery_quantity);
        grocery_date =(TextView) itemView.findViewById(R.id.grocery_date);

        edit_grocery_btn =(Button) itemView.findViewById(R.id.grocery_edit_btn);
        delete_grocery_btn =(Button) itemView.findViewById(R.id.grocery_delete_btn);

        single_grocery_cotainer =(CardView) itemView.findViewById(R.id.single_grocery_container);

    }


}
