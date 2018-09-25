package com.livelabdrools.reader;

import com.livelabdrools.mapper.DataMapper;
import com.livelabdrools.model.Data;
import org.springframework.stereotype.Service;

import java.io.File;
@Service
public interface ReadFile<T> {
	 public abstract Data readFile(File fileToRead, int noOfHeaders);
}
