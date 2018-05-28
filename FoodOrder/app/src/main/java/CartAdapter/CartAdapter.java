package CartAdapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.jewel.foodorder.Model.Order;
import com.example.jewel.foodorder.R;
import com.example.jewel.foodorder.ViewHolder.CartViewHolder;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by fiber on 17-Jan-18.
 */


public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<Order> orderList = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_single_layout, parent, false);
        return  new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRound("" + orderList.get(position).get_quantity(), Color.RED);
        holder.cartCountTV.setImageDrawable(drawable);


        Locale locale = new Locale("en", "US");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(orderList.get(position).get_price())) * (Integer.parseInt(orderList.get(position).get_quantity()));
        holder.cartPriceTV.setText(format.format(price));
        holder.cartNameTV.setText(orderList.get(position).get_productName());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}