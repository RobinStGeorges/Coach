package com.example.kireta.coach.vue;
import com.example.kireta.coach.R;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
//test
import com.example.kireta.coach.controleur.Controle;


public class CalculActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_calcul);
        init();
        //Pour le bouton menu
        creerMenu();
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
        //Recuperation du profil
        //mis en comentaire pour la partie sql
        //page47
        //recupProfil();

    }

    /**
     *
     */
    private void ecouteCalcul(){
        ((Button) findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Test du bouton calculer avec la methode maketest
                //Toast.makeText(CalculActivity.this, "test", Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(CalculActivity.this, "veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
                }
                else{
                    //affichage du message et de la bonne image
                    aficheResult( poids, taille, age, sexe);
                }

            }
            private void aficheResult(int poids,int taille,int age,int sexe){
                controle.creerProfil( poids, taille, age, sexe,CalculActivity.this);
                float IMG=controle.getIMG();
                String message = controle.getMessage();

                //test du message pour renvoyer l'image
                if(message=="trop faible"){
                    //Change l'image
                    imgSmiley.setImageResource(R.drawable.maigre);
                    //Change la couleur du texte
                    lblIMG.setTextColor(Color.RED);
                    //defini le texte
                    lblIMG.setText(String.format("%.01f",IMG)+" : trop faible");
                }
                else if(message=="normal"){
                    imgSmiley.setImageResource(R.drawable.normal);
                    lblIMG.setTextColor(Color.GREEN);
                    lblIMG.setText(String.format("%.01f",IMG)+" : normal");
                }
                else{
                    //Message = trop élevé par default
                    imgSmiley.setImageResource(R.drawable.graisse);
                    lblIMG.setTextColor(Color.RED);
                    lblIMG.setText(String.format("%.01f",IMG)+" : trop élevé");
                }

            }
        });
    }

    /**
     *
     */
    public void recupProfil(){
        //Profil profilR=AccesLocal.recupDernier();
        //mettre les .getxxx de profilR
        if(controle.getTaille()!= null) {

            //donne la valeur sauvegardé
            txtTaille.setText("" + controle.getTaille());
            //txtTaille.setText("" + profilR.getTaille());

            //Log.d("Taille=",txtTaille);
            txtAge.setText("" + controle.getAge());
            //txtAge.setText("" + profilR.getAge());

            txtPoids.setText("" + controle.getPoids());
            //txtPoids.setText("" + profilR.getPoids());

            //imite un clic du bouton
            findViewById(R.id.btnCalc).performClick();
        }
    }
    //bouton retour menu
    public void creerMenu(){
        //appel de ecoute menu pou clic sur l'image retourn menu
        ecouteMenu((ImageButton)findViewById(R.id.imgBtnMenu),MainActivity.class);


    }
    public void ecouteMenu(ImageButton a, final Class b){

        (a).setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent c=new Intent(CalculActivity.this,b);
                startActivity(c);
            }
        });
    }
}
