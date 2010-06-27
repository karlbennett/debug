package org.youthnet.debug.persistence;

import java.util.Collection;

/**
 * User: karl
 * Date: 27-Jun-2010
 */
public interface Page<T> {

    public Integer getPageNumber();

    public Integer getLineNumber();

    public Collection<T> getLines();
}
