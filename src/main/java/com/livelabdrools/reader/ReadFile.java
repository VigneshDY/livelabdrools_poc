package com.livelabdrools.reader;

import java.io.File;

import org.springframework.stereotype.Service;

import com.livelabdrools.model.Data;
@Service
public interface ReadFile<T> {
	 public abstract Data readFile(File fileToRead, int noOfHeaders);
}
