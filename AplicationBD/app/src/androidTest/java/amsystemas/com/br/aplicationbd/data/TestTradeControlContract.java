package amsystemas.com.br.aplicationbd.data;

import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by arthur on 04/08/16.
 */
public class TestTradeControlContract extends AndroidTestCase {
    //intencionalmente incluiu uma barra para fazer de certeza Uri esta pegando
    private static final String TEST_TRADECONTROL_LOCATION="/AOL5459";
    private static final int TEST_TRADECONTROL_ID=15;
    private static final long TEST_VEICULO_DATE=1419033600L;

    public static final String LOG_TAG = TestTradeControlContract.class.getSimpleName();

    public void testBuildTradeControlLocation(){
        Uri locationUri=TradeControlContract.VeiculoEntry.buildVeiculoUri(TEST_TRADECONTROL_ID);
        assertNotNull("Error: Null Uri returned. You deve fill-in buildTradecontrollocation" +"TradeControlContrato:", locationUri);
        assertEquals("Error: Tradecontrol location not propriedade to end URI",TEST_TRADECONTROL_ID,Integer.parseInt(locationUri.getLastPathSegment()));
        Log.v(LOG_TAG,"Passando a Uri da propriedade Localtion :" +locationUri.toString());

        assertEquals("Error: TradeControl location Uri nao foi encontrado noss resultado esperado", locationUri.toString(),"content://br.com.amsystemas.applicationbd/location/15");


    }
}
