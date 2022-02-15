package com.duartelobo.geststocks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.duartelobo.geststocks.helper.DatabaseHelper;
import com.duartelobo.geststocks.model.Utilizador;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText user;
    private EditText pass;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void login(View view){
        db = new DatabaseHelper(getApplicationContext());
        int check=0;
        Intent login_admin = new Intent(this, MenuActivity.class);
        Intent login_cli = new Intent(this, MenuClienteActivity.class);
        user = (EditText) findViewById(R.id.txt_user);
        pass = (EditText) findViewById(R.id.txt_pass);
        String utilizador = user.getText().toString();
        String password = pass.getText().toString();
        String d_admin="";
        List<Utilizador> todosUtilizadores = db.listarUser();
        for (Utilizador users : todosUtilizadores)
        {
            if(utilizador.equals(users.getNomeUtilizador()) && password.equals(users.getPassword()))
            {
                check = 1;
                d_admin = String.valueOf(users.getAdmin());
                break;
            }
            else
            {
                check = 0;
            }
        }
        Log.d("Aviso",d_admin);
        if(check == 1)
        {
            if(d_admin.equals("SIM"))
            {
                startActivity(login_admin);
                Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
            }
            else if (d_admin.equals("NÃO"))
            {
                startActivity(login_cli);
                Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        }
        else if(check == 0)
        {
            Toast.makeText(LoginActivity.this,"Nome de Utilizador ou Password Errados!",Toast.LENGTH_SHORT).show();
        }
    }
}
