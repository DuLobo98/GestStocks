package com.duartelobo.geststocks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.duartelobo.geststocks.helper.DatabaseHelper;

import java.util.List;

public class ListarActivity extends AppCompatActivity {

    GridView l;
    TextView t_codigoP,t_nomeP,t_qtdP,t_armaP, t_codigoA, t_nomeA, t_localA, t_codigoF, t_nomeF, t_moradaF, t_contactoF, t_codigoC, t_prodC, t_fornC, t_qtdC, t_dataC, t_codigoV, t_prodV, t_qtdV, t_dataV;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        Spinner lista = (Spinner) findViewById(R.id.spinner_listar);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(this,
                R.array.listar, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        lista.setAdapter(adapt);
    }
    public void act_listar(View view){
        Spinner lista = (Spinner) findViewById(R.id.spinner_listar);
        db = new DatabaseHelper(getApplicationContext());
        l = (GridView) findViewById(R.id.gridView);
        t_codigoP = (TextView) findViewById(R.id.text_codigoP);
        t_nomeP = (TextView) findViewById(R.id.text_nomeP);
        t_qtdP = (TextView) findViewById(R.id.text_qtdP);
        t_armaP = (TextView) findViewById(R.id.text_armaP);
        t_codigoA = (TextView) findViewById(R.id.text_codigoA);
        t_nomeA = (TextView) findViewById(R.id.text_nomeA);
        t_localA = (TextView) findViewById(R.id.text_localA);
        t_codigoF = (TextView) findViewById(R.id.text_codForn);
        t_nomeF = (TextView) findViewById(R.id.text_nomeF);
        t_moradaF = (TextView) findViewById(R.id.text_moradaF);
        t_contactoF = (TextView) findViewById(R.id.text_contactoF);
        t_codigoC = (TextView) findViewById(R.id.text_CodigoC);
        t_prodC = (TextView) findViewById(R.id.text_prodC);
        t_fornC = (TextView) findViewById(R.id.text_fornC);
        t_qtdC = (TextView) findViewById(R.id.text_qtdC);
        t_dataC = (TextView) findViewById(R.id.text_dataC);
        t_codigoV = (TextView) findViewById(R.id.text_CodigoV);
        t_prodV = (TextView) findViewById(R.id.text_prodV);
        t_qtdV = (TextView) findViewById(R.id.text_qtdV);
        t_dataV = (TextView) findViewById(R.id.text_dataV);
        String li = lista.getSelectedItem().toString();
        if(li.equals("Produtos"))
        {
            l.setNumColumns(4);
            t_codigoA.setVisibility(View.INVISIBLE);
            t_nomeA.setVisibility(View.INVISIBLE);
            t_localA.setVisibility(View.INVISIBLE);
            t_codigoF.setVisibility(View.INVISIBLE);
            t_nomeF.setVisibility(View.INVISIBLE);
            t_moradaF.setVisibility(View.INVISIBLE);
            t_contactoF.setVisibility(View.INVISIBLE);
            t_codigoP.setVisibility(View.VISIBLE);
            t_nomeP.setVisibility(View.VISIBLE);
            t_qtdP.setVisibility(View.VISIBLE);
            t_armaP.setVisibility(View.VISIBLE);
            t_codigoC.setVisibility(View.INVISIBLE);
            t_prodC.setVisibility(View.INVISIBLE);
            t_fornC.setVisibility(View.INVISIBLE);
            t_qtdC.setVisibility(View.INVISIBLE);
            t_dataC.setVisibility(View.INVISIBLE);
            t_codigoV.setVisibility(View.INVISIBLE);
            t_prodV.setVisibility(View.INVISIBLE);
            t_qtdV.setVisibility(View.INVISIBLE);
            t_dataV.setVisibility(View.INVISIBLE);
            List<String> todosArtigos = db.listarProdutos();
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todosArtigos);
            l.setAdapter(adapter);
        }
        else if(li.equals("Armazéns"))
        {
            l.setNumColumns(3);
            t_codigoP.setVisibility(View.INVISIBLE);
            t_nomeA.setVisibility(View.INVISIBLE);
            t_qtdP.setVisibility(View.INVISIBLE);
            t_armaP.setVisibility(View.INVISIBLE);
            t_codigoF.setVisibility(View.INVISIBLE);
            t_nomeF.setVisibility(View.INVISIBLE);
            t_moradaF.setVisibility(View.INVISIBLE);
            t_contactoF.setVisibility(View.INVISIBLE);
            t_codigoA.setVisibility(View.VISIBLE);
            t_nomeA.setVisibility(View.VISIBLE);
            t_localA.setVisibility(View.VISIBLE);
            t_codigoP.setVisibility(View.INVISIBLE);
            t_nomeP.setVisibility(View.INVISIBLE);
            t_qtdP.setVisibility(View.INVISIBLE);
            t_armaP.setVisibility(View.INVISIBLE);
            t_codigoC.setVisibility(View.INVISIBLE);
            t_prodC.setVisibility(View.INVISIBLE);
            t_fornC.setVisibility(View.INVISIBLE);
            t_qtdC.setVisibility(View.INVISIBLE);
            t_dataC.setVisibility(View.INVISIBLE);
            t_codigoV.setVisibility(View.INVISIBLE);
            t_prodV.setVisibility(View.INVISIBLE);
            t_qtdV.setVisibility(View.INVISIBLE);
            t_dataV.setVisibility(View.INVISIBLE);
            List<String> todosArmazens = db.listarArmazens();
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todosArmazens);
            l.setAdapter(adapter);
        }
        else if(li.equals("Fornecedores"))
        {
            l.setNumColumns(4);
            t_codigoP.setVisibility(View.INVISIBLE);
            t_nomeA.setVisibility(View.INVISIBLE);
            t_qtdP.setVisibility(View.INVISIBLE);
            t_armaP.setVisibility(View.INVISIBLE);
            t_codigoA.setVisibility(View.INVISIBLE);
            t_nomeP.setVisibility(View.INVISIBLE);
            t_localA.setVisibility(View.INVISIBLE);
            t_codigoF.setVisibility(View.VISIBLE);
            t_nomeF.setVisibility(View.VISIBLE);
            t_moradaF.setVisibility(View.VISIBLE);
            t_contactoF.setVisibility(View.VISIBLE);
            t_codigoP.setVisibility(View.INVISIBLE);
            t_nomeP.setVisibility(View.INVISIBLE);
            t_qtdP.setVisibility(View.INVISIBLE);
            t_armaP.setVisibility(View.INVISIBLE);
            t_codigoC.setVisibility(View.INVISIBLE);
            t_prodC.setVisibility(View.INVISIBLE);
            t_fornC.setVisibility(View.INVISIBLE);
            t_qtdC.setVisibility(View.INVISIBLE);
            t_dataC.setVisibility(View.INVISIBLE);
            t_codigoV.setVisibility(View.INVISIBLE);
            t_prodV.setVisibility(View.INVISIBLE);
            t_qtdV.setVisibility(View.INVISIBLE);
            t_dataV.setVisibility(View.INVISIBLE);
            List<String> todosFornecedores = db.listarFornecedores();
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todosFornecedores);
            l.setAdapter(adapter);
        }
        else if(li.equals("Compras"))
        {
            l.setNumColumns(5);
            t_codigoP.setVisibility(View.INVISIBLE);
            t_nomeA.setVisibility(View.INVISIBLE);
            t_qtdP.setVisibility(View.INVISIBLE);
            t_armaP.setVisibility(View.INVISIBLE);
            t_codigoA.setVisibility(View.INVISIBLE);
            t_nomeP.setVisibility(View.INVISIBLE);
            t_localA.setVisibility(View.INVISIBLE);
            t_codigoF.setVisibility(View.INVISIBLE);
            t_nomeF.setVisibility(View.INVISIBLE);
            t_moradaF.setVisibility(View.INVISIBLE);
            t_contactoF.setVisibility(View.INVISIBLE);
            t_codigoP.setVisibility(View.INVISIBLE);
            t_nomeP.setVisibility(View.INVISIBLE);
            t_qtdP.setVisibility(View.INVISIBLE);
            t_armaP.setVisibility(View.INVISIBLE);
            t_codigoC.setVisibility(View.VISIBLE);
            t_prodC.setVisibility(View.VISIBLE);
            t_fornC.setVisibility(View.VISIBLE);
            t_qtdC.setVisibility(View.VISIBLE);
            t_dataC.setVisibility(View.VISIBLE);
            t_codigoV.setVisibility(View.INVISIBLE);
            t_prodV.setVisibility(View.INVISIBLE);
            t_qtdV.setVisibility(View.INVISIBLE);
            t_dataV.setVisibility(View.INVISIBLE);
            List<String> todasCompras = db.listarCompras();
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todasCompras);
            l.setAdapter(adapter);
        }
        else if(li.equals("Vendas"))
        {
            l.setNumColumns(4);
            t_codigoP.setVisibility(View.INVISIBLE);
            t_nomeA.setVisibility(View.INVISIBLE);
            t_qtdP.setVisibility(View.INVISIBLE);
            t_armaP.setVisibility(View.INVISIBLE);
            t_codigoA.setVisibility(View.INVISIBLE);
            t_nomeP.setVisibility(View.INVISIBLE);
            t_localA.setVisibility(View.INVISIBLE);
            t_codigoF.setVisibility(View.INVISIBLE);
            t_nomeF.setVisibility(View.INVISIBLE);
            t_moradaF.setVisibility(View.INVISIBLE);
            t_contactoF.setVisibility(View.INVISIBLE);
            t_codigoP.setVisibility(View.INVISIBLE);
            t_nomeP.setVisibility(View.INVISIBLE);
            t_qtdP.setVisibility(View.INVISIBLE);
            t_armaP.setVisibility(View.INVISIBLE);
            t_codigoC.setVisibility(View.INVISIBLE);
            t_prodC.setVisibility(View.INVISIBLE);
            t_fornC.setVisibility(View.INVISIBLE);
            t_qtdC.setVisibility(View.INVISIBLE);
            t_dataC.setVisibility(View.INVISIBLE);
            t_codigoV.setVisibility(View.VISIBLE);
            t_prodV.setVisibility(View.VISIBLE);
            t_qtdV.setVisibility(View.VISIBLE);
            t_dataV.setVisibility(View.VISIBLE);
            List<String> todasVendas = db.listarVendas();
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todasVendas);
            l.setAdapter(adapter);
        }
    }
}
