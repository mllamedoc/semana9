package com.example.semana9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;


    public class daoPostulante {

        SQLiteDatabase cx;
        ArrayList<Postulante> lista = new ArrayList<Postulante>();
        Postulante p;
        Context ct;
        String nombreBD = "BDPostulante";
        String tabla = "create table if not exists postulante(id integer primary key autoincrement, Postulante text, Apellido text, Universidad text)";


        public daoPostulante(Context c){
            this.ct = c;
            cx = c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
            cx.execSQL(tabla);
        }

        public boolean insertar(Postulante p){
            ContentValues contenedor = new ContentValues();
            contenedor.put("Postulante",p.getNombre());
            contenedor.put("Apellido",p.getApellido());
            contenedor.put("Universidad",p.getUniversidad());

            return (cx.insert("postulante",null,contenedor)) > 0;
        }

        public boolean eliminar(int id){
            return (cx.delete("postulante","id="+id,null)) > 0;
        }

        public boolean editar(Postulante p){
            ContentValues contenedor = new ContentValues();
            contenedor.put("Postulante",p.getNombre());
            contenedor.put("Apellido",p.getApellido());
            contenedor.put("Universidad",p.getUniversidad());

            return (cx.update("postulante",contenedor,"id="+p.getId(), null)) > 0;
        }

        public ArrayList<Postulante> verTodos(){
            lista.clear();
            Cursor cursor = cx.rawQuery("select * from postulante",null);
            if( cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do{
                    lista.add(new Postulante(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)));
                }while (cursor.moveToNext());
            }
            return lista;
        }

        public Postulante verUno(int posicion){
            Cursor cursor = cx.rawQuery("select * from postulante",null);
            cursor.moveToPosition(posicion);
            p = new Postulante(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            return p;
        }

    }


