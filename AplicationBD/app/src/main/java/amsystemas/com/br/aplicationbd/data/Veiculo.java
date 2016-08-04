package amsystemas.com.br.aplicationbd.data;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by arthur on 30/07/16.
 */
public class Veiculo extends BaseEntity {
    //Key data fields
    //************
    private String placa;
    private int quilometragem;
    private long data;

    //************
    public Veiculo(String ownedAccount, String createBy, Date createdOn, String lastUpdateBy, Date lastUpdateOn, String placa, int Quilometragem){
        super(ownedAccount,createBy,createdOn,lastUpdateBy,lastUpdateOn,-1);
        this.placa=placa;
        this.quilometragem=quilometragem;
    }
    public Veiculo(){

    }
    //O seguinte metodo esta aqui para testar
    // e tambem para ver como objeto veiculo é tipicamente criado
    public static Veiculo createVeiculo(){
        String ownedAccout = "Account1";
        String createBy="Arthur";
        Date createdOn = Calendar.getInstance().getTime();
        String lastUpdateBy="Arthur";
        Date lastUpdateOn = Calendar.getInstance().getTime();

        //Vemos como muitos veiculo eu tenho a incremento por 1
        //O seguinte metodo retorna uma colecao de veiculos na database
        //isto não essencial
        //voce vai ver isto claramente nao secao transaccoes
       // List<Veiculo> veiculos = Services.PersistenceServices.veiculos.getAllVeiculos();
        //int i = veiculos.size();
        String placa = String.format("ACD676 %s",100000);
        //String author = "arthur";
        int quilometragem = 10 *2*100;
        return new Veiculo(ownedAccout,createBy,createdOn,lastUpdateBy,lastUpdateOn,placa,quilometragem);
    }

    public String getPlaca() {
        return placa;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public long getData() {
        return data;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public void setData(long data) {
        this.data = data;
    }
}
