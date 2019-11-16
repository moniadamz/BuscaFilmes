package busca.filmes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME = "BuscaFilmes";

    public Banco(Context contexto){
        super(contexto, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_FILMES = "CREATE TABLE IF NOT EXISTS filmes (idfilme integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nome text," +
                "ano text )";

        String SQL_ATORES =  "" +
                "CREATE TABLE IF NOT EXISTS atores " +
                "(idator integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nome text )";

        String SQL_FILMES_ATORES = "CREATE TABLE IF NOT EXISTS filmes_atores (id integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "FOREIGN KEY(idfilme) REFERENCES filmes(idfilme)," +
                "FOREIGN KEY(idator) REFERENCES filmes(idator) )";


        sqLiteDatabase.execSQL(SQL_FILMES);
        sqLiteDatabase.execSQL(SQL_ATORES);
        sqLiteDatabase.execSQL(SQL_FILMES_ATORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
