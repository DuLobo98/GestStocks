package com.duartelobo.geststocks.model;

/**
 * Created by Duarte Lobo on 28/02/2016.
 */
public class Fornecedor {
    int CodForn, Contacto;
    String NomeForn, Morada;
    // construtores
    public Fornecedor() {
    }

    public Fornecedor(int CodForn, String NomeForn, String Morada, int Contacto) {
        this.CodForn = CodForn;
        this.NomeForn = NomeForn;
        this.Morada = Morada;
        this.Contacto = Contacto;
    }


    // setters e getters
    public void setCodForn(int CodForn) {
        this.CodForn = CodForn;
    }

    public long getCodForn() {
        return this.CodForn;
    }

    public void setNomeForn(String NomeForn) {
        this.NomeForn = NomeForn;
    }

    public String getNomeForn() {
        return this.NomeForn;
    }

    public void setMorada(String Morada) {
        this.Morada = Morada;
    }

    public String getMorada() {
        return this.Morada;
    }
    public void setContacto(int Contacto) {
        this.Contacto = Contacto;
    }

    public int getContacto() {
        return this.Contacto;
    }
}
