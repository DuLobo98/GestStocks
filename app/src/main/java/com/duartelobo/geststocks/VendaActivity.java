package com.duartelobo.geststocks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.duartelobo.geststocks.helper.DatabaseHelper;
import com.duartelobo.geststocks.model.Produto;
import com.duartelobo.geststocks.model.Venda;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class VendaActivity extends AppCompatActivity {

    DatabaseHelper db;
    float qtd_atual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);
        db = new DatabaseHelper(getApplicationContext());
    }
    public void act_verificar(View view)
    {
        EditText et = (EditText) findViewById(R.id.editText4);
        TextView t1 = (TextView) findViewById(R.id.textView25);
        TextView t2 = (TextView) findViewById(R.id.textView27);
        TextView t3 = (TextView) findViewById(R.id.textView29);
        int cod = Integer.parseInt(et.getText().toString());
        List<Produto> todosArtigos = db.info_venda(cod);
        for (Produto artigo : todosArtigos) {
            t1.setText(artigo.getNomeProd());
            t2.setText(String.valueOf(artigo.getQtd()));
            qtd_atual = artigo.getQtd();
            t3.setText(String.valueOf(artigo.getPreco()));
        }
    }
    public void act_venda(View view)
    {
        EditText et = (EditText) findViewById(R.id.editText4);
        EditText et1 = (EditText) findViewById(R.id.editText6);
        int cod = Integer.parseInt(et.getText().toString());
        float qtd = Float.parseFloat(et1.getText().toString());
        float qtd_final = qtd_atual - qtd;
        int cod_venda=0;
        List<Venda> codigo_venda = db.listarCodVenda();
        for(Venda cod_v : codigo_venda)
        {
            cod_venda = (int)cod_v.getCodVenda()+1;
        }
        String data = DateFormat.getDateInstance().format(new Date());
        if(qtd>qtd_atual)
        {
            Toast.makeText(VendaActivity.this, "A quantidade indicada é maior que a quantidade em stock!", Toast.LENGTH_SHORT).show();
        }
        else if(qtd==qtd_atual)
        {
            db.removerProduto(cod);
            db.registarVenda(cod_venda,cod,qtd,data);
            Toast.makeText(VendaActivity.this, "Venda efetuada com sucesso!", Toast.LENGTH_SHORT).show();
        }
        else if(qtd<qtd_atual)
        {
            db.atualizarQtd(cod,qtd_final);
            db.registarVenda(cod_venda,cod,qtd,data);
            Toast.makeText(VendaActivity.this, "Venda efetuada com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }
}
