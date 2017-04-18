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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Exchanger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HistoryFragment extends Fragment{
    LayoutInflater inflater;
//    Goods[] goods;
    ArrayList<Goods> goods;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
//    OkHttpClient client;
    Exchanger<String> exchanger = new Exchanger<>();
    String json = "";
    String url = "http://192.168.43.165:9910/resource?";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        this.inflater = inflater;

        int id = 0;
        url += "id=" + id;
        OkHttpClient client = new OkHttpClient();
//        exchanger = new Exchanger<>();
        Request request  = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        String srt = response.body().string();

                        //json = exchanger.exchange(response.body().string());
                        Log.v("onResponse", srt);

                        json = exchanger.exchange(srt);
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                }
            }
        });
        String string = json;
        ArrayList<Goods> gsonGoods = gson.fromJson(json, ArrayList.class);
        goods = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        for (int i = 0; i < 22; i+=3) {
            date.setDate(date.getDate()-1);
            goods.add(new Goods());
            goods.get(i).setDate(date.getTime());
            goods.get(i).setName("Tool");
            goods.get(i).setPrice(152);
            goods.add(new Goods());
            goods.get(i+1).setDate(date.getTime());
            goods.get(i+1).setName("Cookie");
            goods.get(i+1).setPrice(17);
            goods.add(new Goods());
            goods.get(i+2).setDate(date.getTime());
            goods.get(i+2).setName("Milk");
            goods.get(i+2).setPrice(56);
        }
        String st = gson.toJson(goods);

        TableLayout tableLayout = (TableLayout)rootView.findViewById(R.id.table_goods_history);
        for (int i = 0; i < goods.size(); i++) {
            tableLayout.addView(addRow(
                    goods.get(i).getDate(),
                    goods.get(i).getName(),
                    goods.get(i).getPrice(),
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
