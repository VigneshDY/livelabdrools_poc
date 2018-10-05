package com.livelabdrools.mapper;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.livelabdrools.reader.DelimiterReader;
import com.livelabdrools.reader.ExcelReader;
import com.livelabdrools.reader.ReadFile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

public abstract class DataMapper {
	
	protected ReadFile readFile;
	
	protected Map<String, ReadFile> fileReaders;
	
	@PostConstruct
	public void init() {
		fileReaders = new HashMap<String, ReadFile>();
		fileReaders.put("xlsx",new ExcelReader());
		fileReaders.put("csv",new DelimiterReader(","));
		fileReaders.put("psv",new DelimiterReader("\\|"));
		fileReaders.put("tsv",new DelimiterReader("\t"));
	}
	
	public abstract List getData(File file);
}