package com.duartelobo.geststocks.model;

/**
 * Created by Duarte Lobo on 28/02/2016.
 */
public class Produto {
    int CodProd, CodArmazem, CodForn;
    String NomeProd, Descricao, U_medida;
    float Preco, Qtd;

    // construtores
    public Produto() {
    }

    public Produto(int CodProd, String NomeProd, String Descricao, float Qtd, float Preco, String U_medida, int CodArmazem, int CodForn) {
        this.CodProd = CodProd;
        this.NomeProd = NomeProd;
        this.Descricao = Descricao;
        this.Qtd = Qtd;
        this.Preco = Preco;
        this.U_medida = U_medida;
        this.CodArmazem = CodArmazem;
        this.CodForn = CodForn;
    }


    // setters e getters
    public void setCodProd(int CodProd) {
        this.CodProd = CodProd;
    }

    public long getCodProd() {
        return this.CodProd;
    }

    public void setNomeProd(String NomeProd) {
        this.NomeProd = NomeProd;
    }

    public String getNomeProd() {
        return this.NomeProd;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getDescricao() {
        return this.Descricao;
    }

    public void setQtd(float Qtd) {
        this.Qtd = Qtd;
    }

    public float getQtd() {
        return this.Qtd;
    }

    public void setPreco(float Preco) {
        this.Preco = Preco;
    }

    public float getPreco() {
        return this.Preco;
    }

    public void setU_medida(String U_medida) {
        this.U_medida = U_medida;
    }

    public String getU_medida() {
        return this.U_medida;
    }

    public void setCodArmazem(int CodArmazem) {
        this.CodArmazem = CodArmazem;
    }

    public int getCodArmazem() {
        return this.CodArmazem;
    }

    public void setCodForn(int CodForn) {
        this.CodForn = CodForn;
    }

    public int getCodForn() {
        return this.CodForn;
    }
}
