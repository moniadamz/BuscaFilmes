//package busca.filmes;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.view.MenuItem;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//import busca.filmes.FilmesDAO;
//import busca.filmes.Filme;
//import busca.filmes.ListaBuscaAdapter;
//
//
//public class ListaFilmesView {
//
//    private final ListaBuscaAdapter adapter;
//    private final FilmesDAO dao;
//    private final Context context;
//
//    public ListaFilmesView(Context context) {
//        this.context = context;
//        this.adapter = new ListaBuscaAdapter(this.context);
//        this.dao = new FilmesDAO();
//    }
//
////    public void atualizaAlunos() {
////        adapter.atualiza(dao.getTudoByName(this, ));
////    }
////
////    private void remove(Aluno aluno) {
////        dao.remove(aluno);
////        adapter.remove(aluno);
////    }
//
//    public void configuraAdapter(ListView listaDeFilmes) {
//        listaDeFilmes.setAdapter(adapter);
//    }
//}
