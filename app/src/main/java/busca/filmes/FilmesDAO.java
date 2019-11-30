package busca.filmes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FilmesDAO {

    public static void inserir(Context contexto, Filme filme){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nome", filme.getNome() );

        db.insert("filmes" , null , valores );

    }
    public static void editar(Context contexto, Filme filme){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nome", filme.getNome() );

        db.update("filmes" , valores, "id = " + filme.getId(), null );

    }
    public static void excluir(Context contexto, int idFilme){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.delete("filmes" , "idFilme = " + idFilme , null );

    }

    public static List<Filme> getFilmes(Context contexto){
        List<Filme> listaDeTimes = new ArrayList<Filme>();
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM filmes ORDER BY nome", null);
        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Filme t = new Filme();
                t.setId(  cursor.getInt( 0 ) );
                t.setNome( cursor.getString( 1 ) );
                listaDeTimes.add( t );
            }while ( cursor.moveToNext() );
        }
        return listaDeTimes;
    }
    public static Filme getFilmeById(Context contexto, int idFilme){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        String sql = "SELECT * FROM filmes where id = " + idFilme;
        Cursor cursor = db.rawQuery(sql,null);

        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();

                Filme t = new Filme();
                t.setId(  cursor.getInt( 0 ) );
                t.setNome( cursor.getString( 1 ) );

                return t;

        }else {
            return null;
        }
    }
    public static List<Filme> getFilmeByName(Context contexto, String nomeTime){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Filme> filme = new ArrayList<Filme>();

//        String sql = "SELECT * FROM filmes where nome = " + nomeTime;
        Cursor cursor = db.rawQuery("SELECT * FROM filmes where nome like '%" + nomeTime + "%'", null);

        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Filme t = new Filme();
                t.setId(  cursor.getInt( 0 ) );
                t.setNome( cursor.getString( 1 ) );
                filme.add( t );
            }while ( cursor.moveToNext() );
        }
        Log.i("retornando filmes", "getTimeByName: " + filme);
        return filme;
    }
}
