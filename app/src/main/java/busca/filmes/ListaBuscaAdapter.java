//package busca.filmes;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ListaBuscaAdapter extends BaseAdapter {
//
//
//    private final List<Filme> filmes = new ArrayList<>();
//    private final Context context;
//
//    public ListaBuscaAdapter(Context context) {
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//        return filmes.size();
//    }
//
//    @Override
//    public Filme getItem(int posicao) {
//        return filmes.get(posicao);
//    }
//
//    @Override
//    public long getItemId(int posicao) {
//        return filmes.get(posicao).getId();
//    }
//
//    @Override
//    public View getView(int posicao, View view, ViewGroup viewGroup) {
//        View viewCriada = criaView(viewGroup);
//        Filme filmeDevolvido = filmes.get(posicao);
//        vincula(viewCriada, filmeDevolvido);
//        return viewCriada;
//    }
//
//    private void vincula(View view, Filme filme) {
//        TextView nome = view.findViewById(R.id.tv_movieTitle);
//        nome.setText(filme.getNome());
//        TextView ator = view.findViewById(R.id.tv_descMovie);
//        ator.setText(ator.getText());
//    }
//
//    private View criaView(ViewGroup viewGroup) {
//        return LayoutInflater
//                .from(context)
//                .inflate(R.layout.modelo_filmes, viewGroup, false);
//    }
//
//    public void atualiza(List filmes){
//        this.filmes.clear();
//        this.filmes.addAll(filmes);
//        notifyDataSetChanged();
//    }
//
//    public void remove(Filme filme) {
//        filmes.remove(filme);
//        notifyDataSetChanged();
//    }
//}
