package com.example.jewel.foodorder.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.jewel.foodorder.Model.Order;
import com.example.jewel.foodorder.Model.Request;
import com.example.jewel.foodorder.R;
import com.example.jewel.foodorder.Utils.Utils;
import com.example.jewel.foodorder.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderStatusActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;
    private FirebaseDatabase database;
    private DatabaseReference request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        database = FirebaseDatabase.getInstance();
        request = database.getReference("Requests");


        recyclerView = (RecyclerView) findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        loadOrders(Utils.currentUser.getPhone());
    }

    private void loadOrders(final String phone) {
        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(Request.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                request.orderByChild("phone")
                        .equalTo(phone)) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {
                viewHolder.OrderId.setText(adapter.getRef(position).getKey());
                viewHolder.OrderStatus.setText(convertCodeToStatus(model.getStatus()));
                viewHolder.OrderAddress.setText(model.getAddress());
                viewHolder.OrderPhone.setText(model.getPhone());
            }
        };

        recyclerView.setAdapter(adapter);

    }

    /**
     * @desc Code to Status
     * @param status
     * @return
     */
    public String convertCodeToStatus(String status) {
        Log.d("Alert", status.toString());
        if (status.equals("0"))
            return "Placed!";

        else if (status.equals("1"))
            return "On The Way!";

        else
            return "Shipped!";

    }
}
