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
public class InputStreamCollection implements Collection<String> {

    private static final Log log = LogFactory.getLog(InputStreamCollection.class);

    private InputStream inputStream;

    public InputStreamCollection(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Sorry this is a stream so it could have any size.");
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        return new InputStreamCollectiveIterator(this.inputStream);
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Sorry this stream could be any size so turning the whole thing into " +
                "an array may not be such a good idea.");
    }

    @Override
    public <T> T[] toArray(T[] s) {
        throw new UnsupportedOperationException("Sorry this stream could be any size so turning the whole thing into " +
                "an array may not be such a good idea.");
    }

    @Override
    public boolean add(String s) {
        throw new UnsupportedOperationException("This is an input stream, so it can only be read.");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("This is an input stream, so it can only be read.");
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> s) {
        throw new UnsupportedOperationException("This is an input stream, so it can only be read.");
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        throw new UnsupportedOperationException("This is an input stream, so it can only be read.");
    }

    @Override
    public boolean retainAll(Collection<?> objects) {
        throw new UnsupportedOperationException("This is an input stream, so it can only be read.");
    }

    @Override
    public void clear() {
        try {
            if (this.inputStream != null) this.inputStream.close();
        } catch (IOException e) {
            log.error(" -- Failed to close the input stream.");
        }
    }

    protected InputStream getInputStream() {
        return inputStream;
    }
}
