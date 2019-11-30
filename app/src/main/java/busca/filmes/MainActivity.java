package busca.filmes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button registraFilme;
    private Button pesquisaFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registraFilme = findViewById(R.id.registraFilme);
        pesquisaFilme = findViewById(R.id.pesquisaFilme);


        registraFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FormTimesActivity.class);
                startActivity( i );
            }
        });

        pesquisaFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Pesquisa.class);
                startActivity( i );
            }
        });

        pesquisaFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Pesquisa.class);
                startActivity( i );
            }
        });

    }


}