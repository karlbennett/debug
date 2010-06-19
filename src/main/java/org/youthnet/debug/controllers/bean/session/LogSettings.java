package org.youthnet.debug.controllers.bean.session;

/**
 * User: karl
 * Date: 18-Jun-2010
 */
public class LogSettings {

    private Integer lineNum;
    private boolean truncate;

    public LogSettings() {
        this.lineNum = new Integer(500);
        this.truncate = true;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public boolean isTruncate() {
        return truncate;
    }

    public void setTruncate(boolean truncate) {
        this.truncate = truncate;
    }
}
