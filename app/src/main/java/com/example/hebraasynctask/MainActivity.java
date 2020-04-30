package com.example.hebraasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Button btnThread;
    Button btnAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnThread = findViewById(R.id.btnActivityThread);
        btnAsyncTask = findViewById(R.id.btnActivityAsyncTask);

        btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threadIntent();
            }
        });

        btnAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncTaskIntent();
            }
        });

    }

    public void threadIntent() {
        intent = new Intent(this, ThreadActivity.class);
        startActivity(intent);
    }

    public void asyncTaskIntent() {
        intent = new Intent(this, AsyncTaskActivity.class);
        startActivity(intent);
    }
}
