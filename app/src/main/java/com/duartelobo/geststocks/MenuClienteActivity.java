package com.duartelobo.geststocks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);
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
}
