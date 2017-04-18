package com.example.arya.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.Exchanger;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button2;
    OkHttpClient client;
    Exchanger<String> exchanger;
    String string = "";
    String json = "{'b':'5555'}";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient();
        exchanger = new Exchanger<>();

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Request request;
                RequestBody body = RequestBody.create(JSON, json);
                request = new Request.Builder().url("http://192.168.43.52:9910").post(body).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.v("onFailure", "1");
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            Log.v("onResponse", "1");
                            throw new IOException("Unexpected code " + response);
                        } else {
                            Log.v("onResponse", "2");
                            try {

                                //Log.v("onResponse", response.body().string());
                                string = exchanger.exchange(response.body().string());
//                                Log.v("exchange", exchanger.exchange(response.body().string()));
//                                toast = Toast.makeText(getApplicationContext(),
//                                        response.body().string(),
//                                        "12r3efwfew",
//                                        Toast.LENGTH_SHORT);
//                                toast.setGravity(Gravity.CENTER, 0, 0);
//                                toast.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });**/
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2.setText(string);
            }
        });
    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, TabLayoutActivity.class);
        startActivity(intent);
    }

}
