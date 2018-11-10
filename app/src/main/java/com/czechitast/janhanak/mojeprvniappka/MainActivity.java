package com.czechitast.janhanak.mojeprvniappka;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //globalní promněné, aby k nim byl přístup v rámci celé aktivity
    Button tlacitko;
    TextView nadpis;
    EditText editText;
    EditText barva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //nastavení layoutu do aktivity
        tlacitko = findViewById(R.id.tlacitko); //napárování View do proměnné pomocí id
        nadpis = findViewById(R.id.nadpis);
        editText = findViewById(R.id.edittext);
        barva = findViewById(R.id.barva);
        tlacitko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vstup = editText.getText().toString();
                /*if(vstup.equals("")) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("Nic jsi nenapsal")
                            .setPositiveButton("ok", null)
                            .show();

                } else  {
                    nadpis.setText(vstup);
                }*/
                startSecondActivity(vstup);
            }
        });
    }
    private void startSecondActivity(String vstup){
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("barva",barva.getText().toString());
        intent.putExtra("name",vstup);
        startActivity(intent);
    }

}
