package org.youthnet.debug.io;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

/**
 * User: karl
 * Date: 26-Jun-2010
 */
public class InputStreamCollectionHTMLEscaped extends InputStreamCollection {

    private static final Log log = LogFactory.getLog(InputStreamCollectionHTMLEscaped.class);

    public InputStreamCollectionHTMLEscaped(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public Iterator<String> iterator() {
        return new InputStreamCollectiveIteratorHTMLEscaped(getInputStream());
    }
}