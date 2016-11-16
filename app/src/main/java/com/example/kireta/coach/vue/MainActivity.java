package com.example.kireta.coach.vue;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kireta.coach.R;
import com.example.kireta.coach.controleur.Controle;
import com.example.kireta.coach.modele.Profil;



public class MainActivity extends AppCompatActivity {
    //txtPoids, txtTaille, txtAge, rdHomme, lblIMG et imgSmiley
    private TextView txtPoids;
    private TextView txtTaille;
    private TextView txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private ImageView imgSmiley;
    private TextView lblIMG;
    private Button btnCalc;
    private Controle controle;

//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        txtPoids = (EditText) findViewById(R.id.editText) ;
        txtTaille = (EditText) findViewById(R.id.editText2) ;
        txtAge = (EditText) findViewById(R.id.editText3) ;
        rdHomme = (RadioButton) findViewById(R.id.rdHomme) ;
        rdFemme = (RadioButton) findViewById(R.id.rdFemme) ;
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley) ;
        lblIMG = (TextView) findViewById(R.id.lblIMG) ;
        btnCalc = (Button) findViewById(R.id.btnCalc) ;
        controle = Controle.getInstance(this);
        ecouteCalcul();
    }

    /**
     *
     */
    private void ecouteCalcul(){
        ((Button) findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Test du bouton calculer avec la methode maketest
                //Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();

                //Recuperation du poids, de la taille ,et de l'age
                Integer poids = Integer.parseInt(txtPoids.getText().toString());
                Integer taille = Integer.parseInt(txtTaille.getText().toString());
                Integer age = Integer.parseInt(txtAge.getText().toString());

                //recuperation du sexe
                int sexe =0;
                if (rdHomme.isChecked()){
                    sexe=1;
                }
                //Test si les champs sont vides
                if(poids==0 || taille==0 || age==0){
                    Toast.makeText(MainActivity.this, "veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
                }
                else{
                    //affichage du message et de la bonne image
                    aficheResult( poids, taille, age, sexe);
                }

            }
            private void aficheResult(int poids,int taille,int age,int sexe){
                controle.creerProfil( poids, taille, age, sexe,MainActivity.this);
                float IMG=controle.getIMG();
                String message = controle.getMessage();
                String IMGM=String.format("{0}",IMG);
                //test du message pour renvoyer l'image
                if(message=="trop faible"){
                    //Change l'image
                    imgSmiley.setImageResource(R.drawable.maigre);
                    //Change la couleur du texte
                    lblIMG.setTextColor(Color.RED);
                    //defini le texte
                    lblIMG.setText(IMG+" : trop faible");
                }
                else if(message=="normal"){
                    imgSmiley.setImageResource(R.drawable.normal);
                    lblIMG.setTextColor(Color.GREEN);
                    lblIMG.setText(IMG+" : normal");
                }
                else{
                    //Message = trop élevé par default
                    imgSmiley.setImageResource(R.drawable.graisse);
                    lblIMG.setTextColor(Color.RED);
                    lblIMG.setText(IMG+" : trop élevé");
                }

            }
        });
    }

    /**
     *
     */
    private void recupProfil(){
        if(controle.getTaille()!=null) {
            txtTaille.setText("" + controle.getTaille());
            txtAge.setText("" + controle.getAge());
            txtPoids.setText("" + controle.getPoids());
            findViewById(R.id.btnCalc).performClick();
        }
    }
}
