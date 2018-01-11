package com.example.miha.ep_trgovina_android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Miha on 8. 01. 2018.
 */

public class ProductAdapter extends ArrayAdapter<Product> implements Serializable {

    public ProductAdapter(Context context) {
        super(context, 0, new ArrayList<Product>());
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Product product = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.productlist_element, parent, false);
        }

        final TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
        final TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
        final TextView tvRating = (TextView) convertView.findViewById(R.id.tv_rating);

        tvTitle.setText(product.naziv);
        tvPrice.setText(String.valueOf(product.cena) + " EUR");
        tvRating.setText(String.valueOf("Ocena: " + product.ocena));

        return convertView;
    }
}
