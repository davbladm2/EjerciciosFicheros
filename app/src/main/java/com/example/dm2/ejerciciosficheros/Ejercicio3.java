package com.example.dm2.ejerciciosficheros;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ejercicio3 extends AppCompatActivity {

    private ListView listViewWebs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3);



        ArrayList<Webs> listaWebs=new ArrayList<Webs>();
        ArrayList<Integer> imagen=new ArrayList<Integer>();
        try {
            InputStream is=getResources().openRawResource(R.raw.webs);
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String linea=br.readLine();
            int cont=0;
            while(linea!=null){
                String[] web=linea.split(";");
                listaWebs.add(new Webs(web[0],web[1],web[2],web[3]));
                if(web[2].equals("bing")){
                    imagen.add(R.drawable.bing);
                }else if(web[2].equals("yahoo")){
                    imagen.add(R.drawable.yahoo);
                }
                linea=br.readLine();
                cont++;
            }
            br.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Adaptador adaptador=new Adaptador(this,listaWebs,imagen);

        listViewWebs=(ListView)findViewById(R.id.listViewWebs);
        listViewWebs.setAdapter(adaptador);
    }

    class Adaptador extends ArrayAdapter<Webs> {

        private ArrayList<Webs> listaWebs;
        private ArrayList<Integer> imagen;

        public Adaptador(Context context, ArrayList<Webs> listaWebs,ArrayList<Integer> imagen){
            super(context,R.layout.activity_ejercicio3,listaWebs);
            this.listaWebs=listaWebs;
            this.imagen=imagen;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            View item=inflater.inflate(R.layout.activity_ejercicio3,null);

            TextView textViewNombre=(TextView)item.findViewById(R.id.textViewNombre);
            textViewNombre.setText(listaWebs.get(position).getNombre());

            TextView textViewEnlace=(TextView)item.findViewById(R.id.textViewEnlace);
            textViewEnlace.setText(listaWebs.get(position).getEnlace());

            TextView textViewIdentificador=(TextView)item.findViewById(R.id.textViewIdentificador);
            textViewIdentificador.setText(listaWebs.get(position).getIdentificador());


            ImageView imagenView = (ImageView) item.findViewById(R.id.imagenView);
            imagenView.setImageResource(imagen.get(position).intValue());

            return(item);
        }
    }
}
