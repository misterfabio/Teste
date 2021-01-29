package com.fabiocarvalho.teste.DataBase;

public class ScriptsDDL {
    public static String getCreateTableCatalogo(){
        String sql = "CREATE TABLE IF NOT EXISTS CAT_PRD ( " +
                "       _ID         INTEGER PRIMARY KEY AUTOINCREMENT " +
                "     , CODIGO      TEXT NOT NULL" +
                "     , DESCRICAO   TEXT" +
                "     , PRECO_VENDA REAL)";
        return sql;
    }
}
