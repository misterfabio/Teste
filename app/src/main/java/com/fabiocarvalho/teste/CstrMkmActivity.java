package com.fabiocarvalho.teste;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.fabiocarvalho.teste.DataBase.DBAdapter;
import com.fabiocarvalho.teste.DataBase.MkmDBHelper;
import com.fabiocarvalho.teste.Dominio.Entidades.Prd;
import com.fabiocarvalho.teste.Dominio.Repositorio.PrdRepositorio;
import java.util.List;

public class CstrMkmActivity extends PrincipalActivity {

    private SQLiteDatabase conexao;
    private MkmDBHelper mkmDBHelper;
    private PrdRepositorio prdRepositorio;
    private TextView rstdMkm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cstr_mkm);
        rstdMkm = findViewById(R.id.tv_mkm_result_qry);
        try {
            criarConexao();
//            cstrRegMkm();
            Log.e("### CstrMkmActivity::: ", "Vai chamar MkmDBHelper");
            consultarRegistro();
        }catch (Exception e){
            Toast.makeText(this, "MKmDB FALHOU!", Toast.LENGTH_SHORT).show();
        }


    }

    private void cstrRegMkm(){
        DBAdapter mDbHelper = new DBAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        try{
        Cursor testdata = mDbHelper.getTestData();
        int i = testdata.getColumnCount();
        Toast.makeText(this,"Colunas:::: " + i, Toast.LENGTH_LONG).show();}
        catch (Exception e){
            rstdMkm.setText(e.getMessage());
        }
        mDbHelper.close();
    }

    private void consultarRegistro(){
        List<Prd> prds;
        try{
            prds = prdRepositorio.listarProdutos();
            if (prds.size() >= 1){
                int tam = prds.size();
                StringBuilder txAux = new StringBuilder();
                txAux.append(tam).append(" | ");
                txAux.append(prds.get(tam-1).CD_PRD).append(" | ");
                txAux.append(prds.get(tam-1).MRC_PRD).append(" | ");
                txAux.append(prds.get(tam-1).DCR_PPL_PRD).append(" | ");
                txAux.append(prds.get(tam-1).DCR_SEC_PRD).append(" | ");
                txAux.append(prds.get(tam-1).EPFC_PRD).append(" | ");
                txAux.append(prds.get(tam-1).PREC_VND_PRD).append(" | ");
                rstdMkm.setText(txAux);
            }else{
                Toast.makeText(this,"Entrou no 'else'", Toast.LENGTH_LONG).show();
                rstdMkm.setText("Produtos = vazio");
            }
        }
        catch (Exception e){
            StringBuilder str = new StringBuilder();
            str.append("Erro: ");
            str.append(e.getMessage());
            rstdMkm.setText(str);
        }
    }

// [ CRIAR CONEXAO ]
    private void criarConexao(){
        try{
            mkmDBHelper = new MkmDBHelper(this);
            conexao = mkmDBHelper.getWritableDatabase();
            String txAux = "Conexão: " + conexao.getPath().toString();
            Toast.makeText(this,txAux, Toast.LENGTH_SHORT).show();
            prdRepositorio = new PrdRepositorio(conexao);
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
