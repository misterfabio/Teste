package com.fabiocarvalho.teste.Dominio.Repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.fabiocarvalho.teste.Dominio.Entidades.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntToLongFunction;

public class ProdutoRepositorio {

    private SQLiteDatabase conexao;

    public ProdutoRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserirProduto (Produto produto){
        ContentValues contentValues = new ContentValues();
        contentValues.put("CODIGO", produto.codigo);
        contentValues.put("DESCRICAO", produto.descricao);
        contentValues.put("PRECO_VENDA", produto.preco_venda);
        conexao.insertOrThrow("CAT_PRD", null, contentValues);
    }

    public void excluirProduto (Long _id){
        String[] parm = new String[1];
        parm[0] = String.valueOf(_id);
        conexao.delete("CAT_PRD", "_ID = ? ", parm);
    }

    public void alterarProduto (Produto produto){
        ContentValues contentValues = new ContentValues();
        contentValues.put("_ID", produto._id);
        contentValues.put("CODIGO", produto.codigo);
        contentValues.put("DESCRICAO", produto.descricao);
        contentValues.put("PRECO_VENDA", produto.preco_venda);
        String[] parm = new String[1];
        parm[0] = String.valueOf(produto._id);
        conexao.update("CAT_PRD", contentValues, "_ID = ? ", parm);
    }

    public List<Produto> listarProdutos(){
        List<Produto> produtos = new ArrayList<Produto>();
        StringBuilder sql = new StringBuilder();
            sql.append("SELECT * ");
            sql.append("  FROM CAT_PRD");
        Produto produto = new Produto();
        try {
            Cursor cursor = conexao.rawQuery(sql.toString(), null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do{
                    produto._id = cursor.getLong(cursor.getColumnIndexOrThrow("_ID"));
                    produto.codigo = cursor.getString(cursor.getColumnIndexOrThrow("CODIGO"));
                    produto.descricao = cursor.getString(cursor.getColumnIndexOrThrow("DESCRICAO"));
                    produto.preco_venda = cursor.getDouble(cursor.getColumnIndexOrThrow("PRECO_VENDA"));
                    produtos.add(produto);
                }while(cursor.moveToNext());
            }
            cursor.close();
        }catch(SQLException e){
            produto._id = 0L;
            produto.codigo = "";
            produto.descricao = "ERRO: " + e.getMessage();
            produto.preco_venda = 0.00;
            produtos.add(produto);
        }
        return produtos;
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
