package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView sorularTextView;
    TextView soruTextView;
    Button cvp_a,cvp_b,cvp_c,cvp_d;
    Button onayBtn;
    int puan = 0;
    int toplamSoru = Sorular.sorular.length;
    int guncelSoruIndex = 0;
    String secilenCevap = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sorularTextView = findViewById(R.id.sorular);
        soruTextView = findViewById(R.id.soru);
        cvp_a = findViewById(R.id.cvp_a);
        cvp_b = findViewById(R.id.cvp_b);
        cvp_c = findViewById(R.id.cvp_c);
        cvp_d = findViewById(R.id.cvp_d);
        onayBtn = findViewById(R.id.onay);

        cvp_a.setOnClickListener(this);
        cvp_b.setOnClickListener(this);
        cvp_c.setOnClickListener(this);
        cvp_d.setOnClickListener(this);
        onayBtn.setOnClickListener(this);

        sorularTextView.setText("Sorular :" + toplamSoru);
        yeniSoruGetir();

    }

    @Override
    public void onClick(View v) {

        cvp_a.setBackgroundColor(Color.WHITE);
        cvp_b.setBackgroundColor(Color.WHITE);
        cvp_c.setBackgroundColor(Color.WHITE);
        cvp_d.setBackgroundColor(Color.WHITE);

        Button tiklananBtn = (Button) v;
        if(tiklananBtn.getId() == R.id.onay){
            if(secilenCevap.equals(Sorular.dogruCevaplar[guncelSoruIndex])){
                puan++;
            }
            guncelSoruIndex++;
            yeniSoruGetir();
            }
        else{
            secilenCevap = tiklananBtn.getText().toString();
            tiklananBtn.setBackgroundColor(Color.RED);
        }
    }
    void yeniSoruGetir(){

        if(guncelSoruIndex == toplamSoru){
            quizBitir();
            return;
        }

        soruTextView.setText(Sorular.sorular[guncelSoruIndex]);
        cvp_a.setText(Sorular.secimler[guncelSoruIndex][0]);
        cvp_b.setText(Sorular.secimler[guncelSoruIndex][1]);
        cvp_c.setText(Sorular.secimler[guncelSoruIndex][2]);
        cvp_d.setText(Sorular.secimler[guncelSoruIndex][3]);
    }

    void quizBitir(){
        String bitisDurumu = "";
        if(puan > toplamSoru*0.60){
            bitisDurumu = "Geçti";
        }
        else {
            bitisDurumu = "Kaldı";
        }

        new AlertDialog.Builder(this)
                .setTitle(bitisDurumu)
                .setMessage("Puan "+ toplamSoru +" üzerinden " + puan)
                .setPositiveButton("Yeniden Başlat",(dialogInterface, i) -> yenidenBaslat() )
                .setCancelable(false)
                .show();
    }
    void yenidenBaslat(){
        puan = 0;
        guncelSoruIndex = 0;
        yeniSoruGetir();
    }
}