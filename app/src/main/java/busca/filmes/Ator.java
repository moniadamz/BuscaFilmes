package busca.filmes;

import androidx.annotation.NonNull;

public class Ator {
    private int id;
    private String nome;
    private int filme;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getFilme() { return filme; }

    public void setFilme(int filme) { this.filme = filme; }

    @NonNull
    @Override
    public String toString() { return nome; }
}
