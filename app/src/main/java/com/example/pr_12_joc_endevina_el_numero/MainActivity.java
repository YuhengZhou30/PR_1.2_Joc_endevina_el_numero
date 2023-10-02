package com.example.pr_12_joc_endevina_el_numero;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber;
    private Button button;
    private TextView textView;
    private AlertDialog dialog;
    static Random random = new Random();
    private int randomNumber;
    private int contandor;
    private TextView contadorView;
    private TextView historialTextView;

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
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
