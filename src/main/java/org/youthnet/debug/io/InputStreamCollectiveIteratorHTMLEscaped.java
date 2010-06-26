package org.youthnet.debug.io;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;

/**
 * User: karl
 * Date: 26-Jun-2010
 */
public class InputStreamCollectiveIteratorHTMLEscaped extends InputStreamCollectiveIterator {

    private static final Log log = LogFactory.getLog(InputStreamCollectiveIteratorHTMLEscaped.class);

    public InputStreamCollectiveIteratorHTMLEscaped(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public String next() {
        String nextString = super.next();
        if (nextString != null)
            return StringEscapeUtils.escapeHtml(nextString).replaceAll("\n", "<BR>\n").replaceAll(" ", "&nbsp;");
        
        return null;
    }
}