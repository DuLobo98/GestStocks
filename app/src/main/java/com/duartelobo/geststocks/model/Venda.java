package com.duartelobo.geststocks.model;

/**
 * Created by Duarte Lobo on 28/02/2016.
 */
public class Venda {
    int CodVenda, CodProd;
    float Qtd;
    String Data;
    // construtores
    public Venda() {
    }

    public Venda(int CodVenda, int CodProd, float Qtd, String Data) {
        this.CodVenda = CodVenda;
        this.CodProd = CodProd;
        this.Qtd = Qtd;
        this.Data = Data;
    }


    // setters e getters
    public void setCodVenda(int CodVenda) {
        this.CodVenda = CodVenda;
    }

    public long getCodVenda() {
        return this.CodVenda;
    }

    public void setCodProd(int CodProd) {
        this.CodProd = CodProd;
    }

    public long getCodProd() {
        return this.CodProd;
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
