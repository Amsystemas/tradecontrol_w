package amsystemas.com.br.aplicationbd.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by arthur on 03/08/16.
 */
public class TradeControlProvider extends ContentProvider {
    //The URI incorporado usado pelo contentProvider
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private TradeControlBDHelper mOpenHelper;

     static final int VEICULO=100;
    static final int VEICULO_WITH_LOCATION=101;
    static final int VEICULO_WITH_LOCATION_AND_DATA=102;
    static final int LOCATION=300;

    private static final SQLiteQueryBuilder sVeiculoByLocationSettingQueryBuilder;

    static {
        sVeiculoByLocationSettingQueryBuilder = new SQLiteQueryBuilder();
        //Aqui podemos implementar uma consulta con join
        sVeiculoByLocationSettingQueryBuilder.setTables(TradeControlContract.VeiculoEntry.TABLE_NAME
                //" INNER JOIN " +
                //TradeControlContract.OutraTabelaEntry.TABLE_NAME +
                // "ON " + TradeControlContract.VeiculoEntry.TABLE_NAME +
                // "." + TradeControlContract.VeiculoEntry.COLUMN_LOC_KEY +
                // " = " + TradeControlContract.OutraTabelaEntry.TABLE_NAME +
                // "." + TradeControlContract.OutraTabelaEntry._ID);
        );
    }
    //Location.location_setting + ?
    private static final String sVeiculoSettingSelection= TradeControlContract.VeiculoEntry.TABLE_NAME + "."
            + TradeControlContract.VeiculoEntry.COLUMN_LOCATION_SETTING + "= ?";
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
static UriMatcher buildUriMatcher(){
final UriMatcher matcher =new UriMatcher(UriMatcher.NO_MATCH);
    final String authority = TradeControlContract.CONTENT_AUTHORITY;

    //para cada tipo de URI vamos adicionar o codigo correspondente
    matcher.addURI(authority,TradeControlContract.PATH_TRADECONTROL,VEICULO);
    matcher.addURI(authority,TradeControlContract.PATH_TRADECONTROL + "/*",VEICULO_WITH_LOCATION);
    matcher.addURI(authority,TradeControlContract.PATH_TRADECONTROL + "/*/#",VEICULO_WITH_LOCATION_AND_DATA);
    matcher.addURI(authority,TradeControlContract.PATH_LOCATION, LOCATION);
    return matcher;
}
}
