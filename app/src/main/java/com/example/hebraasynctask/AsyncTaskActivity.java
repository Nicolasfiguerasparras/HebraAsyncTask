package com.example.hebraasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AsyncTaskActivity extends AppCompatActivity {

    Intent intent;
    Button btnAsyncTask;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        btnAsyncTask = findViewById(R.id.btnAsyncTask);
        btnCancel = findViewById(R.id.btnCancel);

        final Task tarea = new Task();

        btnAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarea.execute();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarea.cancel(true);
            }
        });

    }

    private class Task extends AsyncTask<Void, Integer, Boolean>{

        ProgressBar progressBar = findViewById(R.id.progressBarAsyncTask);

        public Task() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setMax(100);
            progressBar.setMin(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                cambiaActividad();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();
            progressBar.setProgress(progreso);
        }

        @Override
        protected void onCancelled() {
            if(progressBar.getProgress() != 0){
                progressBar.setProgress(0);
                Toast.makeText(AsyncTaskActivity.this, "Tarea cancelada", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            int contador = 1;

            for (int i = 0; i < 20; i++) {
                tareaLarga();

                if(i%2 == 0){
                    publishProgress(10*contador);
                    contador++;
                }

                if(isCancelled()){
                    break;
                }
            }

            return true;
        }
    }

    public void cambiaActividad(){
        intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    private void tareaLarga(){
        try{
            Thread.sleep(1000); // Ejecutamos una tarea haciendo esperar 1 seg
        }catch (InterruptedException e) {}
    }
}