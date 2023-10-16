package com.example.pr_12_joc_endevina_el_numero;


import static com.example.pr_12_joc_endevina_el_numero.MainActivity.montonDatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);
        ArrayList<Object[]> dato = new ArrayList<>();
        for (userData u : montonDatos) {

            dato.add( new Object[]{u.getNombre(), u.getContador()} );
        }


// Ordenar el dato en base a la puntuación de pequeño a grande
        Collections.sort(dato, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                int puntuacion1 = (int) o1[1];
                int puntuacion2 = (int) o2[1];
                return Integer.compare(puntuacion1, puntuacion2);
            }
        });

        TableLayout tableLayout = findViewById(R.id.tableLayout);
        int posicion = 1;

        for (Object[] data : dato) {
            String nombreUsuario = (String) data[0];
            int puntuacion = (int) data[1];

            TableRow tableRow = new TableRow(this);

            // Crea TextViews para cada columna y configura sus atributos
            TextView textViewRank = new TextView(this);
            textViewRank.setText(String.valueOf(posicion)); // Para mostrar el número de fila
            textViewRank.setTextSize(18);
            textViewRank.setPadding(8, 0, 8, 0);

            TextView textViewUserName = new TextView(this);
            textViewUserName.setText(nombreUsuario);
            textViewUserName.setTextSize(18);
            textViewUserName.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1));
            textViewUserName.setPadding(8, 0, 8, 0);

            TextView textViewScore = new TextView(this);
            textViewScore.setText(String.valueOf(puntuacion));
            textViewScore.setTextSize(18);

            // Agrega TextViews a la fila
            tableRow.addView(textViewRank);
            tableRow.addView(textViewUserName);
            tableRow.addView(textViewScore);

            // Agrega la fila al TableLayout
            tableLayout.addView(tableRow, -1);
            posicion += 1;
        }

        Button volverButton = new Button(this);
        volverButton.setText("Volver");
        volverButton.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        volverButton.setGravity(Gravity.CENTER);
        tableLayout.addView(volverButton,-1);

        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent principal = new Intent(RankingActivity.this, MainActivity.class);
                startActivity(principal);
            }


        });

    }
}
