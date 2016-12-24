package com.example.kireta.coach.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kireta.coach.R;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creerMenu();
    }

    public void creerMenu(){
        //appel de ecoute menu pour clic sur l'image IMG
        ecouteMenu((ImageButton)findViewById(R.id.imageButton),CalculActivity.class);
        //appel de ecoute menu pou clic sur l'image historique
        ecouteMenu((ImageButton)findViewById(R.id.imageButton2),HistoActivity.class);

    }
    public void ecouteMenu(ImageButton a, final Class b){

        (a).setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent c=new Intent(MainActivity.this,b);
                startActivity(c);
            }
        });


    }
}
