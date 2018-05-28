package com.example.jewel.foodorder.UI;

import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.jewel.foodorder.DB.DatabaseHandler;
import com.example.jewel.foodorder.Model.Food;
import com.example.jewel.foodorder.Model.Order;
import com.example.jewel.foodorder.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class FoodDetails extends AppCompatActivity implements View.OnClickListener {

    private TextView food_name;
    private TextView food_price;
    private TextView food_description;

    private ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String foodId = "";

    FirebaseDatabase database;
    private DatabaseHandler handler;
    DatabaseReference food;
    Food currentFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);


        // Firebase
        database = FirebaseDatabase.getInstance();
        food = database.getReference("Foods");

        // Init View
        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);
        btnCart = (FloatingActionButton) findViewById(R.id.btnCart);
        food_name = (TextView) findViewById(R.id.food_name);
        food_price = (TextView) findViewById(R.id.food_price);
        food_description = (TextView) findViewById(R.id.food_description);
        food_image = (ImageView) findViewById(R.id.img_food);


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollaseAppBar);


        // Get Food if from intent
        if (getIntent() != null){
            foodId = getIntent().getStringExtra("FoodId");
            if (!foodId.isEmpty()){
                getDetailFood(foodId);
            }
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        btnCart.setOnClickListener(this);
    }

    /**
     * Get the single Food Details
     * @param foodId
     */
    private void getDetailFood(final String foodId) {

        food.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentFood = dataSnapshot.getValue(Food.class);
                Picasso.with(getBaseContext()).load(currentFood.getImage())
                        .into(food_image);

                collapsingToolbarLayout.setTitle(currentFood.getName());

                food_name.setText(currentFood.getName());
                food_price.setText(currentFood.getPrice());
                food_description.setText(currentFood.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCart:
                //showMessage("Display Message");
                handler = new DatabaseHandler(FoodDetails.this);

                Order order = new Order();
                order.set_productId(foodId);
                order.set_productName(currentFood.getName());
                order.set_price(currentFood.getPrice());
                order.set_discount(currentFood.getDiscount());
                order.set_quantity(numberButton.getNumber());

                handler.addOrder(order);
                showMessage("Order Added Successfully!");

                return;
        }
    }


    private void showMessage(String message) {
        Toast.makeText(FoodDetails.this, message, Toast.LENGTH_SHORT).show();
    }


}
