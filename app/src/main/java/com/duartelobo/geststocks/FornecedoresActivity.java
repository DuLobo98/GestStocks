package com.duartelobo.geststocks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.duartelobo.geststocks.helper.DatabaseHelper;
import com.duartelobo.geststocks.model.Fornecedor;

import java.util.List;

public class FornecedoresActivity extends AppCompatActivity {
    private EditText n,m,c;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedores);
        db = new DatabaseHelper(getApplicationContext());
        n = (EditText) findViewById(R.id.txt_nomeForn);
        m = (EditText) findViewById(R.id.txt_moradaForn);
        c = (EditText) findViewById(R.id.txt_contactoForn);
    }
    public void registar_forn(View view)
    {
        int n_cod=0,contactoF;
        String nomeF, moradaF;
        List<Fornecedor> codigo_forn = db.listarCodForn();
        for(Fornecedor cod_f : codigo_forn)
        {
            n_cod = (int)cod_f.getCodForn()+1;
        }
        nomeF = n.getText().toString();
        moradaF = m.getText().toString();
        contactoF = Integer.parseInt(c.getText().toString());
        db.criarForn(n_cod, nomeF, moradaF, contactoF);
        Toast.makeText(FornecedoresActivity.this, "Registo efetuado com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
