package com.example.arya.first;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Exchanger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MyFriendsFragment extends Fragment {

    Exchanger<String> exchanger;
    String json = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_friends, container, false);
        LinearLayout linearLayout = (LinearLayout)rootView.findViewById(R.id.friends_list);

        OkHttpClient client = new OkHttpClient();
        exchanger = new Exchanger<>();
        Request request  = new Request.Builder().url("http://192.168.43.52:9910").build();
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
                        json = exchanger.exchange(response.body().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ArrayList<User> gsonUsers = gson.fromJson(json, ArrayList.class);

        ArrayList<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(0);
        user1.setName("Marina");
        users.add(user1);
        User user2 = new User();
        user2.setId(1);
        user2.setName("Denis");
        users.add(user2);
        User user3 = new User();
        user3.setId(2);
        user3.setName("Natalya");
        users.add(user3);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 50;
        layoutParams.topMargin = 25;
        for (final User user : users) {
            final TextView name = new TextView(inflater.getContext());
            name.setText(user.getName());
            name.setTextSize(18);
            name.setTextColor(getResources().getColor(R.color.black));
            name.setLayoutParams(layoutParams);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(inflater.getContext(),
                            "User index - " + user.getId(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            linearLayout.addView(name);
        }
        return rootView;
    }
}
