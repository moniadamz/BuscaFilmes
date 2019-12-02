package busca.filmes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;


public class Pesquisa extends AppCompatActivity {
    private Button pesquisar;
    private EditText digitaFilme;
//    private final ListaFilmesView listaFilmesView = new ListaFilmesView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);
        pesquisar = findViewById(R.id.pesquisaFilme);
        digitaFilme = findViewById(R.id.digitaFilme);

        setTitle(getResources().getString(R.string.search));

        pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesquisar();
                //configuraLista();
                //pesquisarTudao();
            }
        });
    }
    private void pesquisar(){
        String nome = digitaFilme.getText().toString();
        List<Filme> filme = FilmesDAO.getTudoByName(this, nome);
        ListView listaFilmes = (ListView) findViewById(R.id.resultadoPesquisa);
        if ( filme.size() == 0 ){
            listaFilmes.setEnabled( false );
            Filme fake = new Filme();
            fake.setNome("Lista Vazia!");
            filme.add( fake );
        }else {
            listaFilmes.setEnabled( true );
        }

        ArrayAdapter<Filme> adapter = new ArrayAdapter<Filme>(this, android.R.layout.simple_list_item_1, filme);
        listaFilmes.setAdapter(adapter);
    }

//    private void configuraLista() {
//        ListView listaDeFilmes = findViewById(R.id.resultadoPesquisa);
////        listaFilmesView.configuraAdapter(listaDeFilmes);
////        configuraListenerDeCliquePorItem(listaDeFilmes);
//        registerForContextMenu(listaDeFilmes);
//    }

    private void pesquisarTudao(){
        String nome = digitaFilme.getText().toString();
        List tudao = FilmesDAO.getFilmeByName(this, nome);
        ListView listaTudao = (ListView) findViewById(R.id.resultadoPesquisa);
        if ( tudao.size() == 0 ){
            listaTudao.setEnabled( false );
            Filme fake = new Filme();
            fake.setNome("TUDAO VAZIO!");
            tudao.add( fake );
        }else {
            listaTudao.setEnabled( true );
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tudao);
        listaTudao.setAdapter(adapter);
    }
}
