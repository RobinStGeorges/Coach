package com.example.kireta.coach.controleur;

import android.content.Context;

import com.example.kireta.coach.modele.Profil;
import com.example.kireta.coach.outils.Serializer;

/**
 * Created by kIRETA on 12/11/2016.
 */

public final class Controle {
    private static Controle instance=null;
    private static Profil profil;
    private static String nomFic=saveProfil;

    private Controle() {
        super();
    }

    public static final Controle getInstance(){
        if (Controle.instance==null){
            Controle.instance = new Controle() ;

        }
        return Controle.instance;
    }

    /**
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe 1 pour homme 0 pour femme
     */
    public static void creerProfil(int poids,int taille,int age,int sexe, Context context){
        profil=new Profil(poids,taille,age,sexe);
        Serializer.serialize(saveProfil,profil,context);
    }

    /**
     *
     * @return
     */
    public static float getIMG(){
        return profil.getImg();
    }

    /**
     *
     * @return
     */
    public static String getMessage(){
        return profil.getMessage();
    }
    private static void recupSerialize(Context context){
        
    }
}
