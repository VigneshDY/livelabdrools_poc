package com.cts.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cts.operator.Operator;

public class RuleFactTest {
	String str="Helloo";
	RuleFact rf=new RuleFact();
	
	@Test
	public void testGetAttribute() {
		
		rf.setAttribute(str);
		String str1=rf.getAttribute();
		assertEquals(str, str1);
	}
	
	
	@Test
	public void testGetValue() {
		
		rf.setValue(str);
		String str1=rf.getValue();
		assertEquals(str, str1);
	}
	
	

}
