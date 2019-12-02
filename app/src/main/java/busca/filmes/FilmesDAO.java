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
    public static void excluir(Context contexto, int idfilme){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.delete("atores" , "idfilme = " + idfilme , null);
        db.delete("filmes" , "idfilme = " + idfilme , null );

    }

    public static List<Filme> getFilmes(Context contexto){
        List<Filme> listaDeTimes = new ArrayList<Filme>();
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM filmes ORDER BY nome", null);
        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Filme f = new Filme();
                f.setId(  cursor.getInt( 0 ) );
                f.setNome( cursor.getString( 1 ) );
                listaDeTimes.add( f );
            }while ( cursor.moveToNext() );
        }
        return listaDeTimes;
    }
    public static Filme getFilmeById(Context contexto, int idfilme){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        String sql = "SELECT * FROM filmes where id = " + idfilme;
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

    public static List<Filme> getFilmeByName(Context contexto, String nomeBusca){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Filme> filme = new ArrayList<Filme>();
        List<Ator> ator = new ArrayList<Ator>();

//        String sql = "SELECT * FROM filmes where nome = " + nomeTime;
        //Cursor cursor = db.rawQuery("SELECT * FROM filmes where nome like '%" + nomeFilme + "%'", null);
        Cursor cursor = db.rawQuery("SELECT e.nome, d.nome FROM filmes e, atores d where e.nome like '%" + nomeBusca + "%'" + " AND d.nome like '%" + nomeBusca + "%'", null);
        Log.i("log do sql", "getFilmeByName: " + cursor);

        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Filme t = new Filme();
                //t.setId(  cursor.getInt( 0 ) );
                t.setNome( cursor.getString( 0 ) );
                filme.add( t );
                Ator a = new Ator();
                //a.setId( cursor.getInt(0 ) );
                a.setNome( cursor.getString(1));
                ator.add( a );
            }while ( cursor.moveToNext() );
        }
        Log.i("Retornando Flmes", "getTimeByName: " + filme);
        Log.i("Retoanando Atores", "getFilmeByName: " + ator);
        return filme;
    }

    public static List getTudoByName(Context contexto, String nomeBusca){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();
        List tudao = new ArrayList<>();

        Cursor cursorFilme = db.rawQuery("SELECT nome FROM filmes where nome like '%" + nomeBusca + "%'", null);
        Cursor cursorAtor = db.rawQuery("SELECT nome FROM atores where nome like '%" + nomeBusca + "%'", null);

        if ( cursorFilme.getCount() > 0 ){
            cursorFilme.moveToFirst();
            do{
                Filme t = new Filme();
                //t.setId(  cursor.getInt( 0 ) );
                t.setNome( cursorFilme.getString( 0 ) );
                tudao.add( t );
            }while ( cursorFilme.moveToNext() );
        }
        if ( cursorAtor.getCount() > 0 ){
            cursorAtor.moveToFirst();
            do{
                Ator a = new Ator();
                //a.setId( cursor.getInt(0 ) );
                a.setNome( cursorAtor.getString(0));
                tudao.add( a );
            }while ( cursorAtor.moveToNext() );
        }

        Log.i("Retornando Filmes e tudo mais", "getTUDAOByName: " + tudao);
        return tudao;
    }
}
