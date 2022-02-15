package com.duartelobo.geststocks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void vender(View view){
        Intent vender = new Intent(this, VendaActivity.class);
        startActivity(vender);
    }
    public void registar(View view){
        Intent registar = new Intent(this, RegistarActivity.class);
        startActivity(registar);
    }
    public void procurar(View view){
        Intent procurar = new Intent(this, ProcurarActivity.class);
        startActivity(procurar);
    }
    public void listar(View view){
        Intent listar = new Intent(this, ListarActivity.class);
        startActivity(listar);
    }
    public void fornecedores(View view){
        Intent fornecedores = new Intent(this, FornecedoresActivity.class);
        startActivity(fornecedores);
    }
    public void armazem(View view){
        Intent armazem = new Intent(this, ArmazemActivity.class);
        startActivity(armazem);
    }
    public void definicoes(View view) {
        Intent def = new Intent(this, DefinicoesActivity.class);
        startActivity(def);
    }
}
