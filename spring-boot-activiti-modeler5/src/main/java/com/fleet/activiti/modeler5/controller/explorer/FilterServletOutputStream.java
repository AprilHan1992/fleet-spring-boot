package com.fleet.activiti.modeler5.controller.explorer;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FilterServletOutputStream extends ServletOutputStream {

    private DataOutputStream dos;
    private WriteListener writeListener;

    public FilterServletOutputStream(OutputStream os) {
        dos = new DataOutputStream(os);
    }

    public void write(int b) throws IOException {
        dos.write(b);
    }

    public void write(byte[] b) throws IOException {
        dos.write(b);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        dos.write(b, off, len);
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        this.writeListener = writeListener;
    }

    @Override
    public boolean isReady() {
        return true;
    }
}
