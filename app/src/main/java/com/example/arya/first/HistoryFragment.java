package com.example.arya.first;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Date;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;

public class HistoryFragment extends Fragment{
    private Button btnW, btnR;
    final String FILENAME = "file1";
    private String contain = "";
    final String LOG_TAG = "myLogs";
    Goods[] newgoods;
    Goods[] goods;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        goods = new Goods[3];
        for (int i = 0; i < goods.length; i++) {
            goods[i] = new Goods();
        }
        newgoods = new Goods[3];
        for (int i = 0; i < newgoods.length; i++) {
            newgoods[i] = new Goods();
        }
        goods[0].setDate(new Date(2016,10,2));
        goods[0].setName("Tool");
        goods[0].setPrice(152);
        goods[1].setDate(new Date(2016,11,12));
        goods[1].setName("Cookie");
        goods[1].setPrice(17);
        goods[2].setDate(new Date(2016,12,22));
        goods[2].setName("Milk");
        goods[2].setPrice(56);

        btnW = (Button)rootView.findViewById(R.id.btnWrite);
        btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // отрываем поток для записи
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                            getActivity().openFileOutput(FILENAME, MODE_PRIVATE)));
                    // пишем данные
//                    bw.write("Содержимое файла");
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
//                    for (int i = 0; i < goods.length; i++) {
//                        bw.write(gson.toJson(goods[i]));
//                    }
                    bw.write(gson.toJson(goods));
                    // закрываем поток
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
                    // открываем поток для чтения
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            getActivity().openFileInput(FILENAME)));
                    // читаем содержимое
//                    while ((contain = br.readLine()) != null) {
//                        Log.d(LOG_TAG, contain);
//                        GsonBuilder builder = new GsonBuilder();
//                        Gson gson = builder.create();
//                        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
//                                gson.fromJson(contain, Goods.class).getName(),
//                                Toast.LENGTH_SHORT);
//                        toast.setGravity(Gravity.CENTER, 0, 0);
//                        toast.show();
//                    }
                    contain = br.readLine();
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
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
}
