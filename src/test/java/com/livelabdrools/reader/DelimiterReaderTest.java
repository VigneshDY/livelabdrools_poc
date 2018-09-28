package com.livelabdrools.reader;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.livelabdrools.model.Person;

public class DelimiterReaderTest {
@Test
	public void testGetHeader()
	{
		
	 List<String[]> expected = new ArrayList<String[]>();
     String[] expectedArr  = new String[5];
     expectedArr[0]="id";
     expectedArr[1]="firstname";
     expectedArr[2]="lastname";
     expectedArr[3]="location";
     expectedArr[4]="timezone";
     expected.add(expectedArr);
     DelimiterReader delimiterReader=new DelimiterReader("\\|");
     File filename=new File("C:\\Users\\674448\\Desktop\\personinput.psv");
     List<String[]> actual=delimiterReader.getHeader(filename,1);
     assertEquals(expected.get(0), actual.get(0));
	}
	public void testGetData()
		{
			int index = 1;
			List<String[]> data = new ArrayList<String[]>();
			DelimiterReader delimiterReader=new DelimiterReader("\\|");
			try {
				BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\674448\\Desktop\\personinput.psv"));
				String line = "";
				for(int i = 0; i < 1; i++, br.readLine());
				while ((line = br.readLine()) != null) {
					index++;
					data.add(line.split("\\|"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			assertEquals(data.get(0),delimiterReader.getHeader(new File("C:\\Users\\674448\\Desktop\\personinput.psv"), 1).get(0) );
		}

		
	}

