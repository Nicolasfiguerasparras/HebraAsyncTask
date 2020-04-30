package com.example.hebraasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class ThreadActivity extends AppCompatActivity {

    Button btnTarea;
    Button btnNuevoHilo;
    ProgressBar progressBar;
    int contador;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        btnTarea = findViewById(R.id.btnTarea);
        btnNuevoHilo = findViewById(R.id.btnNuevoHilo);
        progressBar = findViewById(R.id.progressBarThread);

        // Al pulsar en el botón "Tarea"
        btnTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setMax(100); // Ajustamos el valor máximo de la barra de progreso
                progressBar.setMin(0); // Ajustamos el valor mínimo de la barra de progreso

                contador = 1;

                for (int i = 0; i < 20; i++) {
                    tareaLarga();
                    if(i%2 == 0){
                        progressBar.setProgress(10*contador);
                        contador++;
                    }
                }

                cambiaActividad();
            }
        });

        btnNuevoHilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuevosHilos();
            }
        });
    }

    private void tareaLarga(){
        try{
            Thread.sleep(1000); // Ejecutamos una tarea haciendo esperar 1 seg
        }catch (InterruptedException e) {}
    }

    public void nuevosHilos(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                progressBar.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(0);
                    }
                });

                for (int i = 0; i < 20; i++) {
                    tareaLarga();
                    progressBar.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.incrementProgressBy(5);
                        }
                    });
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cambiaActividad();
                    }
                });
            }
        }).start();
    }

    public void cambiaActividad(){
        intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}
