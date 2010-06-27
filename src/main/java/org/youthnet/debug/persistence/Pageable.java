package org.youthnet.debug.persistence;

/**
 * User: karl
 * Date: 27-Jun-2010
 */
public interface Pageable {

    public Integer getLineNumber();

    public void setLineNumber(Integer lineNum);

    public Page getPage(Integer pageNum);
}
