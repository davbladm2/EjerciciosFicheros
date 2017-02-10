package com.example.dm2.ejerciciosficheros;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Ejercicio1 extends AppCompatActivity {

    private EditText editTextContenido;
    private TextView TextViewContenidoConFichero;
    private String texto;
    private boolean sdDisponible=false;
    private boolean sdAccesoEscritura=false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);

        editTextContenido = (EditText) findViewById(R.id.editTextContenido);
        TextViewContenidoConFichero = (TextView) findViewById(R.id.TextViewContenidoConFichero);

        String estado= Environment.getExternalStorageState();

        if(estado.equals(Environment.MEDIA_MOUNTED)){
            sdDisponible=true;
            sdAccesoEscritura=true;
        }else if(estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            sdDisponible=true;
            sdAccesoEscritura=false;
        }else{
            sdDisponible=false;
            sdAccesoEscritura=false;
        }
    }

    public void pulsar(View v){
        //Crear el nuevo fichero en memoria interna
        if (v.getId() == R.id.buttonAnadirFichInt) {
            try {
                OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("texto.txt", Context.MODE_PRIVATE));
                texto = editTextContenido.getText().toString();
                osw.write(texto);
                osw.close();
            } catch (Exception e) {
                Log.e("Ficheros", "Error!! al escribir el fichero a memoria interna");
            }
        }

        //Leer el fichero en memoria interna
        if (v.getId() == R.id.buttonLeerFichInt) {
            try {
                BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("texto.txt")));
                texto = fin.readLine();
                fin.close();
                TextViewContenidoConFichero.setText(texto);
            } catch (Exception e) {
                Log.e("Ficheros", "ERROR!! al leer fichero desde memoria interna");
            }
        }

        //Leer el fichero desde recursos
        if (v.getId() == R.id.buttonLeerRecurso){
            try {
                InputStream is = getResources().openRawResource(R.raw.texto_raw);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String linea=br.readLine();
                while(linea!=null){
                    TextViewContenidoConFichero.setText(TextViewContenidoConFichero.getText()+linea+"\n");
                    linea=br.readLine();
                }
                is.close();
            } catch (IOException e) {
                Log.e("Ficheros", "ERROR!! al leer fichero desde el recurso");
            }
        }

        //Crear el fichero en memoria externa
        if(v.getId()==R.id.buttonAnadirFichExt){
            if(sdAccesoEscritura==true && sdDisponible==true){
                try{
                    File ruta_sd= getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                    //File ruta_sd = Context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                    File f = new File(ruta_sd.getAbsolutePath(),"prueba_sd.txt");
                    OutputStreamWriter sow =new OutputStreamWriter(new FileOutputStream(f, true));
                    texto = editTextContenido.getText().toString();
                    sow.write(texto);
                    sow.close();
                    Log.e("Fichero","Fichero escrito correctamente");
                }catch (Exception ex){
                    Log.e("Fichero"," Error al escribir el fichero en tarjeta SD "+ex.fillInStackTrace());
                }
            }else{
                Log.e("Fichero","La SD no esta disponible");
            }

        }

        //Leer el fichero en memoria externa
        if(v.getId()==R.id.buttonLeerFichExt){
            try {
                File ruta_sd=getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                File f=new File(ruta_sd.getAbsolutePath(),"prueba_sd.txt");
                BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                String linea=br.readLine();
                while(linea!=null){
                    TextViewContenidoConFichero.setText(TextViewContenidoConFichero.getText()+linea+"\n");
                    linea=br.readLine();
                }
                br.close();
            } catch (Exception e) {
                Log.e("Ficheros","Error!! en la lectura del fichero en SD");
            }
        }

        //Borrar el fichero en memoria interno
        if(v.getId()==R.id.buttonBorrarFichInt){
            deleteFile("texto.txt");
            Log.e("Eliminacion","Archivo borrado");
        }

        //Borrar el fichero en memoria externa
        if(v.getId()==R.id.buttonBorrarFichExt){
            File ruta_sd=getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            File f=new File(ruta_sd.getAbsolutePath(),"prueba_sd.txt");
            f.delete();
            Log.e("Eliminacion","Archivo borrado");
        }
    }
}
