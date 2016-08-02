package amsystemas.com.br.aplicationbd.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;

import android.provider.BaseColumns;
import android.text.format.Time;

/**
 * Created by arthur on 01/08/16.
 * Define tabela e nome das colunas para a base de dados tradecontrol
 */
public class TradeControlContract {
    //O "Content authority" é o nome para todo o content provider, similar ao
    //relacionamento entre o dominio e o nome do website. Uma string conveniente para user
    //no content authority é o nome do pacote da app, que é garantido ser unico no dispositivo.
    public static final String CONTENT_AUTHORITY="br.com.amsystemas.applicationbd";

    //Use Content_Authority para criar a base de todas as URI que aplicacao vai contatar
    //o content provider
    public static final Uri BASE_CONTENT_URI =Uri.parse("content://" + CONTENT_AUTHORITY);

    //Possiveis caminhos(colocados juntos para base content URI para possiveis URI's)
    //Para Instanciar, content://br.com.amsystemas.aplicationbd/tradecontrol é um caminho valido
    //para procurar a base tradecontrol. content://br.com.amsystemas.aplicationbd/givemeroot/ não será possivel
    // como o ContentProvider tem sido dado qualquer informação no qual fazer com  "givemeroot"
    //Pelo menos esperamos que nao.
    public static final String PATH_TRADECONTROL="tradecontrol";
    public static final String PATH_LOCATION="location";

    //para fazer isto mais facil para o data exata, normalizamos  todas as datas que vamos usar
    //a base de dados para iniciar o Julian day no UTC
    public static long normalizeDate(long startDate){
        //Normaliza a data inicial iniciando pelo (UTC)day
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate,time.gmtoff);
        return time.setJulianDay(julianDay);
    }
    //Classe aninhadas que define o conteudo da tabela veiculo
    public static final class VeiculoEntry implements BaseColumns{

        public static final Uri CONTENT_URI=BASE_CONTENT_URI.buildUpon().appendPath(PATH_LOCATION).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +"/" + CONTENT_AUTHORITY + "/" + PATH_LOCATION;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LOCATION;

        //Table name
        public static final String TABLE_NAME="veiculo";

        //A placa do veiculo que vamos enviar para o sistema
        public static final String Column_Veiculo_Placa="placa";
        //A quilometragem do veiculo
        public static final String Column_Veiculo_Quilometragem="quilometragem";

        public static Uri buildVeiculoUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

    }
    //definir demais classes com as defidas tabelas



}
