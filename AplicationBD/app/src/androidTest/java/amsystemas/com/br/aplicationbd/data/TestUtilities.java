package amsystemas.com.br.aplicationbd.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.Map;
import java.util.Set;

/**
 * Created by arthur on 02/08/16.
 */
public class TestUtilities extends AndroidTestCase {
    static ContentValues createsVeiculoValues(){
        ContentValues testValues = new ContentValues();
        testValues.put(TradeControlContract.VeiculoEntry.Column_Veiculo_Placa,"AOL5459");
        testValues.put(TradeControlContract.VeiculoEntry.Column_Veiculo_Quilometragem,"168000");
        return testValues;
    }
    static void validateCurrentRecord(String error, Cursor valueCursor, ContentValues expectedValues){
        Set<Map.Entry<String,Object>>valueSet = expectedValues.valueSet();
        for(Map.Entry<String,Object>entry:valueSet){
            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);
            assertFalse("Column '" + columnName + "' nao encontrado. " + error, idx ==-1);
            String expectedValue = entry.getValue().toString();
            assertEquals("Value '" + entry.getValue().toString() + "' nao foi encontrado o valor esperado'" + expectedValue + "'. " + error,expectedValue,valueCursor.getString(idx));
        }
    }
}
