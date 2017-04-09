package com.example.richardssurface.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText tv = (EditText) findViewById(R.id.edit_message);
                tv.setText("");
            }
        });

    }

    public void reverseWord(View view) {
        // Do something in response to button
        TextView tv = (TextView) findViewById(R.id.edit_message);
        String builder = new StringBuilder(tv.getText()).reverse().toString();
        tv.setText(builder);
    }

    public void clearWord(View view) {
        // Do something in response to button
        TextView tv = (TextView) findViewById(R.id.edit_message);
        String builder = "";
        tv.setText(builder);
    }
}
