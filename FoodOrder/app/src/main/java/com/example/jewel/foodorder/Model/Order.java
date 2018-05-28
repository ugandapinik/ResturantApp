package com.example.jewel.foodorder.Model;

/**
 * Created by fiber on 20-Dec-17.
 */

public class Order {
    private int _id;
    private String _productId;
    private String _productName;
    private String _quantity;
    private String _price;
    private String _discount;

    public Order(){}

    public Order(int _id, String _productId, String _productName, String _quantity, String _price, String _discount) {
        this._id = _id;
        this._productId = _productId;
        this._productName = _productName;
        this._quantity = _quantity;
        this._price = _price;
        this._discount = _discount;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_productId(String _productId) {
        this._productId = _productId;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }

    public void set_quantity(String _quantity) {
        this._quantity = _quantity;
    }

    public void set_price(String _price) {
        this._price = _price;
    }

    public void set_discount(String _discount) {
        this._discount = _discount;
    }

    public int get_id() {
        return _id;
    }

    public String get_productId() {
        return _productId;
    }

    public String get_productName() {
        return _productName;
    }

    public String get_quantity() {
        return _quantity;
    }

    public String get_price() {
        return _price;
    }

    public String get_discount() {
        return _discount;
    }
}
