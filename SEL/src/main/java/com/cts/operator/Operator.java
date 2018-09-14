package com.cts.operator;

public enum Operator {
	
	 EQ("equals"),GT("greater than"),LT("less than");
		private String action;

		public String getAction() {
			return this.action;
		}

		private Operator(String action) {
			this.action = action;
		}
		
	}
