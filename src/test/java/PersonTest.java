

import static org.junit.Assert.*;

import org.junit.Test;

import com.livelabdrools.model.Person;

public class PersonTest {
	String str="Hellooo";
	Person p=new Person();
	
	@Test
	public void testGetId() {
		
		p.setId(str);
		String str1=p.getId();
		assertEquals(str, str1);
	}

	@Test
	public void testGetFirstName() {
		
		p.setFirstName(str);
		String str1=p.getFirstName();
		assertEquals(str, str1);
	}

	@Test
	public void testGetLastName() {
		
		p.setLastName(str);
		String str1=p.getLastName();
		assertEquals(str, str1);
	}

	@Test
	public void testGetLocation() {
	
		p.setLocation(str);
		String str1=p.getLocation();
		assertEquals(str, str1);
	}

	@Test
	public void testGetTimeZone() {
		
		p.setTimeZone(str);
		String str1=p.getTimeZone();
		assertEquals(str, str1);
	}

}
