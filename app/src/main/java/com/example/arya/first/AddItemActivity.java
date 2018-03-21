package com.example.arya.first;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddItemActivity extends AppCompatActivity {

    int DIALOG_DATE = 1;
    private TextView textDate;
    private TextView textName;
    private TextView textPrice;
    private Button addItemButton;
    private Date dateDate;
    private LinearLayout linearLayout;
    private ArrayList<User> users;
    Gson gson = (new GsonBuilder()).create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        textDate = (TextView)findViewById(R.id.text_date);
        textName = (TextView)findViewById(R.id.text_name);
        textPrice = (TextView)findViewById(R.id.text_price);
        addItemButton = (Button)findViewById(R.id.button_add_item);
        linearLayout = (LinearLayout)findViewById(R.id.friends);


        if (getIntent().getExtras() != null) {
            textDate.setText(getIntent().getExtras().getString("date"));
            textName.setText(getIntent().getExtras().getString("name"));
            textPrice.setText(getIntent().getExtras().getString("price"));
            addItemButton.setText(R.string.save);
        } else {
            dateDate = new Date(System.currentTimeMillis());
            textDate.setText(new SimpleDateFormat("dd.MM.yy", Locale.ROOT).format(dateDate));
        }
        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_DATE);
            }
        });

        users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            users.add(new User());
            users.get(i).setId(i);
            users.get(i).setName("User " + i);
        }

        for (int i = 0; i < users.size(); i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(users.get(i).getName());
            checkBox.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(checkBox);
        }

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Goods goods = new Goods();
                goods.setName(String.valueOf(textName.getText()));
                try {
                    goods.setDate((new SimpleDateFormat("dd.MM.yy", Locale.ROOT)
                            .parse(String.valueOf(textDate.getText()))).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                goods.setPrice(Float.parseFloat(String.valueOf(textPrice.getText())));
                ArrayList<User> checkedUsers = new ArrayList<>();
                for (int i = 0; i < users.size(); i++) {
                    CheckBox checkBox = (CheckBox) linearLayout.getChildAt(i);
                    if (checkBox.isChecked()) {
                        checkedUsers.add(users.get(i));
                    }
                }
                String stringGoods = gson.toJson(goods);
                String stringUsers = gson.toJson(checkedUsers);

                Intent intent = new Intent();
                intent.putExtra("goods", stringGoods);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

}

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            return new DatePickerDialog(this, myCallBack,
                    dateDate.getYear() + 1900, dateDate.getMonth(), dateDate.getDate());
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateDate.setYear(view.getYear());
            dateDate.setMonth(view.getMonth());
            dateDate.setDate(view.getDayOfMonth());
            textDate.setText(new SimpleDateFormat("dd.MM.yy", Locale.ROOT).format(dateDate));
        }
    };
}
