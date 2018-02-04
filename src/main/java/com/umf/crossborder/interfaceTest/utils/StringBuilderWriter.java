package com.umf.crossborder.interfaceTest.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;


public class StringBuilderWriter extends Writer{
	
	private final StringBuilder builder;

	public StringBuilderWriter() throws IOException {
        this.builder = new StringBuilder();
        InputStream i;

    }
	
	@Override
	public void write(final char[] value, final int offset, final int length) {
	   if (value != null) {
	       builder.append(value, offset, length);
	   }
	}

	@Override
	public String toString() {
	      return builder.toString();
	}

	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}
}
