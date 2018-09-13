package com.cts.model;

import java.util.ArrayList;
import java.util.List;

import com.cts.service.RuleEngine;
import com.cts.service.RuleFact;

public class Rule {

	private List<RuleFact> input = new ArrayList<RuleFact>();

	private List<RuleFact> output = new ArrayList<RuleFact>();
	

	public Rule(List<RuleFact> ruleFacts) {
		for (RuleFact ruleFact : ruleFacts) {
			if (null != ruleFact.getOperator()) {
				input.add(ruleFact);
			} else {
				output.add(ruleFact);
			}
		}
		/*RuleEngine ruleEngine = new RuleEngine(input);*/
		
	}

	public Rule() {
		// TODO Auto-generated constructor stub
	}

	public List<RuleFact> getInput() {
		return input;
	}

	public void setInput(List<RuleFact> input) {
		this.input = input;
	}

	public List<RuleFact> getOutput() {
		return output;
	}

	public void setOutput(List<RuleFact> output) {
		this.output = output;
	}

	@Override
	public String toString() {
		return "Rule [input=" + input + ", output=" + output + "]";
	}
public List<RuleFact> parseInputRuleFact(){
	return input;
}
public List<RuleFact> parseOutputRuleFact(){
	return output;
}
	
}
