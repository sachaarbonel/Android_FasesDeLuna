package com.journaldev.navigationdrawer.models;

/**
 * Created by Sacha on 04/10/2017.
 */

public class FaseLunar {

    private int recursoImagen;
    private String nombre;
    private String nombreAlternativo;
    private String descripcion;


    public FaseLunar(int imgRes, String n, String altN, String desc){
        recursoImagen = imgRes;
        nombre = n;
        nombreAlternativo = altN;
        descripcion = desc;
    }

    public int getImageResource() {
        return recursoImagen;
    }

    public String getName() {
        return nombre;
    }

    public String getAltName() {
        return nombreAlternativo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
