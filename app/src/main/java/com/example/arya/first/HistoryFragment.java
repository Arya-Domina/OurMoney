package com.example.arya.first;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HistoryFragment extends Fragment{
    LayoutInflater inflater;
    Goods[] goods;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        this.inflater = inflater;

        goods = new Goods[24];
        for (int i = 0; i < goods.length; i++) {
            goods[i] = new Goods();
        }
        Date date = new Date(System.currentTimeMillis());
        for (int i = 0; i < 22; i+=3) {
            date.setDate(date.getDate()-1);
            goods[i].setDate(date.getTime());
            goods[i].setName("Tool");
            goods[i].setPrice(152);
            goods[i+1].setDate(date.getTime());
            goods[i+1].setName("Cookie");
            goods[i+1].setPrice(17);
            goods[i+2].setDate(date.getTime());
            goods[i+2].setName("Milk");
            goods[i+2].setPrice(56);
        }

        TableLayout tableLayout = (TableLayout)rootView.findViewById(R.id.table_goods_history);
        for (int i = 0; i < goods.length; i++) {
            tableLayout.addView(addRow(
                    goods[i].getDate(),
                    goods[i].getName(),
                    goods[i].getPrice(),
                    i % 2 == 0 ? R.color.unevenRowBackground : R.color.evenRowBackground));
        }

        return rootView;
    }
    private TableRow addRow(long date, String name, float price, int color) {

        TableRow tableRow = (TableRow)inflater.inflate(R.layout.table_row, null);
        TextView textView = (TextView) tableRow.getChildAt(0);
//        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy", Locale.ROOT);
//        textView.setText(formatter.format(new Date(date)));
        textView.setText(new SimpleDateFormat("dd.MM.yy", Locale.ROOT).format(new Date(date)));
        textView.setBackgroundResource(color);
        textView = (TextView) tableRow.getChildAt(1);
        textView.setText(name);
        textView.setBackgroundResource(color);
        textView = (TextView) tableRow.getChildAt(2);
        textView.setText(Float.toString(price));
        textView.setBackgroundResource(color);
        return tableRow;
    }
}
