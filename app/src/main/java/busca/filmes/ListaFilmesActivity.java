package busca.filmes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

//import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListaFilmesActivity extends AppCompatActivity {

    private FilmesDAO dao = new FilmesDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);

        setTitle(getResources().getString(R.string.titleAppBarList));

        configuraFabNovoFilme();
    }

    private void configuraFabNovoFilme() {
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormFilmeActivity();
                finish();
            }
        });
    }

    private void abreFormFilmeActivity() {
        startActivity( new Intent(this, FormFilmesActivity.class) );
    }

    @Override
    protected void onResume() {
        super.onResume();

        configuraLista();
    }

    //@NotNull
    private void configuraLista() {
        ListView listaFilmes = findViewById(R.id.ActivityListaFilmes);
        final List<Filme> filmes = dao.getFilmes(this);
        listaFilmes.setAdapter( new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filmes));

        listaFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {

                Filme filmeClicado = (Filme) adapterView.getItemAtPosition(posicao);
                Intent formAtores = new Intent(ListaFilmesActivity.this, FormularioActivity.class);
                formAtores.putExtra("filme", filmeClicado.getId());
                formAtores.putExtra("nomeFilme", filmeClicado.getNome());
                startActivity(formAtores);

                Log.i("lista", "onItemClick:"+ filmeClicado);
            }
        });

        listaFilmes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                excluir((Filme) adapterView.getItemAtPosition(posicao));
                return true;
            }
        });
    }

    private void excluir(final Filme filme){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Time");
        alerta.setMessage("Confirma a exclusão do Time "
                + filme.getNome() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FilmesDAO.excluir(ListaFilmesActivity.this, filme.getId());
                carregarLista();
            }
        });
        alerta.show();

    }
    @Override
    protected void onStart() {
        super.onStart();
        carregarLista();
    }

    private void carregarLista(){
        List<Filme> lista = FilmesDAO.getFilmes(this);

        ListView listaFilmes = findViewById(R.id.ActivityListaFilmes);
        ArrayAdapter<Filme> adapter = new ArrayAdapter<Filme>(this, android.R.layout.simple_list_item_1, dao.getFilmes(this));

        listaFilmes.setAdapter(adapter);
        Log.i("Carregar lista", "carregarLista: " + lista);

    }
}
