package busca.filmes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FormFilmesActivity extends AppCompatActivity {

    private FilmesDAO dao = new FilmesDAO();
    private EditText etNome;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_filmes);

        etNome = findViewById(R.id.activityFormFilmesNome);
        btnSalvar = findViewById(R.id.activityFormFilmesBtSalvar);

        setTitle(getResources().getString(R.string.titleAppBarAdd));

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    private void salvar() {
        String nome = etNome.getText().toString();

        if (nome.isEmpty()){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon( android.R.drawable.ic_dialog_alert);
            alerta.setTitle("Atenção!");
            alerta.setMessage("Você deve inserir o nome do Filme.");
            alerta.setPositiveButton("OK", null);
            alerta.show();

        }else{
            Filme filmeCriado = new Filme();
            filmeCriado.setNome(nome);
//            Toast.makeText(FormTimesActivity.this, filmeCriado.getNome(),Toast.LENGTH_SHORT).show();

            dao.inserir(this, filmeCriado);

            startActivity( new Intent(FormFilmesActivity.this, ListaFilmesActivity.class) );

            finish();

        }


    }


}
