package amsystemas.com.br.aplicationbd.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import java.util.HashSet;

/**
 * Created by arthur on 01/08/16.
 */
public class TestBD extends AndroidTestCase {
    public static final String LOG_TAG = TestBD.class.getSimpleName();

    //Deste que nos queremos cada teste iniciar com slate limpo
    void deleteTheDatabase(){
        mContext.deleteDatabase(TradeControlBDHelper.DATABASE_NAME);
    }
    /*
    Esta funcão get chama antes de cada teste é executado para delete a base de dados. Este faz com
    certeza que nos sempre temos um teste limpo

     */
    public void setUp(){
        deleteTheDatabase();
    }
    /*
    Students:
     */

    public void testCreateDb()throws Throwable{
        //contrui um HashSet o todas os nome de tabela no desejamos olhar para
        //observe que aqui vai ser outro tabela no DB que armazena o
        //Android metadata(db version information)
        final HashSet<String> tableNameHashSet = new HashSet<String>();
        tableNameHashSet.add(TradeControlContract.VeiculoEntry.TABLE_NAME);
        //tableNameHashSet.add(TradeControlContract.VeiculoEntry.TABLE_NAME)
        mContext.deleteDatabase(TradeControlBDHelper.DATABASE_NAME);
        SQLiteDatabase db =new TradeControlBDHelper(this.mContext).getWritableDatabase();
        assertEquals(true,db.isOpen());

        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'",null);
        assertTrue("Error : Isso significa que a database no foi criado corretamente",c.moveToFirst());
        //verifica que a tabase foi criado
        do{
            tableNameHashSet.remove(c.getString(0));
        }while(c.moveToNext());
        //se isto falhar segnifica que sua database não contem veiculo
        assertTrue("Error: sua database foi criado sem a tabela veiculo",tableNameHashSet.isEmpty());
        //agora nossa tabela contem o colunas corretas'
        c = db.rawQuery("PRAGMA table_info(" + TradeControlContract.VeiculoEntry.TABLE_NAME +")" ,null);
        assertTrue("Error : isto significa que no estamos habilitados para consultar informacoes da tabela",c.moveToFirst());
        //Construir um HashSet de todas as colunas que nos queremos encontrar
        final HashSet<String> veiculoColumnHashSet = new HashSet<>();
        veiculoColumnHashSet.add(TradeControlContract.VeiculoEntry._ID);
        veiculoColumnHashSet.add(TradeControlContract.VeiculoEntry.Column_Veiculo_Placa);
        veiculoColumnHashSet.add(TradeControlContract.VeiculoEntry.Column_Veiculo_Quilometragem);

        int columnNameIndex = c.getColumnIndex("name");
        Log.v(LOG_TAG,"Coluna Indice " + columnNameIndex);
        do{
            String columnName = c.getString(columnNameIndex);
            Log.v(LOG_TAG,columnName);
            veiculoColumnHashSet.remove(columnName);
        }while(c.moveToNext());

        //se este falhar, isto significa que sua base de dados nao contem todos as colunas necessarias
        assertTrue("Error : A database nao contem todas a colunas necessaria", veiculoColumnHashSet.isEmpty());
        db.close();



    }
    public void testVeiculoTable(){
        insertVeiculo();
    }
    public long insertVeiculo(){
        //Primeiro passo pegar a referencia to writable database
        //se existir aqui um erro no massive Sql table creation Strings
        //error vai ser lancados quando voce tentar pegar a writatable database
        TradeControlBDHelper dbHelper = new TradeControlBDHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //Segundo passo: Create contentValues que voce quer inserir
        //voce pode usar
        ContentValues testValues = TestUtilities.createsVeiculoValues();
        //Terceiro passo inserir contentValues no database e pegar o id da linha
        long veiculoRowId;
        veiculoRowId = db.insert(TradeControlContract.VeiculoEntry.TABLE_NAME,null,testValues);
        //verifica o retorno
        assertTrue(veiculoRowId != -1);
        //Quarto passo: Query the database and
        Cursor cursor = db.query(TradeControlContract.VeiculoEntry.TABLE_NAME,null,null,
                null,null,null,null);
        //move o cursor para valid dabase row and verifica para ver  se existem qualquer registro
        //da consulta
        assertTrue("Error: No Records retornado da consulta ", cursor.moveToFirst());

        //Quinto passo Validar o resultado do dado do Cursor com o original ContentValues
        TestUtilities.validateCurrentRecord("Error : location Query Validation Failed ",cursor,testValues);

        //Move o cursor para demonstrar que ha somente um registro no base
        assertFalse("Error : Mais que um registro retornado",cursor.moveToNext());

        //sexto passo fecha o cursor e a base de dados
        cursor.close();
        db.close();
        return veiculoRowId;

    }
}
