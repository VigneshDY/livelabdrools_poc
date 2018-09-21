package com.livelabdrools.model;

import java.util.ArrayList;
import java.util.List;



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

	}

	public Rule() {

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

	public List<RuleFact> parseInputRuleFact() {
		return input;
	}

	public List<RuleFact> parseOutputRuleFact() {
		return output;
	}
}
