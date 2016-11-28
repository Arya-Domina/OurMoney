package com.example.arya.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView testTextView;
    private EditText testEditText;
    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testTextView = (TextView)findViewById(R.id.textView);
        testEditText = (EditText)findViewById(R.id.test_edit_text);
        testButton = (Button)findViewById(R.id.test_button);

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testTextView.setText(testEditText.getText());
            }
        });

    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, TabLayoutActivity.class);
        startActivity(intent);
    }

}
