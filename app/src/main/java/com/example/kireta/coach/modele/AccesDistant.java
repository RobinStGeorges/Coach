package com.example.kireta.coach.modele;

import android.util.Log;

import com.example.kireta.coach.controleur.Controle;
import com.example.kireta.coach.outils.AccesHTTP;
import com.example.kireta.coach.outils.AsyncResponse;
import com.example.kireta.coach.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by kIRETA on 06/12/2016.
 */

public class AccesDistant implements AsyncResponse {
    final String SERVERADDR ="http://192.168.43.18/coach/serveurcoach.php";

    private Controle controle;

    public AccesDistant(Controle controle){
        this.controle=controle;
    }
    public void processFinish(String output){
        Log.d("serveur process Finish", "************" + output);
        String[] message = output.split("%");
        if (message.length>1){
            if (message[0].equals("enreg")){
                Log.d("*******************","Enreg"+message);
            }
            if (message[0].equals("dernier")){
                try {
                    JSONObject info =new JSONObject(message[1]);
                    Integer poids = info.getInt("poids");
                    Integer taille = info.getInt("taille");
                    Integer age = info.getInt("age");
                    Integer sexe = info.getInt("sexe");
                    Date date = MesOutils.convertStringToDate(info.getString("datemesure"));
                    controle.setProfil(new Profil(poids, taille,age,sexe,date));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (message[0].equals("Erreur !")){
                Log.d("*******************","Erreur"+message);
            }
        }
    }
    public void envoi(String operation, JSONArray lesDonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        accesDonnees.execute(SERVERADDR);
    }

}
