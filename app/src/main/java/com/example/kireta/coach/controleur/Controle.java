package com.example.kireta.coach.controleur;

import android.content.Context;

import com.example.kireta.coach.modele.AccesLocal;
import com.example.kireta.coach.modele.Profil;
import com.example.kireta.coach.outils.Serializer;
import android.util.Log;
import java.util.Date;

/**
 * Created by kIRETA on 12/11/2016.
 */

public final class Controle {
    private static Controle instance=null;
    private static Profil profil;
    private static String nomFic= "saveProfil";
    private static AccesLocal accesLocal;

    private Controle() {
        super();
    }

    public static final Controle getInstance(Context context){
        if (Controle.instance==null){
            Controle.instance = new Controle() ;
            accesLocal=new AccesLocal(context);//Context???
            profil=accesLocal.recupDernier();
            recupSerialize(context);

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
    public  void creerProfil(int poids,int taille,int age,int sexe, Context context){
        profil=new Profil(poids,taille,age,sexe,new Date());
        //Enregistre l proil qui vient d'être cree
        //Mise en commentaire pour la partie sql
       // Serializer.serialize(nomFic,profil,context);
        accesLocal.ajoutProfil(profil);
    }

    /**
     *
     * @return
     */
    public float getIMG(){
        return profil.getImg();
    }

    /**
     *
     * @return
     */
    public String getMessage(){
        return profil.getMessage();
    }

    /**
     * page 35
     * @return
     */
    public Integer getTaille(){
        if (profil==null) {
            return null;
        }
         else{
            return profil.getTaille();
        }
    }
    public Integer getPoids(){
        if (profil==null) {
            return null;
        }
        else{
            return profil.getPoids();
        }
    }
    public Integer getAge(){
        if (profil==null) {
            return null;
        }
        else{
            return profil.getAge();
        }
    }
    public Integer getSexe(){
        if (profil==null) {
            return null;
        }
        else{
            return profil.getSexe();
        }
    }


    /**
     *
     * @param context
     */
    private static void recupSerialize(Context context){
        profil=(Profil)Serializer.deSerialize(nomFic,context);
    }
}
