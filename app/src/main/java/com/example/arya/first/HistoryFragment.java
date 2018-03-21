package com.example.arya.first;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HistoryFragment extends Fragment{
    LayoutInflater inflater;
    ArrayList<Goods> testGoods;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    String json = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        this.inflater = inflater;

        testGoods = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        for (int i = 0; i < 22; i+=3) {
            date.setDate(date.getDate()-1);
            testGoods.add(new Goods());
            testGoods.get(i).setDate(date.getTime());
            testGoods.get(i).setName("Tool");
            testGoods.get(i).setPrice(152);
            testGoods.add(new Goods());
            testGoods.get(i+1).setDate(date.getTime());
            testGoods.get(i+1).setName("Cookie");
            testGoods.get(i+1).setPrice(17);
            testGoods.add(new Goods());
            testGoods.get(i+2).setDate(date.getTime());
            testGoods.get(i+2).setName("Milk");
            testGoods.get(i+2).setPrice(56);
        }

        TableLayout tableLayout = (TableLayout)rootView.findViewById(R.id.table_goods_history);
        tableLayout.addView(inflater.inflate(R.layout.table_title, null));
        for (int i = 0; i < testGoods.size(); i++) {
            tableLayout.addView(addRow(
                    testGoods.get(i).getDate(),
                    testGoods.get(i).getName(),
                    testGoods.get(i).getPrice(),
                    i % 2 == 0 ? R.color.unevenRowBackground : R.color.evenRowBackground));
        }
        Log.v("Main", json);

        return rootView;
    }
    private TableRow addRow(long date, String name, float price, int color) {

        TableRow tableRow = (TableRow)inflater.inflate(R.layout.table_row, null);
        TextView textView = (TextView) tableRow.getChildAt(0);
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
