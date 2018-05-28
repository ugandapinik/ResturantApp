package com.example.jewel.foodorder.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jewel.foodorder.Interface.ItemClickListener;
import com.example.jewel.foodorder.R;

/**
 * Created by fiber on 19-Feb-18.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView OrderId;
    public TextView OrderStatus;
    public TextView OrderPhone;
    public TextView OrderAddress;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);
        OrderAddress = (TextView) itemView.findViewById(R.id.order_address);
        OrderId = (TextView) itemView.findViewById(R.id.order_id);
        OrderStatus = (TextView) itemView.findViewById(R.id.order_status);
        OrderPhone = (TextView) itemView.findViewById(R.id.order_phone);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

    }
}
