package com.example.kireta.coach.controleur;

import com.example.kireta.coach.modele.Profil;

/**
 * Created by kIRETA on 12/11/2016.
 */

public final class Controle {
    private static Controle instance=null;
    private static Profil profil;

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
    public static void creerProfil(int poids,int taille,int age,int sexe){
        profil=new Profil(poids,taille,age,sexe);
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
}
