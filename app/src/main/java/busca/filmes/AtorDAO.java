package busca.filmes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AtorDAO {

    public void inserir(Context contexto, Ator ator){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nome", ator.getNome() );
        valores.put("idFilme", ator.getFilme() );

        db.insert("atores" , null , valores );
    }
    public static void excluir(Context contexto, int idAtor){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.delete("atores" , "idAtor = " + idAtor , null );
    }

    public static List<Ator> getAtores(Context contexto){
        List<Ator> listaDeAtores = new ArrayList<Ator>();
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM atores", null);

        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Ator a = new Ator();
                a.setId(  cursor.getInt( 0 ) );
                a.setNome( cursor.getString( 1 ) );
                listaDeAtores.add( a );
            }while ( cursor.moveToNext() );
        }
//        Log.i("atores do banco", "getAtores: " + listaDeAtores);
        return listaDeAtores;
    }

    public static List<Ator> getAtoresById(Context contexto, int idfilme){
        List<Ator> listaDeAtores = new ArrayList<Ator>();
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM atores where idfilme=" + idfilme, null);

        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Ator a = new Ator();
                a.setId(  cursor.getInt( 0 ) );
                a.setNome( cursor.getString( 1 ) );
                a.setFilme(cursor.getInt(2));
                listaDeAtores.add( a );
            }while ( cursor.moveToNext() );
        }
//        Log.i("Atores do banco", "getAtores: " + listaDeAtores);
        return listaDeAtores;
    }

    public Ator buscarAtorPorNome(Context contexto, String nome){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();
        Ator a;
        Cursor cursor = db.rawQuery("SELECT nome FROM atores WHERE nome = ?", new String[] {nome});
        cursor.moveToFirst();

        if(cursor.getCount() > 0){

            a = new Ator();
            a.setNome(cursor.getString(0));

        } else {
            a = null;
        }

        cursor.close();
        db.close();

        return a;
    }
}
