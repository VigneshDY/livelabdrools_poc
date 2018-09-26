

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.livelabdrools.model.RuleFact;
import com.livelabdrools.rule.SpringRuleEngine;

public class RuleEngineTest {

	@Test
	public void testParseInputRule() {
		String str="Hii matches Hhhh";
		SpringRuleEngine re=new SpringRuleEngine();
		List<RuleFact> inputList =new ArrayList<RuleFact>();
		RuleFact rf=new RuleFact();
		rf.setAttribute("Hii");
		rf.setOperator("EQ");
		rf.setValue("Hhhh");
		inputList.add(rf);
		String str1=re.parseInputRule(inputList);
		assertEquals(str, str1);
	}

}
