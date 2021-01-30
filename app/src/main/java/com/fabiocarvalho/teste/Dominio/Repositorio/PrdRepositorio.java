package com.fabiocarvalho.teste.Dominio.Repositorio;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.fabiocarvalho.teste.Dominio.Entidades.Prd;
import com.fabiocarvalho.teste.Dominio.Entidades.Produto;

import java.util.ArrayList;
import java.util.List;

public class PrdRepositorio {

    private SQLiteDatabase conexao;

    public PrdRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public List<Prd> listarProdutos(){
        List<Prd> prds = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
            sql.append("SELECT * ");
            sql.append("  FROM MKM_CTL_PRD");
        Prd prd = new Prd();
        try {
            Cursor cursor = conexao.rawQuery(sql.toString(), null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do{
                    prd.CD_PRD = cursor.getString(cursor.getColumnIndexOrThrow("CD_PRD"));
                    prd.MRC_PRD = cursor.getString(cursor.getColumnIndexOrThrow("MRC_PRD"));
                    prd.DCR_PPL_PRD = cursor.getString(cursor.getColumnIndexOrThrow("DCR_PPL_PRD"));
                    prd.DCR_SEC_PRD = cursor.getString(cursor.getColumnIndexOrThrow("DCR_SEC_PRD"));
                    prd.EPFC_PRD = cursor.getString(cursor.getColumnIndexOrThrow("EPFC_PRD"));
                    prd.PREC_VND_PRD = cursor.getDouble(cursor.getColumnIndexOrThrow("PREC_VND_PRD"));
                    prds.add(prd);
                }while(cursor.moveToNext());
            }
            cursor.close();
        }catch(SQLException e){
            prd.CD_PRD = "ERRO";
            prd.DCR_PPL_PRD = e.getMessage();
            prd.PREC_VND_PRD = 0.00;
            prds.add(prd);
        }
        return prds;
    }

    public Produto consultarProduto(Long _id){
        Produto produto = new Produto();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("  FROM CAT_PRD");
        sql.append(" WHERE _ID = ?");
        String[] parm = new String[1];
        parm[0] = String.valueOf(produto._id);
        Cursor cursor = conexao.rawQuery(sql.toString(), null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            produto._id = cursor.getLong(cursor.getColumnIndexOrThrow("_ID"));
            produto.codigo = cursor.getString(cursor.getColumnIndexOrThrow("CODIGO"));
            produto.descricao = cursor.getString(cursor.getColumnIndexOrThrow("DESCRICAO"));
            produto.preco_venda = cursor.getDouble(cursor.getColumnIndexOrThrow("PRECO_VENDA"));
            return produto;
        }
        return null;
    }

}
