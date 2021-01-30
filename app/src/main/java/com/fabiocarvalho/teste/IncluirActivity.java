package com.fabiocarvalho.teste;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.fabiocarvalho.teste.DataBase.TesteOpenHelper;
import com.fabiocarvalho.teste.Dominio.Entidades.Produto;
import com.fabiocarvalho.teste.Dominio.Repositorio.ProdutoRepositorio;

import java.util.List;

public class IncluirActivity extends PrincipalActivity{

    private SQLiteDatabase conexao;
    private TesteOpenHelper testeOpenHelper;
    private ProdutoRepositorio produtoRepositorio;

    private EditText edtTxCdPrd;
    private EditText edtTxDcr;
    private EditText edtTxPrecVnd;
    private TextView rstdQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_incluir);

        criarConexao();

        final Button btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validarEntradas()){
                    Toast.makeText(IncluirActivity.this,"Entrada Inválida", Toast.LENGTH_LONG).show();
                }else{
                    if  (incluirRegistro()){
                        Toast.makeText(IncluirActivity.this,"Registro incluído com sucesso!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

// [ VALIDAR ENTRADA ]
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

    private Boolean incluirRegistro(){
        edtTxCdPrd =findViewById(R.id.edtTxCdPrd);
        edtTxDcr = findViewById(R.id.edtTxDcr);
        edtTxPrecVnd = findViewById(R.id.edtTxPrecVnd);
        Produto produto = new Produto();
        produto.codigo = edtTxCdPrd.getText().toString();
        produto.descricao = edtTxDcr.getText().toString();
        produto.preco_venda = Double.parseDouble(edtTxPrecVnd.getText().toString());
        produtoRepositorio.inserirProduto(produto);
        List<Produto> produtos;
        rstdQuery = findViewById(R.id.tv_Rstd);
        try{
            produtos = produtoRepositorio.listarProdutos();
            if (produtos.size() >= 1){
                int tam = produtos.size();
                StringBuilder txAux = new StringBuilder();
                txAux.append(tam).append(" | ");
                txAux.append(produtos.get(tam-1).descricao).append(" | ");
                txAux.append(produtos.get(tam-1)._id).append(" | ");
                rstdQuery.setText(txAux);
            }else{
                Toast.makeText(this,"Entrou no 'else'", Toast.LENGTH_LONG).show();
                rstdQuery.setText("Produtos = vazio");
            }
            return true;
        }
        catch (Exception e){
            StringBuilder str = new StringBuilder();
            str.append("Erro: ");
            str.append(e.getMessage());
            rstdQuery.setText(str);
            return false;
        }
    }

// [ CRIAR CONEXAO ]
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

//================================== [ FIM ] =======================================================
}
