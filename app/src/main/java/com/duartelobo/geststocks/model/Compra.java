package com.duartelobo.geststocks.model;

/**
 * Created by Duarte Lobo on 28/02/2016.
 */
public class Compra {
    int CodCompra, CodProd, CodForn;
    float Qtd;
    String Data;
    // construtores
    public Compra() {
    }

    public Compra(int CodCompra, int CodProd, int CodForn, float Qtd, String Data) {
        this.CodCompra = CodCompra;
        this.CodProd = CodProd;
        this.CodForn = CodForn;
        this.Qtd = Qtd;
        this.Data = Data;
    }


    // setters e getters
    public void setCodCompra(int CodCompra) {
        this.CodCompra = CodCompra;
    }

    public long getCodCompra() {
        return this.CodCompra;
    }

    public void setCodProd(int CodProd) {
        this.CodProd = CodProd;
    }

    public long getCodProd() {
        return this.CodProd;
    }

    public void setCodForn(int CodForn) {
        this.CodForn = CodForn;
    }

    public long getCodForn() {
        return this.CodForn;
    }
    public void setQtd(float Qtd) {
        this.Qtd = Qtd;
    }

    public float getQtd() {
        return this.Qtd;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getData() {
        return this.Data;
    }
}
