package com.rujirakongsomran.pdfviewer;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;
import com.ornach.nobobutton.NoboButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_PDF_CODE = 1000;
    NoboButton btnOpenAssets;
    NoboButton btnOpenStorage;
    NoboButton btnOpenFromInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Request Read & Write External Storage
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new BaseMultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        super.onPermissionsChecked(report);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        super.onPermissionRationaleShouldBeShown(permissions, token);
                    }
                }).check();

        btnOpenAssets = (NoboButton) findViewById(R.id.btnOpenAssets);
        btnOpenStorage = (NoboButton) findViewById(R.id.btnOpenStorage);
        btnOpenFromInternet = (NoboButton) findViewById(R.id.btnOpenFromInternet);

        btnOpenAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                intent.putExtra("ViewType", "assets");
                startActivity(intent);
            }
        });

        btnOpenStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserPDF = new Intent(Intent.ACTION_GET_CONTENT);
                browserPDF.setType("application/pdf");
                browserPDF.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(browserPDF, "Select PDF"), PICK_PDF_CODE);
            }
        });

        btnOpenFromInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                intent.putExtra("ViewType", "internet");
                startActivity(intent);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedPDF = data.getData();
            Intent intent = new Intent(MainActivity.this, ViewActivity.class);
            intent.putExtra("ViewType", "storage");
            intent.putExtra("FileUri", selectedPDF.toString());
            startActivity(intent);

        }
    }
}