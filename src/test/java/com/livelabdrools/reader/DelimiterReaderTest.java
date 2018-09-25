package com.livelabdrools.reader;
import static org.junit.Assert.*;

import org.junit.Test;

import com.livelabdrools.model.Person;

public class DelimiterReaderTest {

	@Test
	public void testGetHeader() {
		String str[]=new String[100];
		str[0]="id";
		str[1]="firstname";
		str[2]="lastname";
		str[3]="location";
		str[4]="timezone";
		DelimiterReader dr=new DelimiterReader();
		assertArrayEquals(str, dr.getHeader());
			}
	
	@Test
	public void testGetData() {
		DelimiterReader dr=new DelimiterReader();
		String str[]=new String[100];
		String arr1[]=new String[100];
		
		str[0]="1|Sarath|ranganathan|Chennai";
		str[1]="2|Radha|tallu|Chennai";
		str[2]="30|Prasanna|r|Chennai";
		str[3]="50|Venkat|Nerella|NY";
		str[4]="60|Ankit|Sharma|toronto";
		str[5]="61|Rishab|Seth|Kolkata";
		int j=0;
		String s[]=new String[100];
		for (int i = 0; i < str.length; i++) {
			s=str[i].split("\\|");
			while(j<s.length)
			{
			arr1[j]=s[i];
			j++;
			}
		}
			assertArrayEquals(arr1, dr.getData());
		
	
		
		
	}
}
