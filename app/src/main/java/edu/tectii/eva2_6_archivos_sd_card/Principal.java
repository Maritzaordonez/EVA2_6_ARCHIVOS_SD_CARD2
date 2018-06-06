package edu.tectii.eva2_6_archivos_sd_card;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Principal extends AppCompatActivity {
    EditText edtArchivo;
    String rutaSDCard;
    final String ARCHIVO = "miarchivo.txt";
    final static int PERMISO_ESCRITURA = 100;
    boolean bLeerEsc = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        edtArchivo = findViewById(R.id.edtArchivo);
        rutaSDCard = Environment.getExternalStorageDirectory().getAbsolutePath();
        Toast.makeText(this, rutaSDCard, Toast.LENGTH_SHORT).show();
        //Checamos si tenemos permiso
      /*  if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISO_ESCRITURA_SD);
        } else {
            bLE = true;
        } */ //Solo con versiones mas de 6.0 en android
    }

 /*   @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISO_ESCRITURA_SD) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                bLE = true;
            }
        }
    } */ //Solo con versiones mas de 6.0 en android

    public void Leer (View v) {

     /*   if (bLE) {
            Toast.makeText(this, "PERMISO", Toast.LENGTH_SHORT).show();
        }*/ //Solo con versiones mas de 6.0 en android

        String sCade;
        try {
            edtArchivo.setText("");
            FileInputStream fiAbrir = new FileInputStream(rutaSDCard + "/" + ARCHIVO);
            InputStreamReader isLeer = new InputStreamReader(fiAbrir);
            BufferedReader brLeerTexto = new BufferedReader(isLeer);
            while ((sCade = brLeerTexto.readLine()) != null) {
                edtArchivo.append(sCade);
                edtArchivo.append("\n");
            }
            brLeerTexto.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Escribir (View v) {
     /*   if (bLE) {
            Toast.makeText(this, "PERMISO", Toast.LENGTH_SHORT).show();
        }*/ //Solo con versiones mas de 6.0 en android


        String[] asCade = edtArchivo.getText().toString().split("\n");
        try {

            FileOutputStream fusAbrir = new FileOutputStream(rutaSDCard + "/" + ARCHIVO);
            OutputStreamWriter oswEscribir = new OutputStreamWriter(fusAbrir);
            //Orientado a archivos de texto solamente
            BufferedWriter bwEscribir = new BufferedWriter(oswEscribir);
            for (int i=0; i<asCade.length; i++) {
                bwEscribir.append(asCade[i]);
                bwEscribir.append("\n");
            }
            bwEscribir.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
