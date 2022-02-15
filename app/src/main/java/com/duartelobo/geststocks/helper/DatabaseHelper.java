package com.duartelobo.geststocks.helper;

import com.duartelobo.geststocks.model.Armazem;
import com.duartelobo.geststocks.model.Compra;
import com.duartelobo.geststocks.model.Fornecedor;
import com.duartelobo.geststocks.model.Produto;
import com.duartelobo.geststocks.model.Utilizador;
import com.duartelobo.geststocks.model.Venda;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

    // Nome da base de dados
	private static final String DATABASE_NAME = "GestStocks";

    // Versão�o da base de dados
	private static final int DATABASE_VERSION = 1;
    
    // Nomes das tabelas
	private static final String TABLE_ARMAZEM = "armazem";
    private static final String TABLE_FORNECEDOR = "fornecedor";
    private static final String TABLE_COMPRA = "compra";
	private static final String TABLE_PRODUTO = "produto";
	private static final String TABLE_UTILIZADOR = "utilizador";
	private static final String TABLE_VENDA = "venda";
 
    // Nomes das colunas das tabelas
	//Tabela Utilizador
	private static final String KEY_CODUTILIZADOR = "CodUtilizador"; //Utilizado tmb nas tabelas: Compra, Venda
	private static final String KEY_NOMEUTILIZADOR = "NomeUtilizador";
	private static final String KEY_PASSWORD = "Password";
	private static final String KEY_ADMIN = "Admin";

	//Tabela Armazem
    private static final String KEY_CODARMAZEM = "CodArmazem"; //Utilizado tmb na tabela Produto
    private static final String KEY_NOMEARMAZEM = "NomeArmazem";
    private static final String KEY_LOCALIZACAO = "Localizacao";

	//Tabela Fornecedor
	private static final String KEY_CODFORN = "CodForn"; //Utilizado tmb nas tabelas: Compra
	private static final String KEY_NOMEFORN = "NomeForn";
	private static final String KEY_MORADA = "Morada";
	private static final String KEY_CONTACTO = "Contacto";

	//Tabela Produto
	private static final String KEY_CODPROD = "CodProd"; //Utilizado tmb nas tabelas: Compra,Venda
	private static final String KEY_NOMEPROD = "NomeProd";
	private static final String KEY_DESCRICAO = "Descricao";
	private static final String KEY_QTD = "Qtd";
	private static final String KEY_PRECO = "Preco";
	private static final String KEY_U_MEDIDA = "U_medida";

	//Tabela Compra
	private static final String KEY_CODCOMPRA = "CodCompra";
	private static final String KEY_QTD_COMPRA = "Qtd";
	private static final String KEY_DATA_COMPRA = "Data";

	//Tabela Venda
	private static final String KEY_CODVENDA = "CodVenda";
	private static final String KEY_QTD_VENDA = "Qtd";
	private static final String KEY_DATA_VENDA = "Data";

    //criar tabela Utilizador
    private static final String CREATE_TABLE_UILIZADOR = "CREATE TABLE " + TABLE_UTILIZADOR + "(" + KEY_CODUTILIZADOR + " INTEGER PRIMARY KEY," + KEY_NOMEUTILIZADOR + " TEXT," + KEY_PASSWORD + " TEXT," + KEY_ADMIN + " TEXT" + ")";

    //criar tabela Armazem
    private static final String CREATE_TABLE_ARMAZEM = "CREATE TABLE " + TABLE_ARMAZEM + "(" + KEY_CODARMAZEM + " INTEGER PRIMARY KEY," + KEY_NOMEARMAZEM + " TEXT," + KEY_LOCALIZACAO + " TEXT" + ")";
    
    //criar tabela Fornecedor
	private static final String CREATE_TABLE_FORNECEDOR = "CREATE TABLE " + TABLE_FORNECEDOR + "(" + KEY_CODFORN + " INTEGER PRIMARY KEY," + KEY_NOMEFORN + " TEXT," + KEY_MORADA + " TEXT," + KEY_CONTACTO + " INTEGER" + ")";

	//criar tabela Produto
	private static final String CREATE_TABLE_PRODUTO = "CREATE TABLE " + TABLE_PRODUTO + "(" + KEY_CODPROD + " INTEGER PRIMARY KEY," + KEY_NOMEPROD + " TEXT," + KEY_DESCRICAO + " TEXT," + KEY_QTD + " REAL," + KEY_PRECO + " REAL," + KEY_U_MEDIDA + " TEXT," + KEY_CODARMAZEM + " INTEGER, " + KEY_CODFORN + " INTEGER" + ")";

	//criar tabela Compra
	private static final String CREATE_TABLE_COMPRA = "CREATE TABLE " + TABLE_COMPRA + "(" + KEY_CODCOMPRA + " INTEGER PRIMARY KEY," + KEY_CODPROD + " INTEGER," + KEY_CODFORN + " INTEGER," + KEY_QTD_COMPRA + " REAL," + KEY_DATA_COMPRA + " TEXT" + ")";

	//criar tabela Venda
	private static final String CREATE_TABLE_VENDA = "CREATE TABLE " + TABLE_VENDA + "(" + KEY_CODVENDA + " INTEGER PRIMARY KEY," + KEY_CODPROD + " INTEGER," + KEY_QTD_VENDA + " INTEGER," + KEY_DATA_VENDA + " TEXT" + ")";

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public DatabaseHelper(Context applicationContext) {
		super(applicationContext, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//criar as tabelas
        db.execSQL(CREATE_TABLE_UILIZADOR);
        db.execSQL(CREATE_TABLE_ARMAZEM);
        db.execSQL(CREATE_TABLE_FORNECEDOR);
		db.execSQL(CREATE_TABLE_PRODUTO);
		db.execSQL(CREATE_TABLE_COMPRA);
		db.execSQL(CREATE_TABLE_VENDA);
		db.execSQL("INSERT INTO utilizador VALUES (1, 'admin', 'admin', 'SIM')");
		db.execSQL("INSERT INTO fornecedor VALUES (1, 'Geral', 'Global', '999999999')");
		db.execSQL("INSERT INTO armazem VALUES (1, 'Sede', 'Sede')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// remover as tabelas existentes
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UTILIZADOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARMAZEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORNECEDOR);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPRA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENDA);
 
        //criar as novas tabelas
        onCreate(db);		
	}
	/*************************************Compras***************************************/

	//Registar Compra
	public Compra registarCompra(int CodCompra, int CodProd, int CodForn, float Qtd, String Data) {

		Compra compra = new Compra(CodCompra, CodProd, CodForn, Qtd, Data);
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_CODCOMPRA, compra.getCodCompra());
		values.put(KEY_CODPROD, compra.getCodProd());
		values.put(KEY_CODFORN, compra.getCodForn());
		values.put(KEY_QTD, compra.getQtd());
		values.put(KEY_DATA_COMPRA, compra.getData());

		// inserir compra
		int compra_id = (int) db.insert(TABLE_COMPRA, null, values);
		compra.setCodProd(compra_id);

		return compra;
	}

	//Retorna Codigo Compra
	public List<Compra> listarCodCompra() {
		String query;
		List<Compra> cod = new ArrayList<Compra>();
		query = "SELECT " + KEY_CODCOMPRA + " FROM " + TABLE_COMPRA + " ORDER BY " + KEY_CODCOMPRA+ " DESC LIMIT 1";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);


		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Compra compra = new Compra();
				compra.setCodCompra(c.getInt((c.getColumnIndex(KEY_CODCOMPRA))));
				cod.add(compra);
			} while (c.moveToNext());
		}
		return cod;
	}

	//listar produtos
	public List<String> listarCompras() {
		String query;
		List<String> compras = new ArrayList<String>();
		query = "SELECT  * FROM " + TABLE_COMPRA;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Compra compra = new Compra();
				String cod_compra = c.getString((c.getColumnIndex(KEY_CODCOMPRA)));
				String cod_prod = c.getString(c.getColumnIndex(KEY_CODPROD));
				String cod_forn = c.getString(c.getColumnIndex(KEY_CODFORN));
				String qtd = c.getString(c.getColumnIndex(KEY_QTD));
				String data = c.getString(c.getColumnIndex(KEY_DATA_COMPRA));

				// adicionar � lista
				compras.add(cod_compra);
				compras.add(cod_prod);
				compras.add(cod_forn);
				compras.add(qtd);
				compras.add(data);
			} while (c.moveToNext());
		}
		return compras;
	}
	/*************************************Vendas***************************************/

	//Registar Venda
	public Venda registarVenda(int CodVenda, int CodProd, float Qtd, String Data) {

		Venda venda = new Venda(CodVenda, CodProd, Qtd, Data);
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_CODVENDA, venda.getCodVenda());
		values.put(KEY_CODPROD, venda.getCodProd());
		values.put(KEY_QTD, venda.getQtd());
		values.put(KEY_DATA_COMPRA, venda.getData());

		// inserir compra
		int venda_id = (int) db.insert(TABLE_VENDA, null, values);
		venda.setCodProd(venda_id);

		return venda;
	}

	//Retorna Codigo Venda
	public List<Venda> listarCodVenda() {
		String query;
		List<Venda> cod = new ArrayList<Venda>();
		query = "SELECT " + KEY_CODVENDA + " FROM " + TABLE_VENDA + " ORDER BY " + KEY_CODVENDA + " DESC LIMIT 1";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);


		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Venda venda = new Venda();
				venda.setCodVenda(c.getInt((c.getColumnIndex(KEY_CODVENDA))));
				cod.add(venda);
			} while (c.moveToNext());
		}
		return cod;
	}

	//listar Vendas
	public List<String> listarVendas() {
		String query;
		List<String> vendas = new ArrayList<String>();
		query = "SELECT  * FROM " + TABLE_VENDA;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Venda venda = new Venda();
				String cod_venda = c.getString((c.getColumnIndex(KEY_CODVENDA)));
				String cod_prod = c.getString(c.getColumnIndex(KEY_CODPROD));
				String qtd = c.getString(c.getColumnIndex(KEY_QTD));
				String data = c.getString(c.getColumnIndex(KEY_DATA_COMPRA));

				// adicionar � lista
				vendas.add(cod_venda);
				vendas.add(cod_prod);
				vendas.add(qtd);
				vendas.add(data);
			} while (c.moveToNext());
		}
		return vendas;
	}
	/*************************************Produtos***************************************/

	//Criar Produto
	public Produto criarProduto(int CodProd, String NomeProd, String Descricao, float Qtd, float Preco, String U_medida, int CodArmazem, int CodForn) {
		
		Produto produto = new Produto(CodProd, NomeProd, Descricao, Qtd, Preco, U_medida, CodArmazem, CodForn);
	    SQLiteDatabase db = this.getWritableDatabase();	 
	    ContentValues values = new ContentValues();
	    values.put(KEY_CODPROD, produto.getCodProd());
	    values.put(KEY_NOMEPROD, produto.getNomeProd());
	    values.put(KEY_DESCRICAO, produto.getDescricao());
	    values.put(KEY_QTD, produto.getQtd());
		values.put(KEY_PRECO, produto.getPreco());
		values.put(KEY_U_MEDIDA, produto.getU_medida());
		values.put(KEY_CODARMAZEM, produto.getCodArmazem());
		values.put(KEY_CODFORN, produto.getCodForn());
	    // inserir artigo
	    int produto_id = (int) db.insert(TABLE_PRODUTO, null, values);
	    produto.setCodProd(produto_id);

	    return produto;
	}

	//listar produtos
	public List<String> listarProdutos() {
		String query;
		List<String> produtos = new ArrayList<String>();
		query = "SELECT  * FROM " + TABLE_PRODUTO;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Produto produto = new Produto();
				String cod = c.getString((c.getColumnIndex(KEY_CODPROD)));
				String nome = c.getString(c.getColumnIndex(KEY_NOMEPROD));
				String qtd = c.getString(c.getColumnIndex(KEY_QTD));
				String cod_arm = c.getString(c.getColumnIndex(KEY_CODARMAZEM));

				// adicionar � lista
				produtos.add(cod);
				produtos.add(nome);
				produtos.add(qtd);
				produtos.add(cod_arm);
			} while (c.moveToNext());
		}
		return produtos;
	}


	//Procurar por código
	public List<String> procurarCod(int cod_p) {
		String query;
		List<String> produtos = new ArrayList<String>();
		query = "SELECT  * FROM " + TABLE_PRODUTO  + " WHERE " + KEY_CODPROD + "==" + cod_p;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Produto produto = new Produto();
				String cod = c.getString((c.getColumnIndex(KEY_CODPROD)));
				String nome = c.getString(c.getColumnIndex(KEY_NOMEPROD));
				String qtd = c.getString(c.getColumnIndex(KEY_QTD));
				String cod_arm = c.getString(c.getColumnIndex(KEY_CODARMAZEM));

				// adicionar � lista
				produtos.add(cod);
				produtos.add(nome);
				produtos.add(qtd);
				produtos.add(cod_arm);
			} while (c.moveToNext());
		}
		return produtos;
	}

	//Procurar por nome
	public List<String> procurarNome(String nome_p) {
		String query;
		List<String> produtos = new ArrayList<String>();
		query = "SELECT  * FROM " + TABLE_PRODUTO  + " WHERE " + KEY_NOMEPROD + " LIKE '" + nome_p + "'";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Produto produto = new Produto();
				String cod = c.getString((c.getColumnIndex(KEY_CODPROD)));
				String nome = c.getString(c.getColumnIndex(KEY_NOMEPROD));
				String qtd = c.getString(c.getColumnIndex(KEY_QTD));
				String cod_arm = c.getString(c.getColumnIndex(KEY_CODARMAZEM));

				// adicionar � lista
				produtos.add(cod);
				produtos.add(nome);
				produtos.add(qtd);
				produtos.add(cod_arm);
			} while (c.moveToNext());
		}
		return produtos;
	}

	//Procurar por armazém
	public List<String> procurarArma(int cod_a) {
		String query;
		List<String> produtos = new ArrayList<String>();
		query = "SELECT  * FROM " + TABLE_PRODUTO  + " WHERE " + KEY_CODARMAZEM + "==" + cod_a;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Produto produto = new Produto();
				String cod = c.getString((c.getColumnIndex(KEY_CODPROD)));
				String nome = c.getString(c.getColumnIndex(KEY_NOMEPROD));
				String qtd = c.getString(c.getColumnIndex(KEY_QTD));
				String cod_arm = c.getString(c.getColumnIndex(KEY_CODARMAZEM));

				// adicionar � lista
				produtos.add(cod);
				produtos.add(nome);
				produtos.add(qtd);
				produtos.add(cod_arm);
			} while (c.moveToNext());
		}
		return produtos;
	}

	//Informações Venda
	public List<Produto> info_venda(int cod_p) {
		String query;
		List<Produto> produtos = new ArrayList<Produto>();
		query = "SELECT  * FROM " + TABLE_PRODUTO + " WHERE " + KEY_CODPROD + "==" + cod_p;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Produto produto = new Produto();
				produto.setNomeProd(c.getString((c.getColumnIndex(KEY_NOMEPROD))));
				produto.setQtd(c.getFloat(c.getColumnIndex(KEY_QTD)));
				produto.setPreco(c.getFloat(c.getColumnIndex(KEY_PRECO)));
				produto.setCodArmazem(c.getInt(c.getColumnIndex(KEY_CODARMAZEM)));

				// adicionar � lista
				produtos.add(produto);
			} while (c.moveToNext());

		}
		return produtos;
	}
	//Remover Produtos
	public void removerProduto(int cod) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PRODUTO, KEY_CODPROD + " = ?",
				new String[] { String.valueOf(cod) });
	}
	//Atualizar Qtd
	public void atualizarQtd(int cod, float qtd) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues dados = new ContentValues();
		dados.put(KEY_QTD,qtd);
		db.update(TABLE_PRODUTO,dados, KEY_CODPROD + " = ?",
				new String[] { String.valueOf(cod) });
	}


	/*************************************Fornecedores***************************************/

	//criar fornecedor
	public Fornecedor criarForn(int CodForn, String NomeForn, String Morada, int Contacto) {

		Fornecedor fornecedor = new Fornecedor(CodForn, NomeForn, Morada, Contacto);
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_CODFORN, fornecedor.getCodForn());
		values.put(KEY_NOMEFORN, fornecedor.getNomeForn());
		values.put(KEY_MORADA, fornecedor.getMorada());
		values.put(KEY_CONTACTO, fornecedor.getContacto());

		// inserir artigo
		int fornecedor_id = (int) db.insert(TABLE_FORNECEDOR, null, values);
		fornecedor.setCodForn(fornecedor_id);

		return fornecedor;
	}

	//listar forncedores
	public List<String> listarFornecedores() {
		String query;
		List<String> fornecedores = new ArrayList<String>();
		query = "SELECT  * FROM " + TABLE_FORNECEDOR;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Fornecedor fornecedor = new Fornecedor();
				String codF = c.getString((c.getColumnIndex(KEY_CODFORN)));
				String nomeF = c.getString(c.getColumnIndex(KEY_NOMEFORN));
				String moradaF = c.getString(c.getColumnIndex(KEY_MORADA));
				String contactoF = c.getString(c.getColumnIndex(KEY_CONTACTO));

				// adicionar à lista
				fornecedores.add(codF);
				fornecedores.add(nomeF);
				fornecedores.add(moradaF);
				fornecedores.add(contactoF);
			} while (c.moveToNext());
		}
		return fornecedores;
	}

	//Listar NomeForn
	public List<String> listarCodForne() {
		String query;
		List<String> fornecedores = new ArrayList<String>();
		query = "SELECT  " + KEY_CODFORN + " FROM " + TABLE_FORNECEDOR;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Fornecedor fornecedor = new Fornecedor();
				String codF = c.getString(c.getColumnIndex(KEY_CODFORN));

				// adicionar à lista
				fornecedores.add(codF);
			} while (c.moveToNext());
		}
		return fornecedores;
	}

	//Retorna Codigo Fornecedor
	public List<Fornecedor> listarCodForn() {
		String query;
		List<Fornecedor> cod = new ArrayList<Fornecedor>();
		query = "SELECT " + KEY_CODFORN + " FROM " + TABLE_FORNECEDOR + " ORDER BY " + KEY_CODFORN + " DESC LIMIT 1";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);


		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setCodForn(c.getInt((c.getColumnIndex(KEY_CODFORN))));
				cod.add(fornecedor);
			} while (c.moveToNext());
		}
		return cod;
	}

	/*************************************Armazéns***************************************/

	//criar armazém
	public Armazem criarArmazem(int CodArmazem, String NomeArmazem, String Localizacao) {

		Armazem armazem = new Armazem(CodArmazem, NomeArmazem, Localizacao);
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_CODARMAZEM, armazem.getCodArmazem());
		values.put(KEY_NOMEARMAZEM, armazem.getNomeArmazem());
		values.put(KEY_LOCALIZACAO, armazem.getLocalizacao());

		// inserir artigo
		int armazem_id = (int) db.insert(TABLE_ARMAZEM, null, values);
		armazem.setCodArmazem(armazem_id);

		return armazem;
	}
	//listar armazens
	public List<String> listarArmazens() {
		String query;
		List<String> armazens = new ArrayList<String>();
		query = "SELECT  * FROM " + TABLE_ARMAZEM;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Armazem armazem = new Armazem();
				String codA = c.getString((c.getColumnIndex(KEY_CODARMAZEM)));
				String nomeA = c.getString(c.getColumnIndex(KEY_NOMEARMAZEM));
				String localizacaoA = c.getString(c.getColumnIndex(KEY_LOCALIZACAO));

				// adicionar � lista
				armazens.add(codA);
				armazens.add(nomeA);
				armazens.add(localizacaoA);
			} while (c.moveToNext());
		}
		return armazens;
	}

	//Listar NomeArmazem
	public List<String> listarCodArmazem() {
		String query;
		List<String> codigos = new ArrayList<String>();
		query = "SELECT  " + KEY_CODARMAZEM + " FROM " + TABLE_ARMAZEM;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Armazem armazem = new Armazem();
				String codA = c.getString(c.getColumnIndex(KEY_CODARMAZEM));

				// adicionar à lista
				codigos.add(codA);
			} while (c.moveToNext());
		}
		return codigos;
	}
	//Retorna Codigo Armazém
	public List<Armazem> listarCodArm() {
		String query;
		List<Armazem> cod = new ArrayList<Armazem>();
		query = "SELECT " + KEY_CODARMAZEM + " FROM " + TABLE_ARMAZEM + " ORDER BY " + KEY_CODARMAZEM + " DESC LIMIT 1";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);


		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Armazem armazem = new Armazem();
				armazem.setCodArmazem(c.getInt((c.getColumnIndex(KEY_CODARMAZEM))));
				cod.add(armazem);
			} while (c.moveToNext());
		}
		return cod;
	}
	/*************************************Utilizadores***************************************/

	//Criar Utilizador
	public Utilizador criarUtilizador(int CodUtilizador, String NomeUtilizador, String Password, String Admin) {

		Utilizador utilizador = new Utilizador(CodUtilizador, NomeUtilizador, Password, Admin);
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_CODUTILIZADOR, utilizador.getCodUtilizador());
		values.put(KEY_NOMEUTILIZADOR, utilizador.getNomeUtilizador());
		values.put(KEY_PASSWORD, utilizador.getPassword());
		values.put(KEY_ADMIN, utilizador.getAdmin());

		// inserir artigo
		int utilizador_id = (int) db.insert(TABLE_UTILIZADOR, null, values);
		utilizador.setCodUtilizador(utilizador_id);

		return utilizador;
	}

	//Listar Utilizadores
	public List<Utilizador> listarUser() {
		String query;
		List<Utilizador> user = new ArrayList<Utilizador>();
		query = "SELECT " + KEY_NOMEUTILIZADOR + ", " + KEY_PASSWORD + ", " + KEY_ADMIN + " FROM " + TABLE_UTILIZADOR;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);


		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Utilizador utilizador = new Utilizador();
				utilizador.setNomeUtilizador(c.getString((c.getColumnIndex(KEY_NOMEUTILIZADOR))));
				utilizador.setPassword((c.getString(c.getColumnIndex(KEY_PASSWORD))));
				utilizador.setAdmin((c.getString(c.getColumnIndex(KEY_ADMIN))));

				// adicionar � lista
				user.add(utilizador);
			} while (c.moveToNext());
		}
		return user;
	}
	//Listar Codigo utilizador
	public List<Utilizador> listarCodUtilizador() {
		String query;
		List<Utilizador> cod = new ArrayList<Utilizador>();
		query = "SELECT " + KEY_CODUTILIZADOR + " FROM " + TABLE_UTILIZADOR + " ORDER BY " + KEY_CODUTILIZADOR + " DESC LIMIT 1";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);


		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Utilizador utilizador = new Utilizador();
				utilizador.setCodUtilizador(c.getInt((c.getColumnIndex(KEY_CODUTILIZADOR))));
				cod.add(utilizador);
			} while (c.moveToNext());
		}
		return cod;
	}
	public List<String> listarUtilizadores() {
		String query;
		List<String> users = new ArrayList<String>();
		query = "SELECT  " + KEY_CODUTILIZADOR + ", " + KEY_NOMEUTILIZADOR + ", " + KEY_ADMIN + " FROM " + TABLE_UTILIZADOR;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		// Iterar sobre todos os registos da tabela Artigo e adi��o � lista
		if (c.moveToFirst()) {
			do {
				Utilizador utilizador = new Utilizador();
				String cod = c.getString((c.getColumnIndex(KEY_CODUTILIZADOR)));
				String nome = c.getString(c.getColumnIndex(KEY_NOMEUTILIZADOR));
				String admin = c.getString(c.getColumnIndex(KEY_ADMIN));

				// adicionar � lista
				users.add(cod);
				users.add(nome);
				users.add(admin);
			} while (c.moveToNext());
		}
		return users;
	}


	public void fecharBD() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
	
}
