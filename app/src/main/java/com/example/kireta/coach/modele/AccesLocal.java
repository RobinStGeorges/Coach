package com.example.kireta.coach.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kireta.coach.outils.MySQLiteOpenHelper;

import java.util.Date;
import com.example.kireta.coach.outils.MesOutils;

/**
 * Created by kIRETA on 16/11/2016.
 */

public class AccesLocal  {
    private String nomBase="bdCoach.sqlite";
    private int versionBase=1;
    private MySQLiteOpenHelper accesDB;
    private SQLiteDatabase db;
    private String req;
    private String reqAP="";



    public AccesLocal(Context context) {
        accesDB=new MySQLiteOpenHelper(context,nomBase,versionBase);
    }

    /**
     * ajoute un profil dans la bdd sqllight
     * @param profil
     */
    public void ajoutProfil(Profil profil){
        //Log.d("date","********************"+profil.getDateMesure());
        db=accesDB.getWritableDatabase();
        reqAP = "insert into profil values (\"" + MesOutils.convertDateToString(profil.getDateMesure()) + "\"," + profil.getPoids() + "," + profil.getTaille() + "," + profil.getAge() + "," + profil.getSexe() + ")";
        Log.d("reqAjoutProfil","****************"+reqAP);
        db.execSQL(reqAP);
    }

    /**
     * selectionne la première ligne de l'enregistrement des rofi dans la bdd
     * @return
     */
    public Profil recupDernier(){
        Profil unProfil=null;
        db=accesDB.getReadableDatabase();
        String reqRD="SELECT * FROM profil ORDER BY datemesure DESC";
        Cursor curseur=db.rawQuery(reqRD,null);

        if(curseur.moveToFirst()){
            //if (curseur.moveToNext()){
            //Date date = new Date();
            Log.d("dateAvantConversion","******************"+curseur.getString(0));

            Date dateMesure = MesOutils.convertStringToDate(curseur.getString(0));

            Log.d("formatDateRecupDernier","******************"+dateMesure);
            int poids=curseur.getInt(1);
            int taille=curseur.getInt(2);
            int age=curseur.getInt(3);
            int sexe=curseur.getInt(4);
            unProfil=new Profil(poids,taille,age,sexe,dateMesure);
        }

        curseur.close();
        return unProfil;
    }
}
