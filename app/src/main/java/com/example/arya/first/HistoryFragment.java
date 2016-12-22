package com.example.arya.first;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;

public class HistoryFragment extends Fragment{
    private Button btnW, btnR;
    final String FILENAME = "file1";
    final String LOG_TAG = "myLogs";
    View rootView;
    LayoutInflater inflater;
    Goods[] newgoods, goods;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_history, container, false);
        this.inflater = inflater;

        goods = new Goods[3];
        for (int i = 0; i < goods.length; i++) {
            goods[i] = new Goods();
        }
        newgoods = new Goods[3];
        for (int i = 0; i < newgoods.length; i++) {
            newgoods[i] = new Goods();
        }
        goods[0].setDate(System.currentTimeMillis());
        goods[0].setName("Tool");
        goods[0].setPrice(152);
        goods[1].setDate(System.currentTimeMillis());
        goods[1].setName("Cookie");
        goods[1].setPrice(17);
        goods[2].setDate(System.currentTimeMillis());
        goods[2].setName("Milk");
        goods[2].setPrice(56);

        TableLayout tableLayout = (TableLayout)rootView.findViewById(R.id.table_goods_history);
        for (int i = 0; i < goods.length; i++) {
            tableLayout.addView(addRow(goods[i].getDate(), goods[i].getName(), goods[i].getPrice()));
        }

        btnW = (Button)rootView.findViewById(R.id.btnWrite);
        btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                            getActivity().openFileOutput(FILENAME, MODE_PRIVATE)));
                    bw.write(gson.toJson(goods));
                    bw.close();
                    Log.d(LOG_TAG, "Файл записан");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnR = (Button)rootView.findViewById(R.id.btnRead);
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            getActivity().openFileInput(FILENAME)));
                    String contain = "";
//                    while ((contain = br.readLine()) != null) {
//                        Log.d(LOG_TAG, contain);
//                    }
                    contain = br.readLine();
                    newgoods = gson.fromJson(contain, newgoods.getClass());
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                            newgoods[0].getName(),
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    toast = Toast.makeText(getActivity().getApplicationContext(),
                            newgoods[1].getName(),
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    toast = Toast.makeText(getActivity().getApplicationContext(),
                            newgoods[2].getName(),
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        return rootView;
    }
    private TableRow addRow(long date, String name, float price) {

//        LayoutInflater inflater = LayoutInflater.from(this); // (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TableRow tableRow = (TableRow)inflater.inflate(R.layout.table_row, null);
        TextView textView = (TextView) tableRow.getChildAt(0);
//        textView.setText(Integer.toString(date.getMonth()) + "." + Integer.toString(date.getDay()));
//        textView.setText("date");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        textView.setText(formatter.format(new Date(date)));
        textView = (TextView) tableRow.getChildAt(1);
        textView.setText(name);
        textView = (TextView) tableRow.getChildAt(2);
        textView.setText(Float.toString(price));
        return tableRow;
    }
}
