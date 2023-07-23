package com.example.semana9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class visor_de_imagen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_de_imagen);

        ImageView img = (ImageView) findViewById(R.id.imagenCompleta);
        TextView nombre = (TextView) findViewById(R.id.nombre);
        TextView apellido = (TextView) findViewById(R.id.apellido);
        TextView universidad = (TextView) findViewById(R.id.universidad);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null){
            nombre.setText(b.getString("NOMBRE"));
            apellido.setText(b.getString("APELLIDO"));
            universidad.setText(b.getString("UNIVERSIDAD"));
        }

    }
}