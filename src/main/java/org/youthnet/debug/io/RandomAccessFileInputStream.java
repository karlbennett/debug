package org.youthnet.debug.io;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.youthnet.debug.util.ExceptionsUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * User: karl
 * Date: 24-Jun-2010
 *
 * Wraps an java.io.InputStream around a RandomAccessFile.
 */
public class RandomAccessFileInputStream extends InputStream {

    private static final Log log = LogFactory.getLog(RandomAccessFileInputStream.class);

    private RandomAccessFile randomAccessFile;
    private long mark;
    private int readlimit;

    public RandomAccessFileInputStream(RandomAccessFile randomAccessFile) {
        this.randomAccessFile = randomAccessFile;    
    }

    @Override
    public int read() throws IOException {
        return this.randomAccessFile.read();
    }

    @Override
    public int read(byte[] bytes) throws IOException {
        return this.randomAccessFile.read(bytes);
    }

    @Override
    public int read(byte[] bytes, int off, int len) throws IOException {
        return this.randomAccessFile.read(bytes, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        n += this.randomAccessFile.getFilePointer();
        this.randomAccessFile.seek(n);
        return n;
    }

    @Override
    public int available() throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {
        this.randomAccessFile.close();
    }

    @Override
    public void mark(int readlimit) {
        try {
            this.mark = this.randomAccessFile.getFilePointer();
            this.readlimit = readlimit;
        } catch (IOException e) {
            log.error(ExceptionsUtil.getStackTrace(e));
        }
    }

    @Override
    public void reset() throws IOException {
        if (this.randomAccessFile.getFilePointer() < (mark + readlimit)) this.randomAccessFile.seek(mark);
    }

    @Override
    public boolean markSupported() {
        return true;
    }
}
