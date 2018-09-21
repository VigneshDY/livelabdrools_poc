package com.livelabdrools.mapper;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.livelabdrools.reader.ReadFile;

public abstract class DataMapper {
	
	private Map<String, ReadFile> fileReaders;
	
	static {
//		fileReaders.
	}
	
	
	
	public abstract List<Object> getData(List<String[]> header,List<String[]> data);
	
}

