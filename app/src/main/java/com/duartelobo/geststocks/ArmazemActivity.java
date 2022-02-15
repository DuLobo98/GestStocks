package com.duartelobo.geststocks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.duartelobo.geststocks.helper.DatabaseHelper;
import com.duartelobo.geststocks.model.Armazem;

import java.util.List;

public class ArmazemActivity extends AppCompatActivity {
    private EditText n,l;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armazem);
        db = new DatabaseHelper(getApplicationContext());
        n = (EditText) findViewById(R.id.txtNomeArmazem);
        l = (EditText) findViewById(R.id.txtLocalizacao);
    }
    public void registar_armazem(View view)
    {
        int n_cod=0;
        String nomeA, localizacaoA;
        List<Armazem> codigo_arm = db.listarCodArm();
        for(Armazem cod_a : codigo_arm)
        {
            n_cod = (int)cod_a.getCodArmazem()+1;
        }
        nomeA = n.getText().toString();
        localizacaoA = l.getText().toString();
        db.criarArmazem(n_cod, nomeA, localizacaoA);
        Toast.makeText(ArmazemActivity.this, "Registo efetuado com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
