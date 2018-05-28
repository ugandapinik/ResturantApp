package com.example.jewel.foodorder.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jewel.foodorder.Interface.ItemClickListener;
import com.example.jewel.foodorder.R;

/**
 * Created by fiber on 17-Jan-18.
 */

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView cartNameTV;
    public TextView cartPriceTV;
    public ImageView cartCountTV;


    private ItemClickListener itemClickListener;


    public CartViewHolder(View itemView) {
        super(itemView);
        cartNameTV = (TextView) itemView.findViewById(R.id.cart_item_name);
        cartPriceTV = (TextView) itemView.findViewById(R.id.cart_item_price);
        cartCountTV = (ImageView) itemView.findViewById(R.id.cart_item_count);

    }

    @Override
    public void onClick(View v) {

    }
}
