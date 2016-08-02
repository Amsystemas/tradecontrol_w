package amsystemas.com.br.aplicationbd.data;

import java.util.Date;

/**
 * Created by arthur on 30/07/16.
 */
public class BaseEntity {
    private int id;//database identifer

    private String ownedAccount =null;
    private String createBy;
    private Date createdOn;
    private String lastUpdateBy;
    private Date lastUpdateOn;

    public BaseEntity(String ownedAccount,String createBy,Date createdOn,String lastUpdateBy,Date lastUpdateOn, int id){
        super();
        this.ownedAccount=ownedAccount;
        this.createBy=createBy;
        this.createdOn=createdOn;
        this.lastUpdateBy=lastUpdateBy;
        this.lastUpdateOn=lastUpdateOn;
        this.id=id;
    }
    //Para Persitence
    public BaseEntity(){}

    public String getCreateBy() {
        return createBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public int getId() {
        return id;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public Date getLastUpdateOn() {
        return lastUpdateOn;
    }

    public String getOwnedAccount() {
        return ownedAccount;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public void setLastUpdateOn(Date lastUpdateOn) {
        this.lastUpdateOn = lastUpdateOn;
    }

    public void setOwnedAccount(String ownedAccount) {
        this.ownedAccount = ownedAccount;
    }

}
