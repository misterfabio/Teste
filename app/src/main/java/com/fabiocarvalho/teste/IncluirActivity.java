package com.fabiocarvalho.teste;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.fabiocarvalho.teste.DataBase.TesteOpenHelper;
import com.fabiocarvalho.teste.Dominio.Repositorio.ProdutoRepositorio;

public class IncluirActivity extends PrincipalActivity{

    private SQLiteDatabase conexao;
    private TesteOpenHelper testeOpenHelper;
    private ProdutoRepositorio produtoRepositorio;

    private EditText edtTxCdPrd;
    private EditText edtTxDcr;
    private EditText edtTxPrecVnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_incluir);

        Toast.makeText(this,"Incluir registro.", Toast.LENGTH_LONG).show();
        criarConexao();

        final Button btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validarEntradas()){
                    Toast.makeText(IncluirActivity.this,"Entrada Inválida", Toast.LENGTH_LONG).show();
                }
            }
        });

//        Produto produto = new Produto();
//        produto._id = 0L;
//        produto.codigo = "1";
//        produto.descricao = "Segundo";
//        produto.preco_venda = 1.11;
//        produtoRepositorio.inserirProduto(produto);
//        List<Produto> produtos;
//        try{
//            Toast.makeText(this,"Entrou no 'try'", Toast.LENGTH_LONG).show();
//            produtos = produtoRepositorio.listarProdutos();
//            if (produtos.size() > 1){
//                int tam = produtos.size();
//                textView.setText("Tamanho: " + tam);
//                for (int i=1; i<=tam; i++){
//                    StringBuilder str = new StringBuilder();
//                    str.append(produtos.get(i-1).descricao);
//                    str.append(": ");
//                    str.append(i);
//                    str.append(" de ");
//                    str.append(tam);
//                    str.append("|");
//                    str.append(produtos.get(i-1)._id);
//                    textView.setText(str);
//                }
//            }else if(produtos.size()>0){
//                Toast.makeText(this,"Entrou no 'if > 0'", Toast.LENGTH_LONG).show();
//                textView.setText(produtos.get(0).descricao);
//            }else{
//                Toast.makeText(this,"Entrou no 'else'", Toast.LENGTH_LONG).show();
//                textView.setText("Produtos = vazio");
//            }
//        }
//        catch (Exception e){
//            StringBuilder str = new StringBuilder();
//            str.append("Erro: ");
//            str.append(e.getMessage());
//            textView.setText(str);
//        }




    }

    //---------------------------------- [ CRIAR CONEXAO ] ---------------------------------------------
    private Boolean validarEntradas(){
        edtTxCdPrd = findViewById(R.id.edtTxCdPrd);
        edtTxDcr = findViewById(R.id.edtTxDcr);
        edtTxPrecVnd = findViewById(R.id.edtTxPrecVnd);
        boolean vldc = true;
        if (edtTxCdPrd.getText().toString().equals("")){
            vldc = false;
        }
        if (edtTxDcr.getText().toString().equals("")){
            vldc = false;
        }
        if (edtTxPrecVnd.getText().toString().equals("")){
            vldc = false;
        }
        return vldc;
    }
//---------------------------------- [ CRIAR CONEXAO - FIM ] ---------------------------------------

//---------------------------------- [ CRIAR CONEXAO ] ---------------------------------------------
    private void criarConexao(){
        try{
            testeOpenHelper = new TesteOpenHelper(this);
            conexao = testeOpenHelper.getWritableDatabase();
            Toast.makeText(this,"Conexao executada com sucesso", Toast.LENGTH_SHORT).show();
            produtoRepositorio = new ProdutoRepositorio(conexao);
        }catch(SQLException e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(e.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
            Toast.makeText(this,"ERRO na conexao", Toast.LENGTH_LONG).show();
        }
    }
//---------------------------------- [ CRIAR CONEXAO - FIM ] ---------------------------------------

}
