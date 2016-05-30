package com.lgcs.form;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnSiguiente;
    EditText nombre;
    DatePicker fecha;
    EditText telefono;
    EditText email;
    EditText descripcion;
    String formattedDate;

    Button btnEditar;
    String txtNombre;
    String txtFecha;
    String txtTelefono;
    String txtEmail;
    String txtDescripcion;

    int   day;
    int   month;
    int   year;
    int xxmonth;

    String mes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        nombre = (EditText) findViewById(R.id.inputNombre);
        fecha = (DatePicker) findViewById(R.id.dpFecha);
        telefono = (EditText) findViewById(R.id.inputTelefono);
        email = (EditText) findViewById(R.id.inputEmail);
        descripcion = (EditText) findViewById(R.id.inputDescripcion);

        Button btnSet = (Button) findViewById(R.id.btnSet);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                day  = fecha.getDayOfMonth();
                month= fecha.getMonth() + 1;
                year = fecha.getYear();
                Log.i("day",String.valueOf(day));
                Log.i("month",String.valueOf(month));
                Log.i("year",String.valueOf(year));
            }
        });

        /* Get*/
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null) {
            Log.i("extras","Entra");
            txtNombre = extras.getString("txtNombre");
            txtFecha = extras.getString("txtFecha");
            txtTelefono = extras.getString("txtTelefono");
            txtEmail = extras.getString("txtEmail");
            txtDescripcion = extras.getString("txtDescripcion");
            String eventMonth;
            String eventDay;
            String eventYear;
            if(txtFecha.length() == 9){
                eventMonth = txtFecha.substring(0,1);
                eventDay  = txtFecha.substring(2,4);
                eventYear = txtFecha.substring(5,9);
            }else {
                eventMonth = txtFecha.substring(0, 2);
                eventDay = txtFecha.substring(3, 5);
                eventYear = txtFecha.substring(6, 10);
            }

            /*if(Integer.parseInt(eventMonth) <= 9){
                eventMonth = "0"+eventMonth;
            }*/
            Log.i("Day",eventDay);
            Log.i("Month",eventMonth);
            Log.i("Year",eventYear);

            nombre.setText(txtNombre);
            fecha.updateDate(Integer.parseInt(eventYear),Integer.parseInt(eventMonth)-1,Integer.parseInt(eventDay));
            telefono.setText(txtTelefono);
            email.setText(txtEmail);
            descripcion.setText(txtDescripcion);
        }

        long dateTime = fecha.getCalendarView().getDate();
        Date date = new Date(dateTime);

        DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
        formattedDate = dateFormat.format(date);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ConfirmacionDatos.class);
                intent.putExtra("txtNombre", nombre.getText().toString());
                intent.putExtra("txtFecha", month+"/"+day+"/"+year);
                intent.putExtra("txtTelefono", telefono.getText().toString());
                intent.putExtra("txtEmail", email.getText().toString());
                intent.putExtra("txtDescripcion", descripcion.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}

