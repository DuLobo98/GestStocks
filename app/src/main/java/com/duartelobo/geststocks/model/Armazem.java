package com.duartelobo.geststocks.model;

/**
 * Created by Duarte Lobo on 28/02/2016.
 */
public class Armazem {
    int CodArmazem;
    String NomeArmazem, Localizacao;
    // construtores
    public Armazem() {
    }

    public Armazem(int CodArmazem, String NomeArmazem, String Localizacao) {
        this.CodArmazem = CodArmazem;
        this.NomeArmazem = NomeArmazem;
        this.Localizacao = Localizacao;
    }


    // setters e getters
    public void setCodArmazem(int CodArmazem) {
        this.CodArmazem = CodArmazem;
    }

    public long getCodArmazem() {
        return this.CodArmazem;
    }

    public void setNomeArmazem(String NomeArmazem) {
        this.NomeArmazem = NomeArmazem;
    }

    public String getNomeArmazem() {
        return this.NomeArmazem;
    }

    public void setLocalizacao(String Localizacao) {
        this.Localizacao = Localizacao;
    }

    public String getLocalizacao() {
        return this.Localizacao;
    }
}
