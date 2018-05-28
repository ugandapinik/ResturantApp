package com.example.jewel.foodorder.UI;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jewel.foodorder.DB.DatabaseHandler;
import com.example.jewel.foodorder.Model.Order;
import com.example.jewel.foodorder.Model.Request;
import com.example.jewel.foodorder.R;
import com.example.jewel.foodorder.Utils.Utils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import CartAdapter.CartAdapter;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    private TextView totalPriceTV;
    private Button btnPlace;


    private List<Order> orderList = new ArrayList<>();
    private DatabaseHandler databaseHandler;
    private CartAdapter cartAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Get Firebase Information
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");


        // Init
        recyclerView = (RecyclerView) findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(CartActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        
        
        totalPriceTV = (TextView) findViewById(R.id.total);
        btnPlace = (Button) findViewById(R.id.btnPlaceOder);
        
        loadListFood();
                
    }


    @Override
    protected void onStart() {
        super.onStart();

        btnPlace.setOnClickListener(this);
    }

    private void loadListFood() {
        databaseHandler = new DatabaseHandler(CartActivity.this);
        orderList = databaseHandler.getAllOrders();
        cartAdapter = new CartAdapter(orderList, CartActivity.this);
        recyclerView.setAdapter(cartAdapter);

        // Calculate Total Price;
        int total = 0;
        for (Order order:orderList){
            total += (Integer.parseInt(order.get_price())) * (Integer.parseInt(order.get_quantity()));

        }

        Locale locale = new Locale("en", "US");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        totalPriceTV.setText(format.format(total));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlaceOder:
                showAlertDialog();
                break;
        }

    }

    private void showAlertDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CartActivity.this);
        alertDialog.setTitle("One more step!");
        alertDialog.setMessage("Enter your address: ");

        final EditText editAddress = new EditText(CartActivity.this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );


        editAddress.setLayoutParams(layoutParams);
        alertDialog.setView(editAddress);
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Request request = new Request();
                request.setPhone(Utils.currentUser.getPhone());
                request.setName(Utils.currentUser.getName());
                request.setAddress(editAddress.getText().toString());
                request.setStatus("0");
                request.setTotal(totalPriceTV.getText().toString());
                request.setFoods(orderList);


                // Submitted to Firebase

                requests.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(request);


                // Delete Cart Result
                Toast.makeText(CartActivity.this, "Thank you, Order Placed!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        alertDialog.show();

    }
}
