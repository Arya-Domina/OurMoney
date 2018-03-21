package com.example.arya.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class MyItemsFragment extends Fragment {
    LayoutInflater inflater;
    View rootView;
    ArrayList<Goods> goods;
    Gson gson = (new GsonBuilder()).create();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goods = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        goods.add(new Goods());
        goods.get(0).setDate(date.getTime());
        goods.get(0).setName("Tool");
        goods.get(0).setPrice(152);
        goods.add(new Goods());
        goods.get(1).setDate(date.getTime());
        goods.get(1).setName("Cookie");
        goods.get(1).setPrice(17);
        goods.add(new Goods());
        goods.get(2).setDate(date.getTime());
        goods.get(2).setName("Milk");
        goods.get(2).setPrice(56);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my_items, container, false);
        Button button = (Button)rootView.findViewById(R.id.button_add_item);
        this.inflater = inflater;
        showTable();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), AddItemActivity.class);
                int requestCode = 0;
                startActivityForResult(intent, requestCode);
            }
        });
        return rootView;
    }
    private TableRow addRow(final long date, final String name, final float price, int color) {
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
        tableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), AddItemActivity.class);
                intent.putExtra("date", new SimpleDateFormat("dd.MM.yy", Locale.ROOT).format(new Date(date)));
                intent.putExtra("name", name);
                intent.putExtra("price", Float.toString(price));
//                startActivityForResult(intent, requestCode);
                startActivity(intent);
            }
        });
        return tableRow;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Goods newGoods = gson.fromJson(data.getStringExtra("goods"), Goods.class);
            goods.add(newGoods);
        }
        showTable();
    }

    private void showTable() {
        TableLayout tableLayout = (TableLayout)rootView.findViewById(R.id.table_my_goods);
        tableLayout.removeAllViews();
        tableLayout.addView(inflater.inflate(R.layout.table_title, null));
        for (int i = 0; i < goods.size(); i++) {
            tableLayout.addView(addRow(
                    goods.get(i).getDate(),
                    goods.get(i).getName(),
                    goods.get(i).getPrice(),
                    i % 2 == 0 ? R.color.unevenRowBackground : R.color.evenRowBackground));
        }
    }
}
