package com.example.kireta.coach.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.kireta.coach.R;
public class HistoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        creerMenu();
    }
    //bouton retour menu
    public void creerMenu(){
        //appel de ecoute menu pou clic sur l'image retourn menu
        ecouteMenu((ImageButton)findViewById(R.id.btnMenuHisto),MainActivity.class);


    }
    public void ecouteMenu(ImageButton a, final Class b){

        (a).setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent c=new Intent(HistoActivity.this,b);
                startActivity(c);
            }
        });
    }
}
