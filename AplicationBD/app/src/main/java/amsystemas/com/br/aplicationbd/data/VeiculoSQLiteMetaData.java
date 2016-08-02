package amsystemas.com.br.aplicationbd.data;

/**
 * Created by arthur on 30/07/16.
 */
public class VeiculoSQLiteMetaData extends BaseEntitySQLiteMetaData {
    static public final String TABLE_NAME="t_veiculos";
    static public final String PLACA="placa";
    static public final String QUILOMETRAGEM="quilometragem";

    public static VeiculoSQLiteMetaData s_self = new VeiculoSQLiteMetaData();
}
