package com.duartelobo.geststocks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.duartelobo.geststocks.helper.DatabaseHelper;
import com.duartelobo.geststocks.model.Compra;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class RegistarActivity extends AppCompatActivity {

    DatabaseHelper db;
    private EditText c, n, d, p, q;
    private Spinner u, a, f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);
        db = new DatabaseHelper(getApplicationContext());
        c = (EditText) findViewById(R.id.txt_cod);
        q = (EditText) findViewById(R.id.txt_qtd);
        n = (EditText) findViewById(R.id.txt_nome);
        p = (EditText) findViewById(R.id.txt_preco);
        d = (EditText) findViewById(R.id.txt_desc);
        u = (Spinner) findViewById(R.id.spinner_u_medida);
        a = (Spinner) findViewById(R.id.spinner_armazem);
        f = (Spinner) findViewById(R.id.spinner_fornecedor);
        //u_medida
        Spinner spinner = (Spinner) findViewById(R.id.spinner_u_medida);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.u_medida, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        //armazem

        Spinner sp = (Spinner) findViewById(R.id.spinner_armazem);
        // Create an ArrayAdapter using the string array and a default spinner layout
        List<String> todosArmazens = db.listarCodArmazem();
        final ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, todosArmazens);
        // Specify the layout to use when the list of choices appears
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sp.setAdapter(adapt);

        //Fornecedor

        Spinner sp_f = (Spinner) findViewById(R.id.spinner_fornecedor);
        // Create an ArrayAdapter using the string array and a default spinner layout
        List<String> todosFornecedores = db.listarCodForne();
        final ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, todosFornecedores);
        // Specify the layout to use when the list of choices appears
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sp_f.setAdapter(adap);
    }
        public void registar_produto(View view)
    {
        int cod, cod_a, cod_forn, cod_compra=0;
        String nome, desc, um;
        float preco, qtd;
        List<Compra> codigo_compra = db.listarCodCompra();
        for(Compra cod_c : codigo_compra)
        {
            cod_compra = (int)cod_c.getCodCompra()+1;
        }
        cod = Integer.parseInt(c.getText().toString());
        nome = n.getText().toString();
        desc = d.getText().toString();
        qtd = Float.parseFloat(q.getText().toString());
        preco = Float.parseFloat(p.getText().toString());
        um = u.getSelectedItem().toString();
        cod_a = Integer.parseInt(a.getSelectedItem().toString());
        cod_forn = Integer.parseInt(f.getSelectedItem().toString());
        String data = DateFormat.getDateInstance().format(new Date());
        db.criarProduto(cod, nome, desc, qtd, preco, um, cod_a, cod_forn);
        db.registarCompra(cod_compra,cod,cod_forn,qtd,data);
        Toast.makeText(RegistarActivity.this, "Registo efetuado com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
