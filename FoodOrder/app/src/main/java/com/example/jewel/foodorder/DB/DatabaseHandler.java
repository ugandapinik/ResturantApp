package com.example.jewel.foodorder.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jewel.foodorder.Model.Order;
import com.example.jewel.foodorder.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fiber on 20-Dec-17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;
    private static String DATABASE_NAME = "foodorderManager";

    // Contacts Table Name
    private static final String TABLE_ORDERS = "food_order";

    // food order table columns
    private static final String KEY_ID = "id";
    private static final String KEY_PRODUCT_ID = "product_id";
    private static final String KEY_PRODUCT_NAME = "product_name";
    private static final String KEY_QUANTITY = "quanitity";
    private static final String KEY_PRICE = "price";
    private static final String KEY_DISCOUNT = "discount";


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_ORDER_TABLE = "CREATE TABLE " + TABLE_ORDERS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_PRODUCT_ID + " TEXT, "
                + KEY_PRODUCT_NAME + " TEXT, "
                + KEY_QUANTITY + " TEXT,"
                + KEY_PRICE + " TEXT,"
                + KEY_DISCOUNT + " INTEGER"
                + ")";

        database.execSQL(CREATE_ORDER_TABLE);
        Log.d("Database Operation", "Database Created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // Drop older table existed
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);

        // Create tables again
        onCreate(database);
        Log.d("Database Operation", "Database Upgraded!");
    }


    // Adding new order
    public void addOrder(Order order){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_ID, order.get_productId());
        values.put(KEY_PRODUCT_NAME, order.get_productName());
        values.put(KEY_QUANTITY, order.get_quantity());
        values.put(KEY_PRICE, order.get_price());
        values.put(KEY_DISCOUNT, order.get_discount());

        // Inserting Row
        database.insert(TABLE_ORDERS, null, values);
        Log.d("Table Operation", "Add Order!");
        database.close();
        // Closing Database
    }


    // Getting Single Order
    public Order getOrder(int orderId){
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(TABLE_ORDERS,
                new String[] {KEY_PRODUCT_ID, KEY_PRODUCT_NAME, KEY_QUANTITY, KEY_PRICE, KEY_DISCOUNT},
                KEY_ID + "=?",
                new String[]{ String.valueOf(orderId) }, null, null, null, null
        );


        if (cursor != null){
            cursor.moveToFirst();
        }

        Order order = new Order();
        order.set_id(Integer.parseInt(cursor.getString(0)));
        order.set_productId(cursor.getString(1));
        order.set_productName(cursor.getString(2));
        order.set_quantity(cursor.getString(3));
        order.set_price(cursor.getString(4));
        order.set_discount(cursor.getString(5));

        //return orderObject;
        Log.d("Table Operation", "Get Order!");
        return order;

    }

    // Getting All Order
    public List<Order> getAllOrders(){
        List<Order> orderList = new ArrayList<Order>();
        // Select All Query
        String[] columns = {
                KEY_ID,
                KEY_PRODUCT_ID,
                KEY_PRODUCT_NAME,
                KEY_QUANTITY,
                KEY_PRICE,
                KEY_DISCOUNT,
        };

        // SORTING ORDERS
        String sortOrder = KEY_ID + " ASC";
        List<Order> orders = new ArrayList<Order>();
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(TABLE_ORDERS,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()){
            do {
                Order order = new Order();
                order.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                order.set_productName(cursor.getString(cursor.getColumnIndex(KEY_PRODUCT_NAME)));
                order.set_quantity(cursor.getString(cursor.getColumnIndex(KEY_QUANTITY)));
                order.set_price(cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
                order.set_discount(cursor.getString(cursor.getColumnIndex(KEY_DISCOUNT)));
                orders.add(order);

            }while(cursor.moveToNext());
        }

        cursor.close();
        database.close();
        Log.d("Table Operation", "Get All Orders!");
        return orders;
    }


    public void deleteOrder(Order order){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_ORDERS, KEY_ID + " =?", new String[] { String.valueOf(order.get_id())});
        Log.d("Table Operation", "Delete Order!");
        database.close();
    }

}
