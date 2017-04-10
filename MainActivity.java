package com.example.aluno.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;



import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CorteList cuts = new CorteList();
    MapsActivity setMap= new MapsActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cuts.getCortes();
        List<String> cortes = cuts.returnCortes();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cortes);


        String g= cortes.get(0);


        final TextView textViewToChange = (TextView) findViewById(R.id.url);
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerTest);
        spinner.setAdapter(adapter);

        textViewToChange.setText(g);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String corte = String.valueOf(spinner.getSelectedItem());
                String nome = cuts.returnU(corte);
                String valor ="R$: "+cuts.returnGRANA(corte);
                String location=cuts.returnLocation(corte);
                textViewToChange.setText(valor);
                changeImage(nome);
                setMap.setLocation(location);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button nomeParaOBotao = (Button) findViewById(R.id.nomeDoBotao);

        nomeParaOBotao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent it = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(it);
            }
        });

    }
    public void changeImage(String imag)
    {
        try {
            URL url = new URL(imag);
            HttpURLConnection conexao = (HttpURLConnection)
                    url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setDoInput(true);
            conexao.connect();

            InputStream is = conexao.getInputStream();
            Bitmap imagem = BitmapFactory.decodeStream(is);

            ImageView img = (ImageView)findViewById(R.id.imageView);
            img.setImageBitmap(imagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
