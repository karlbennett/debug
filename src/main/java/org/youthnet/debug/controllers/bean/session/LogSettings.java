package org.youthnet.debug.controllers.bean.session;

/**
 * User: karl
 * Date: 18-Jun-2010
 */
public class LogSettings {

    private Integer lineNum;

    public LogSettings() {
        this.lineNum = new Integer(500);
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}
