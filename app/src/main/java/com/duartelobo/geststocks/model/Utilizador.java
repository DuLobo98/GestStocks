package com.duartelobo.geststocks.model;

/**
 * Created by Duarte Lobo on 28/02/2016.
 */
public class Utilizador {
    int CodUtilizador;
    String NomeUtilizador, Password, Admin;


    // construtores
    public Utilizador() {
    }

    public Utilizador(int CodUtilizador, String NomeUtilizador, String Password, String Admin) {
        this.CodUtilizador = CodUtilizador;
        this.NomeUtilizador = NomeUtilizador;
        this.Password = Password;
        this.Admin = Admin;
    }


    // setters e getters
    public void setCodUtilizador(int CodUtilizador) {
        this.CodUtilizador = CodUtilizador;
    }

    public long getCodUtilizador() {
        return this.CodUtilizador;
    }

    public void setNomeUtilizador(String NomeUtilizador) {
        this.NomeUtilizador = NomeUtilizador;
    }

    public String getNomeUtilizador() {
        return this.NomeUtilizador;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setAdmin(String Admin) {
        this.Admin = Admin;
    }

    public String getAdmin() {
        return this.Admin;
    }
}
