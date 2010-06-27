package org.youthnet.debug.io;

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
public class InputStreamCollectiveIterator implements Iterator<String> {

    private static final Log log = LogFactory.getLog(InputStreamCollectiveIterator.class);

    public static final int BUFFER_SIZE = 1024;

    private InputStream inputStream;
    private byte[] bytes;
    private int bytesRead;

    public InputStreamCollectiveIterator(InputStream inputStream) {
        this.inputStream = inputStream;
        this.bytes = new byte[BUFFER_SIZE];
        this.bytesRead = BUFFER_SIZE;
    }

    @Override
    public boolean hasNext() {
        return this.bytesRead > 0;
    }

    @Override
    public String next() {
        try {
            if ((this.bytesRead = this.inputStream.read(this.bytes)) > 0)
                return new String(Arrays.copyOfRange(this.bytes, 0, this.bytesRead));
            else this.inputStream.close();
        } catch (IOException e) {
            log.error(" -- Failed to read the input stream.");
        }

        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("This is an input stream, so it can only be read.");
    }
}
