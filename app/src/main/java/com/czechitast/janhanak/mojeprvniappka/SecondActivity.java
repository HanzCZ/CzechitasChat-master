package com.czechitast.janhanak.mojeprvniappka;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        new AlertDialog.Builder(this)
                .setMessage(getIntent().getStringExtra("vstup"))
                .setPositiveButton("ok", null)
                .show();

    }
}
