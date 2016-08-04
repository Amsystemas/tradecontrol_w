package amsystemas.com.br.aplicationbd.data;

import android.content.UriMatcher;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by arthur on 04/08/16.
 */
public class TestUriMatcher extends AndroidTestCase {
    private static final String TAG_LOG=TestUriMatcher.class.getName();

    private static final String LOCATION_QUERY="AOL5459";
    private static final long TEST_DATE=1419033600L ;
    private static final long TEST_VEICULO_ID=15;

    //content://br.com.amsystemas.applicationbd/
    private static final Uri TESTE_TRADECONTROL_DIR = TradeControlContract.VeiculoEntry.CONTENT_URI;
    //private static final Uri TESTE_TRADECONTROL_VEICULO_DIR=TradeControlContract.VeiculoEntry.buildVeiculoWithPlaca(LOCATION_QUERY);

    public void testUriMatcher(){
        UriMatcher testMatcher = TradeControlProvider.buildUriMatcher();
        Log.v(TAG_LOG,"<<< :" + testMatcher.match(TESTE_TRADECONTROL_DIR));
        assertEquals("Erro : O Tradecontrol Uri estava colocado Incorretamente .",testMatcher.match(TESTE_TRADECONTROL_DIR),300);
    }
}
