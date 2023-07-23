package com.example.semana9;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Postulante p;
    daoPostulante dao;
    Adaptador adapter;
    ArrayList<Postulante> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new daoPostulante(this);
        lista = dao.verTodos();
        adapter = new Adaptador(this, lista, dao);

        ListView list = (ListView) findViewById(R.id.lista);
        Button agregar = (Button) findViewById(R.id.agregar);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        agregar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                final Dialog dialogo = new Dialog( MainActivity.this);
                dialogo.setTitle("Nueva Postulacion");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dialogo);
                dialogo.show();

                final EditText Nombre = (EditText) dialogo.findViewById(R.id.Nombre);
                final EditText Apellido = (EditText) dialogo.findViewById(R.id.Apellido);
                final EditText Universidad  = (EditText) dialogo.findViewById(R.id.Universidad);


                Button guardar = (Button) dialogo.findViewById(R.id.d_agregar);
                guardar.setText("Agregar");
                Button cancelar = (Button) dialogo.findViewById(R.id.d_cancelar);

                guardar.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        try {
                            p = new Postulante(
                                    Nombre.getText().toString(),
                                    Apellido.getText().toString(),
                                    Universidad.getText().toString());
                            dao.insertar(p);
                            lista=dao.verTodos();
                            adapter.notifyDataSetChanged();
                            dialogo.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(),"ERROR",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialogo.dismiss();
                    }
                });

            }
        });

    }
}