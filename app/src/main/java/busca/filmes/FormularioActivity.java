package busca.filmes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FormularioActivity extends AppCompatActivity {

    private Button activityFormAtoresBtSalvar;
    private TextView eTNomeFilme;
    private EditText nomeAtor;
    private int idfilme;
    private String nomeFilme;

    private AtorDAO daoAtor = new AtorDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_atores);
        setTitle(getResources().getString(R.string.titleAppBarPlayer));

        eTNomeFilme = findViewById(R.id.atoresFilme);
        nomeAtor = findViewById(R.id.eTNomeAtor);

        idfilme = getIntent().getExtras().getInt("filme");
        nomeFilme = getIntent().getExtras().getString("nomeFilme");
        eTNomeFilme.setText(nomeFilme);

        activityFormAtoresBtSalvar = findViewById(R.id.activityFormAtoresBtSalvar);

        activityFormAtoresBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarLista();
    }

    private void salvar() {
        String nome = nomeAtor.getText().toString();

        if (nome.isEmpty()) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon(android.R.drawable.ic_dialog_alert);
            alerta.setTitle("Atenção!");
            alerta.setMessage("Você deve inserir o nome do ator.");
            alerta.setPositiveButton("OK", null);
            alerta.show();
        } else {
            Ator atorCriado = new Ator();
            atorCriado.setNome(nome);
            atorCriado.setFilme(idfilme);

            daoAtor.inserir(this, atorCriado);
            finish();
        }
    }

    private void carregarLista() {

        ListView listaAtores = findViewById(R.id.form_Activity_Lv_Atores);
        ArrayAdapter<Ator> adapter = new ArrayAdapter<Ator>(this, android.R.layout.simple_list_item_1, daoAtor.getAtoresById(this, idfilme));
        Log.i("Carregarlista", "carregarLista: "+ listaAtores);

        listaAtores.setAdapter(adapter);
        listaAtores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                excluir((Ator) adapterView.getItemAtPosition(posicao));
                return true;
            }
        });
    }
    private void excluir(final Ator ator){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(getResources().getString(R.string.alertDelete));
        alerta.setMessage(getResources().getString(R.string.alertMessage) + ator.getNome() + "?");
        alerta.setNeutralButton(getResources().getString(R.string.alertNeutralButton), null);
        alerta.setPositiveButton(getResources().getString(R.string.positiveButton), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AtorDAO.excluir(FormularioActivity.this, ator.getId());
                carregarLista();
            }
        });
        alerta.show();

    }

}