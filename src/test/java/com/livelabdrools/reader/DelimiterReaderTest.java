package com.livelabdrools.reader;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.livelabdrools.mapper.PersonMapperTest;
import org.junit.Test;

import com.livelabdrools.mapper.RuleMapperTest;
import com.livelabdrools.model.Person;

public class DelimiterReaderTest {
    String name = "personinput.psv";
    ClassLoader classLoader = new DelimiterReaderTest().getClass().getClassLoader();
    File fileName = new File(classLoader.getResource(name).getFile());
    @Test

    public void testGetHeader() {

        List<String[]> expected = new ArrayList<String[]>();
        String[] expectedArr = {"id","firstname","lastname","location","timezone"};
        expected.add(expectedArr);
        String[] expectedArr1 = {"abc","def","ghi","jkl","mno"};
        expected.add(expectedArr1);
        DelimiterReader delimiterReader = new DelimiterReader("\\|");
        List<String[]> actual = delimiterReader.getHeader(fileName, 1);
        assertEquals(expected.get(0), actual.get(0));
        assertFalse(expected.get(1).equals(actual.get(0)));
    }

    public void testGetData() {
        int index = 1;
        List<String[]> data = new ArrayList<String[]>();
        DelimiterReader delimiterReader = new DelimiterReader("\\|");
        String[] expectedArr = {"1","Sarath","ranganathan","Chennai",null};
        data.add(expectedArr);
        String[] expectedArr1 = {"abc","def","ghi","jkl",null};
        data.add(expectedArr1);
        assertEquals(data.get(0),delimiterReader.getHeader(fileName, 1).get(0));
        assertFalse(data.get(1).equals(delimiterReader.getData(fileName, 1).get(0)));
    }

}
