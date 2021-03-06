package com.example.kireta.coach.modele;

import com.example.kireta.coach.outils.Serializer;
import com.example.kireta.coach.modele.AccesDistant;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kIRETA on 12/11/2016.
 */

public class Profil implements Serializable{
    // Constantes
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus

    //Variables

    private int poids;
    private Integer taille;
    private int age;
    private int sexe; //S=0 pour une femme, =1 pour un homme
    private float img;
    private String message;
    private Date dateMesure;

    //Contructeur

    /**
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public Profil(int poids,int taille, int age,int sexe,Date dateMesure) {
        this.sexe = sexe;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
        this.dateMesure=dateMesure;
        calculIMG();
        resultIMG();
    }
    //Les getters

    /**
     *
     * @return
     */
    public int getPoids() {
        return poids;
    }

    /**
     *
     * @return
     */
    public Integer getTaille() {
        return taille;
    }

    /**
     *
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @return
     */
    public int getSexe() {
        return sexe;
    }

    /**
     *
     * @return
     */
    public float getImg() {
        return img;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    // Fonction qui calcul l'IMG

    /**
     *
     */
    private void calculIMG() {
        //formule:(1,2 * Poids/Taille²) + (0,23 * age) - (10,83 * S) - 5,4

        img = (float)((1.2 * poids/(((float)taille/100)*((float)taille/100)) + (0.23 * age) - (10.83 * sexe) - 5.4));
    }

    //Methode resultIMG ne prend rien et ne retourne rien

    /**
     *
     */
    private void resultIMG() {
        if (sexe == 0) {
            if (img <= minFemme) {
                message = "trop faible";
            } else if (img <= maxFemme) {
                message = "normal";
            } else {
                message = "trop élevé";
            }
        }
        else{
            if (img <= minHomme) {
                message = "trop faible";
            } else if (img <= maxHomme) {
                message = "normal";
            } else {
                message = "trop élevé";
            }
        }
    }

    /**
     *
     * @return
     */
    public Date getDateMesure() {
        return dateMesure;
    }
    public JSONArray convertToJSONArray(){
        List uneListe =new ArrayList();
        uneListe.add(dateMesure);
        uneListe.add(poids);
        uneListe.add(taille);
        uneListe.add(age);
        uneListe.add(sexe);
        JSONArray Jsona= new JSONArray(uneListe) ;
        return Jsona;
    }

}


