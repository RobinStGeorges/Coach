package com.example.kireta.coach.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kireta.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

/**
 * Created by kIRETA on 16/11/2016.
 */

public class AccesLocal {
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
     *
     *
     */
    public void ajoutProfil(Profil profil){
        db=accesDB.getWritableDatabase();
        reqAP = "insert into profil values (\"" + profil.getDateMesure() + "\"," + profil.getPoids() + "," + profil.getTaille() + "," + profil.getAge() + "," + profil.getSexe() + ")";
        db.execSQL(reqAP);
    }
    public Profil recupDernier(){
        Profil unProfil=null;
        db=accesDB.getReadableDatabase();
        String reqRD="SELECT * FROM profil ORDER BY datemesure ASC";
        Cursor curseur;
        curseur=db.rawQuery(reqRD,null);
        curseur.moveToFirst();
        //if (curseur.moveToNext()){
            Date date = new Date();
            int poids=curseur.getInt(1);
            int taille=curseur.getInt(2);
            int age=curseur.getInt(3);
            int sexe=curseur.getInt(4);
            unProfil=new Profil(poids,taille,age,sexe,date);
        //}

        curseur.close();
        return unProfil;
    }
}
