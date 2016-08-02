package amsystemas.com.br.aplicationbd.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by arthur on 30/07/16.
 */
public class VeiculoPSSQLite {

    private long createVeiculo(Veiculo veiculo){
        //Get Access  to a read database
        SQLiteDatabase db = TradeControlBDHelper.m_self.getWritableDatabase();
        //preenche campos do objeto veiculo dentro do content values
        ContentValues bcv =new ContentValues();
        //
        // preenche outros campos
        bcv.put(VeiculoSQLiteMetaData.PLACA,veiculo.getPlaca());
        bcv.put(VeiculoSQLiteMetaData.QUILOMETRAGEM,veiculo.getQuilometragem());
        String nullColumnName=null;
        long rowId = db.insertOrThrow(VeiculoSQLiteMetaData.TABLE_NAME,nullColumnName,bcv);
        return rowId;
    }
    private void updateVeiculo(Veiculo veiculo){
        if(veiculo.getId()<0){
            throw  new SQLException("Veiculo id e menor que 0");
        }
        //Get access para ler a Database
        SQLiteDatabase db = TradeControlBDHelper.m_self.getWritableDatabase();
        //Preenche os campos do objeto veiculo no content values
        ContentValues bcv = new ContentValues();
        //... fill other campos
        bcv.put(VeiculoSQLiteMetaData.PLACA,veiculo.getPlaca());
        bcv.put(VeiculoSQLiteMetaData.QUILOMETRAGEM,veiculo.getQuilometragem());

        //Voce pode fazer isto
        String whereClause = String.format("%s=%s",VeiculoSQLiteMetaData.ID_COLNAME,veiculo.getId());
        String whereClauseArgs =null;
        //Nosso proximo 4 linhas
        String whereClause2 = VeiculoSQLiteMetaData.ID_COLNAME+" =?";
        String[] whereClause2Args = new String[1];
        whereClause2Args[1]=Integer.toString(veiculo.getId());

        int count = db.update(VeiculoSQLiteMetaData.TABLE_NAME,bcv,whereClause2,whereClause2Args);
        if(count==0){
            throw new SQLException(String.format("Falha ao tualizar veculo :%s",veiculo.getId()));
        }
    }
    public  void deleteVeiculo(int veiculoid){
        //Get acceso ao writable database
        SQLiteDatabase db = TradeControlBDHelper.m_self.getWritableDatabase();
        String tname= VeiculoSQLiteMetaData.TABLE_NAME;
        String whereClause = String.format("%s =%s;",VeiculoSQLiteMetaData.ID_COLNAME,veiculoid);
        String[] whereClauseargs =null;
        int i = db.delete(tname,whereClause,whereClauseargs);
        if(i != 1){
            throw new RuntimeException("The number de veiculos dtelete não é 1 mas :" + i);
        }
    }
    public List<Veiculo>getAllVeiculos(){
        //getAccess para ler database
        SQLiteDatabase db =TradeControlBDHelper.m_self.getReadableDatabase();

        String tname = VeiculoSQLiteMetaData.TABLE_NAME;
        //get coluna name array from o metada class
        //(See
        //String[]colnames= VeiculoSQLiteMetaData.s_self.ge
        return null;
    }

}
