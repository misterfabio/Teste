package com.fabiocarvalho.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fabiocarvalho.teste.DataBase.AssetDatabaseOpenHelper;

public class CstrMkMAct2 extends AppCompatActivity {

    EditText etCdPrd;
    TextView cdPrd, mrcPrd, dcrPplPrd, dcrSecPrd, epcfPrd, precVndPrd;
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cstr_mk_m_act2);

        etCdPrd = findViewById(R.id.et_cd_prd);
        cdPrd = findViewById(R.id.tv_cd_prd);
        mrcPrd = findViewById(R.id.tv_mrc_prd);
        dcrPplPrd = findViewById(R.id.tv_dcr_ppl_prd);
        dcrSecPrd = findViewById(R.id.tv_dcr_sec_prd);
        epcfPrd = findViewById(R.id.tv_epfc_prd);
        precVndPrd = findViewById(R.id.tv_prec_vnd_prd);
        btnOk = findViewById(R.id.btnOk);


        AssetDatabaseOpenHelper adb = new AssetDatabaseOpenHelper(this);
        SQLiteDatabase db = adb.openDatabase();

        Cursor c = db.rawQuery("select * from MKM_CTL_PRD where CD_PRD=?", new String[]{"7898506258352"});
        Log.d("### TesteMkM", "cnt: "+c.getCount());

        if (c.moveToFirst()){
            String dica = c.getString(c.getColumnIndex("CD_PRD"));
            cdPrd.setText(dica);
            mrcPrd.setText(String.valueOf(c.getCount()));
        }

        c.close();

    }
}