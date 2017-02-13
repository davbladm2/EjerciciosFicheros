package com.example.dm2.ejerciciosficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio2 extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        //Se instacia el spinner
        spinner=(Spinner)findViewById(R.id.spinner);

        //Se crea un adaptador para el array
        ArrayAdapter<String> arrayAdaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);

        //Se lee el fichero del recurso
        try {
            InputStream is=getResources().openRawResource(R.raw.provincias);
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String linea=br.readLine();
            int cont=0;
            while(linea!=null){
                arrayAdaptador.insert(linea,cont);
                linea=br.readLine();
                cont++;
            }
            br.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Metemos el Adaptador en el spinner
        arrayAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdaptador);
    }
}
