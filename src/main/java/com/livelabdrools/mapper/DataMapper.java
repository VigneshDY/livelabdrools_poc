package com.livelabdrools.mapper;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class DataMapper<T> {
	
	@Autowired
	private File file;
	
	
	public abstract List<T> getData(String line);
}
