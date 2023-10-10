package com.example.pr_12_joc_endevina_el_numero;

import static com.example.pr_12_joc_endevina_el_numero.MainActivity.Datos;
import static com.example.pr_12_joc_endevina_el_numero.MainActivity.montonDatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserNameInput extends AppCompatActivity {
    private  ArrayList<Object[]> userData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_input_name);
        EditText editText = findViewById(R.id.editTextText);
        Button button = findViewById(R.id.button2);
        Intent anterior=getIntent();


        editText.setHint("Ingresa tu nombre");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean salir=false;
                String nameUser=editText.getText().toString().trim();
                if ( nameUser.isEmpty()){
                    Toast.makeText(UserNameInput.this, "Tiene que tener algo", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UserNameInput.this, "HOLA "+nameUser+"\nDatos guardado !!!", Toast.LENGTH_SHORT).show();
                    salir=true;
                };
                if (salir){
                    Intent intent = new Intent(UserNameInput.this, RankingActivity.class);
                    Datos.setNombre(nameUser);
                    montonDatos.add(Datos);
                    startActivity(intent);
                }
            }


        });



    }

}
