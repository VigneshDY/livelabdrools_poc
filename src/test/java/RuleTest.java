

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cts.service.RuleFact;
import com.livelabdrools.model.Rule;

public class RuleTest {

	List<RuleFact> li = new ArrayList<RuleFact>();
	Rule r=new Rule();
	
	@Test
	public void testGetInput() {
		
		r.setInput(li);		
		assertEquals(li, r.getInput());
	}

	@Test
	public void testGetOutput() {
		r.setOutput(li);		
		assertEquals(li, r.getOutput());	
	}

	@Test
	public void testParseInputRuleFact() {
			
		assertEquals(li, r.parseInputRuleFact());
	}

	@Test
	public void testParseOutputRuleFact() {
		assertEquals(li, r.parseOutputRuleFact());
	}

}
