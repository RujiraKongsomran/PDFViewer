package com.rujirakongsomran.pdfviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnOpenAssets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenAssets = (Button) findViewById(R.id.btnOpenAssets);
        btnOpenAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                intent.putExtra("ViewType", "assets");
                startActivity(intent);
            }
        });
    }
}