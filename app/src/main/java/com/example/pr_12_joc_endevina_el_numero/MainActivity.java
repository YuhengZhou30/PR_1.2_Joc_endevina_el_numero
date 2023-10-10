package com.example.pr_12_joc_endevina_el_numero;
import android.content.Intent;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<userData> montonDatos=  new ArrayList<userData>();
    public static userData Datos =new userData();
    private EditText editTextNumber;
    private Button button;
    private TextView textView;
    private AlertDialog dialog;
    private AlertDialog dialogPedirNom;
    static Random random = new Random();
    private int randomNumber;
    private int contandor;
    private TextView contadorView;
    private TextView historialTextView;
    private ArrayList<String[]> userData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomNumber = random.nextInt(101);
        editTextNumber = findViewById(R.id.editTextNumber);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.texto);
        historialTextView = findViewById(R.id.historial);
        contadorView = findViewById(R.id.contadorId);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  historial = historialTextView.getText().toString();
                contandor+=1;
                contadorView.setText(contandor+"");
                // Obtener el número ingresado por el usuario
                String numeroIngresado = editTextNumber.getText().toString();
                historial+="\n"+numeroIngresado;
                // Realizar alguna acción con el número (puedes convertirlo a un entero, validar, etc.)
                // Aquí, simplemente lo mostramos en el TextView
                int numeroInt;
                try {
                    numeroInt = Integer.parseInt(numeroIngresado);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Un numero valido", Toast.LENGTH_SHORT).show();
                    historial+="\n"+"Un numero valido";
                    return ;
                }

                if (numeroInt != randomNumber) {
                    editTextNumber.setText("");


                    if (numeroInt < randomNumber) {
                        Toast.makeText(MainActivity.this, "El número es mayor", Toast.LENGTH_SHORT).show();
                        historial+="\n"+"El número es mayor";
                    } else {
                        Toast.makeText(MainActivity.this, "El número es menor", Toast.LENGTH_SHORT).show();
                        historial+="\n"+"El número es menor";
                    }
                    historialTextView.setText(historial);
                } else {
                    showAlertDialog();
                }

            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¡Has ganado!\nCon total de "+contandor+" intentos.");
        builder.setPositiveButton("Que guay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Aquí puedes agregar código para manejar el botón "OK" si es necesario
                dialog.dismiss(); // Cierra el AlertDialog
                DialogPedirNom();
            }
        });

        dialog = builder.create();
        dialog.show();
    }
    private void DialogPedirNom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Te gustaria guardar tu nombre ?");
        builder.setPositiveButton("Vale", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, UserNameInput.class);
                Datos.setContador(contandor);

                startActivity(intent);
                dialog.dismiss(); // Cierra el AlertDialog
            }
        });

        builder.setNegativeButton("Proxima vez", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Aquí puedes agregar código para manejar el botón "OK" si es necesario
                dialog.dismiss(); // Cierra el AlertDialog
            }
        });


        dialog = builder.create();
        dialog.show();
    }
}

 class userData {
    private String nombre;
    private int contador;

    // Getter para el campo "nombre"
    public String getNombre() {
        return nombre;
    }

    // Setter para el campo "nombre"
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para el campo "contador"
    public int getContador() {
        return contador;
    }

    // Setter para el campo "contador"
    public void setContador(int contador) {
        this.contador = contador;
    }
}
