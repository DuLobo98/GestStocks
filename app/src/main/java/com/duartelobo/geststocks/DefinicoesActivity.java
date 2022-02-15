package com.duartelobo.geststocks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.duartelobo.geststocks.helper.DatabaseHelper;
import com.duartelobo.geststocks.model.Utilizador;

import java.util.List;

public class DefinicoesActivity extends AppCompatActivity {
    private EditText n,p;
    private CheckBox a;
    private GridView l;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definicoes);
        db = new DatabaseHelper(getApplicationContext());
        n = (EditText) findViewById(R.id.editText2);
        p = (EditText) findViewById(R.id.editText5);
        a = (CheckBox) findViewById(R.id.checkBox);
        l = (GridView) findViewById(R.id.gridView3);
        List<String> todosUtilizadores = db.listarUtilizadores();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todosUtilizadores);
        l.setAdapter(adapter);
    }
    public void registar_user(View view)
    {
        int n_cod=0;
        String nome, pass,admin;
        List<Utilizador> codigo_user = db.listarCodUtilizador();
        for(Utilizador cod_user : codigo_user)
        {
            n_cod = (int)cod_user.getCodUtilizador()+1;
        }
        nome = n.getText().toString();
        pass = p.getText().toString();
        if(a.isChecked())
        {
            admin="SIM";
        }
        else
        {
            admin="NÃO";
        }
        db.criarUtilizador(n_cod, nome, pass, admin);
        Toast.makeText(DefinicoesActivity.this, "Registo efetuado com sucesso!", Toast.LENGTH_SHORT).show();
        List<String> todosUtilizadores = db.listarUtilizadores();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todosUtilizadores);
        l.setAdapter(adapter);
    }
}
