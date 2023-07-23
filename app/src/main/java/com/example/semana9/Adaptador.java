package com.example.semana9;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    ArrayList<Postulante> lista;
    daoPostulante dao;
    Postulante p;
    Activity a;
    int id = 0;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Adaptador(Activity a, ArrayList<Postulante> lista, daoPostulante dao){
        this.lista = lista;
        this.a = a;
        this.dao = dao;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Postulante getItem(int i) {
        p = lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        p = lista.get(i);
        return p.getId();
    }



    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {

        View v = view;

        if (v == null){
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item,null);
        }

        p = lista.get(posicion);

        TextView Nombre=(TextView) v.findViewById(R.id.t_nombre);
        TextView Apellido=(TextView) v.findViewById(R.id.t_apellido);
        TextView Universidad=(TextView) v.findViewById(R.id.t_universidad);


        Button editar=(Button) v.findViewById(R.id.editar);
        Button eliminar=(Button) v.findViewById(R.id.eliminar);

        Nombre.setText(p.getNombre());
        Apellido.setText(p.getApellido());
        Universidad.setText(p.getUniversidad());


        editar.setTag(posicion);
        editar.setTag(posicion);

        eliminar.setTag(posicion);



        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());

                final Dialog dialogo = new Dialog(a);
                dialogo.setTitle("Editar Registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dialogo);
                dialogo.show();

                final EditText Nombre = (EditText) dialogo.findViewById(R.id.Nombre);
                final EditText Apellido = (EditText) dialogo.findViewById(R.id.Apellido);
                final EditText Univerdiad = (EditText) dialogo.findViewById(R.id.Universidad);


                Button guardar = (Button) dialogo.findViewById(R.id.d_agregar);
                guardar.setText("Guardar");
                Button cancelar = (Button) dialogo.findViewById(R.id.d_cancelar);

                p = lista.get(pos);
                setId(p.getId());

                Nombre.setText(p.getNombre());
                Apellido.setText(p.getApellido());
                Univerdiad.setText(""+p.getUniversidad());


                guardar.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        try {
                            p = new Postulante(getId(),
                                    Nombre.getText().toString(),
                                    Apellido.getText().toString(),
                                    Univerdiad.getText().toString());

                            dao.editar(p);
                            lista=dao.verTodos();
                            notifyDataSetChanged();
                            dialogo.dismiss();
                        }catch (Exception e){
                            Toast.makeText(a,"ERROR",Toast.LENGTH_SHORT).show();
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

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                p = lista.get(pos);
                setId(p.getId());

                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("Estas seguro de eliminar esta postulacion ?");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.eliminar(getId());
                        lista = dao.verTodos();
                        notifyDataSetChanged();
                    }
                });

                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                del.show();

            }
        });



        return v;
    }



}

