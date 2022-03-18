package com.he.v8_inspect.network;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class CountingOutputStream extends FilterOutputStream {
    private long mCount;

    public long getCount() {
        return this.mCount;
    }

    public CountingOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(int i) throws IOException {
        this.out.write(i);
        this.mCount++;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        this.mCount += (long) i2;
    }
}