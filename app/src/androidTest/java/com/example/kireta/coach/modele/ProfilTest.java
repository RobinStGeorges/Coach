package com.example.kireta.coach.modele;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kIRETA on 12/11/2016.
 */
public class ProfilTest {
    //Creation d'un profile femme
    private Profil profil = new Profil(67,165,35,0);
    //Resultat de l'img
    private float img=(float)32.2;
    //message correspondant
    private String message="trop élevé";
    @Test
    public void getImg() throws Exception {
    assertEquals(img,profil.getImg(),(float)0.1);
    }

    @Test
    public void getMessage() throws Exception {
    assertEquals(message,profil.getMessage());
    }

}