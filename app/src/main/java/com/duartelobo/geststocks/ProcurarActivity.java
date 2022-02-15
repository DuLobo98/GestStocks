package com.duartelobo.geststocks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.duartelobo.geststocks.helper.DatabaseHelper;

import java.util.List;

public class ProcurarActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText t;
    TextView t32,t33,t34,t35;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar);
        Spinner spinner = (Spinner) findViewById(R.id.spinner_procura);
        GridView grid = (GridView) findViewById(R.id.gridView2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.procura, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void act_procurar(View view)
    {
        db = new DatabaseHelper(getApplicationContext());
        Spinner spinner = (Spinner) findViewById(R.id.spinner_procura);
        GridView l = (GridView) findViewById(R.id.gridView2);
        t = (EditText) findViewById(R.id.editText3);
        String op = spinner.getSelectedItem().toString();
        t32 = (TextView) findViewById(R.id.textView32);
        t33 = (TextView) findViewById(R.id.textView33);
        t34 = (TextView) findViewById(R.id.textView34);
        t35 = (TextView) findViewById(R.id.textView35);
        if (op.equals("Código"))
        {
            t32.setVisibility(View.VISIBLE);
            t33.setVisibility(View.VISIBLE);
            t34.setVisibility(View.VISIBLE);
            t35.setVisibility(View.VISIBLE);
            int cod = Integer.parseInt(t.getText().toString());
            List<String> todosArtigos = db.procurarCod(cod);
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todosArtigos);
            l.setAdapter(adapter);
        }
        else
        {
            if (op.equals("Nome"))
            {
                t32.setVisibility(View.VISIBLE);
                t33.setVisibility(View.VISIBLE);
                t34.setVisibility(View.VISIBLE);
                t35.setVisibility(View.VISIBLE);
                Editable nome = t.getText();
                String n = nome.toString();
                List<String> todosArtigos = db.procurarNome(n);
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todosArtigos);
                l.setAdapter(adapter);
                Log.d("NOME", n);
            }
            else
            {
                t32.setVisibility(View.VISIBLE);
                t33.setVisibility(View.VISIBLE);
                t34.setVisibility(View.VISIBLE);
                t35.setVisibility(View.VISIBLE);
                int cod = Integer.parseInt(t.getText().toString());
                List<String> todosArtigos = db.procurarArma(cod);
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todosArtigos);
                l.setAdapter(adapter);
            }
        }
    }
}