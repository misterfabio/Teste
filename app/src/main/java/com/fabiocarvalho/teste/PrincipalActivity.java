package com.fabiocarvalho.teste;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Button btnIncluir = findViewById(R.id.btnIncr);
        btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this, IncluirActivity.class);
                startActivity(intent);
            }
        });
    }

//--------------------------------------------------------------------------------------------------
//                                      [ FIM ]
//--------------------------------------------------------------------------------------------------
}