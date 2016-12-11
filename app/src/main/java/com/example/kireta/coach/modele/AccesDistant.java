package com.example.kireta.coach.modele;

import android.util.Log;

import com.example.kireta.coach.outils.AccesHTTP;
import com.example.kireta.coach.outils.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by kIRETA on 06/12/2016.
 */

public class AccesDistant implements AsyncResponse {
    final String SERVERADDR ="http://192.168.43.18/coach/serveurcoach.php";

    public AccesDistant(){
        super() ;
    }
    public void processFinish(String output){
        Log.d("serveur process Finish", "************" + output);
        String[] message = output.split("%");
        if (message.length>1){
            if (message[0]=="enreg"){
                Log.d("*******************","Enreg"+message);
            }
            if (message[0]=="dernier"){
                Log.d("*******************","Dernier"+message);
            }
            if (message[0]=="Erreur !"){
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
