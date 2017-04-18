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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MyItemsFragment extends Fragment {
    LayoutInflater inflater;
//    Goods[] goods;
    ArrayList<Goods> goods;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_items, container, false);
        Button button = (Button)rootView.findViewById(R.id.button_add_item);
        this.inflater = inflater;
        goods = new ArrayList<>();
//        goods = new Goods[3];
//        for (int i = 0; i < goods.length; i++) {
//            goods[i] = new Goods();
//        }
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


        TableLayout tableLayout = (TableLayout)rootView.findViewById(R.id.table_my_goods);
        for (int i = 0; i < goods.size(); i++) {
            tableLayout.addView(addRow(
                    goods.get(i).getDate(),
                    goods.get(i).getName(),
                    goods.get(i).getPrice(),
                    i % 2 == 0 ? R.color.unevenRowBackground : R.color.evenRowBackground));
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), AddItemActivity.class);
                startActivity(intent);
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
//                Intent intent = new Intent(inflater.getContext(), AddItemActivity.class);
//                intent.putExtra("name", v.get);
//                startActivity(intent);
//                Toast.makeText(getContext(), "called " + name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(inflater.getContext(), AddItemActivity.class);
                intent.putExtra("date", new SimpleDateFormat("dd.MM.yy", Locale.ROOT).format(new Date(date)));
                intent.putExtra("name", name);
                intent.putExtra("price", Float.toString(price));
                startActivity(intent);

            }
        });
        return tableRow;
    }

}
