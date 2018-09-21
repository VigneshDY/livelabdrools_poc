package com.livelabdrools.model;

public class RuleFact {
private String attribute;
private Operator operator;
private String value;
public String getAttribute() {
	return attribute;
}
public Operator getOperator() {
	return operator;
}
public void setOperator(Operator operator) {
	this.operator = operator;
}
public void setOperator(String operator) {
	this.operator = null == operator ? null : Operator.valueOf(operator);
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public void setAttribute(String attribute) {
	this.attribute = attribute;
}
@Override
public String toString() {
	return "RuleFact [attribute=" + attribute + ", operator=" + operator + ", value=" + value + "]";
}

}
