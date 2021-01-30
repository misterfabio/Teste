package com.fabiocarvalho.teste;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    Button btnCstrMkm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Button btnIncrTst = findViewById(R.id.btnIncrTst);
        btnIncrTst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this, IncluirActivity.class);
                startActivity(intent);
            }
        });

        btnCstrMkm = findViewById(R.id.btnCstrMkm);

        btnCstrMkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnCstrMkm.getText().toString().equals("CLICOU")){
                    btnCstrMkm.setText("CONSULTAR MKM");
                }else{
                    btnCstrMkm.setText("CLICOU");
                }
//                Intent intent = new Intent(PrincipalActivity.this, CstrMkmActivity.class);
                Intent intent = new Intent(PrincipalActivity.this, CstrMkMAct2.class);
                startActivity(intent);
            }
        });

    }

//--------------------------------------------------------------------------------------------------
//                                      [ FIM ]
//--------------------------------------------------------------------------------------------------
}