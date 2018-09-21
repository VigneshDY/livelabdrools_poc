package com.livelabdrools.reader;

import com.livelabdrools.model.Data;

import java.io.File;

public interface ReadFile<T> {
	 public abstract Data readFile(File fileToRead, int noOfHeaders);
}
