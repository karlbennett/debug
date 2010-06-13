package org.youthnet.debug.domain.core.old;

import java.io.Serializable;
import java.util.Date;


/**
 * User: Olivier Van Acker (olivier.van.acker@youthnet.org)
 * Date: 01-Feb-2010
 */
public class VBaseResult implements Serializable {

    public enum RESULT {
        SUCCESS,
        FAIL
    }

    private String resultMessage;
    private RESULT result;
    private Date databaseDate;

    public VBaseResult() {
        
    }

    public VBaseResult(RESULT result, String resultMessage, Date databaseDate) {
        this.resultMessage = resultMessage;
        this.result = result;
        this.databaseDate = databaseDate;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public RESULT getResult() {
        return result;
    }

    public Date getDatabaseDate() {
        return databaseDate;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public void setResult(RESULT result) {
        this.result = result;
    }

    public void setDatabaseDate(Date databaseDate) {
        this.databaseDate = databaseDate;
    }
}
