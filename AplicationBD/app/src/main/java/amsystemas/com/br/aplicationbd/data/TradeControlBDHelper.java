package amsystemas.com.br.aplicationbd.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arthu on 30/07/2016.
 * Exemplo de SqliteOpenHelper
 * Como criar base de dados
 * como migrar base de dados
 * como manter uma referencia statica
 * Como ler e escrever database
 * Esta classe tambem pode atuar com DatabaseContext.IFactory para criar referencia
 * a base de dados para ler e gravar. Este não é um aspecto critico para entender mais
 * inclui vantagens
 */
public class TradeControlBDHelper extends SQLiteOpenHelper{
    //Este é um e somente uma database Helpers for this database
    //para aplicacao Inteirra
    public static TradeControlBDHelper m_self = new TradeControlBDHelper(MyApplication.m_appContext);

    /**
     * versao atual da base de dados
     */
    public static final int DATABASE_VERSION=2;
    //Nome da base de dados no dispositivo
    static final String DATABASE_NAME ="tradecontrol.db";
    //Nome do arquivo DDL que voce quer carregar enquanto cria base de daddo
    private static final String CREATE_DATABASE_FILENAME = "create-tradecontrol-db.sql";
    //Tag logging
    private static final String TAG="TradeControlBDHelper";
    //Passa o nome database e versao para a classe bae
    //Este não é um contrutor public
     TradeControlBDHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //cria uma tabela para manter os dados do veiculo. Um veiculo consiste de uma placa
        // a quilometragem
        //A base de dados nao existe. Carrega DDL de um arquivo no diretorio assets
       final String SQL_CREATE_Veiculo_Table ="Create Table " + TradeControlContract.VeiculoEntry.TABLE_NAME + " (" +
               TradeControlContract.VeiculoEntry._ID + " INTEGER PRIMARY KEY," +
               TradeControlContract.VeiculoEntry.Column_Veiculo_Placa + " TEXT UNIQUE NOT NULL," +
               TradeControlContract.VeiculoEntry.Column_Veiculo_Quilometragem + " TEXT NOT NULL" + ");";
        db.execSQL(SQL_CREATE_Veiculo_Table);

    }
    /*
    //Uma  funcao para carregar um declaracao SQl na hora de usar o metodo execSQL
    private void loadSQLFrom(String asserFilename,SQLiteDatabase db){
        List<String>statements = getDDLStatementsFrom(asserFilename);
        for(String stmt : statements){
            Log.d(TAG,"Executando a declaracao :" + stmt);
            db.execSQL(stmt);
        }
    }
    //Optimizando esta funacao pra robustez
    //Agora é suposto que não existe comentario no arquivo
    //a declaracao sao separadas por virgular
    private List<String> getDDLStatementsFrom(String assetFilename){
        ArrayList<String>l=new ArrayList<String>();
        String s = getStringFromAssetFile(assetFilename);
        for(String stmt:s.split(";")){
            //add the stmt se é uma declaração valida
            if(isValid(stmt)){
                l.add(stmt);
            }
        }
        return l;
    }
    private boolean isValid(String s){
        //escreve a logica aqui para ver se é null, vazia ou etc
        return true; //no momento
    }
    */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //use antigo e nova versao para executar declaracao DDL
        //para atualizar a base de dados
        db.execSQL("DROP TABLE IF EXISTS" + TradeControlContract.VeiculoEntry.TABLE_NAME);
        onCreate(db);
    }
    /*
    //usando sua especificao para lembrar o context da aplicacao
    //entao usando este contexto da aplicação para ler  o assets
    private String getStringFromAssetFile(String filename){
        Context ctx = MyApplication.m_appContext;
        if(ctx ==null){
            throw new RuntimeException("Desculpe o contexto de sua app é null !");
        }
        try{
            AssetManager am = ctx.getAssets();
            InputStream is = am.open(filename);
            String s = convertStreamToString(is);
            is.close();
            return s;
        }catch (IOException x){
            throw  new RuntimeException("Desculpe nao foi possivel ler o arquivo : " + filename,x);
        }
    }
    //Optimize depois, Este pode nao ser a maneira mais eficiente de ler
    private String convertStreamToString(InputStream is) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = is.read();
        while(i != -1){
            baos.write(i);
            i = is.read();
        }
        return baos.toString();
    }
    */
    //Aqui sao alguns exemplos de como pegar acesso e ler and escrever base de dados
    //Este metodos vai fazer mais sentido depois
    //a transacao aplicada atraves proxies dinamicos
    /*
    public ReadDataContext createReadableDatabase(){
        return new ReadDatabaseContext(this.getReadableDatabase());

    }
    public WriteDataBaseContext createWriteDatabase(){
        return new WriteDataseContext(this.getWritableDatabase());
    }
    */

}
//Aqui é o codigo para Myapplication retornar o contexto


