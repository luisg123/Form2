package com.lgcs.form;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class ConfirmacionDatos extends AppCompatActivity {
    private TextView txtNombre;
    private TextView txtFecha;
    private TextView txtTelefono;
    private TextView txtEmail;
    private TextView txtDescripcion;
    private Button btnEditar;
    private String nombre;
    private String fecha;
    private String telefono;
    private String email;
    private String descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        btnEditar = (Button) findViewById(R.id.btnEditar);

        Bundle parametros = getIntent().getExtras();
        nombre = parametros.getString("txtNombre");
        fecha = parametros.getString("txtFecha");
        telefono = parametros.getString("txtTelefono");
        email = parametros.getString("txtEmail");
        descripcion = parametros.getString("txtDescripcion");

        txtNombre = (TextView) findViewById(R.id.tvNombre);
        txtFecha = (TextView) findViewById(R.id.tvFecha);
        txtTelefono = (TextView) findViewById(R.id.tvTelefono);
        txtEmail = (TextView) findViewById(R.id.tvEmail);
        txtDescripcion = (TextView) findViewById(R.id.tvDescripcion);

        txtNombre.setText(nombre);
        txtFecha.setText(fecha);
        txtTelefono.setText(telefono);
        txtEmail.setText(email);
        txtDescripcion.setText(descripcion);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmacionDatos.this,MainActivity.class);
                intent.putExtra("txtNombre", nombre);
                intent.putExtra("txtFecha", fecha);
                intent.putExtra("txtTelefono", telefono);
                intent.putExtra("txtEmail", email);
                intent.putExtra("txtDescripcion", descripcion);
                startActivity(intent);
                finish();
            }
        });
    }
}
