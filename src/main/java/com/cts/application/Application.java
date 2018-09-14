package com.cts.application;

import com.cts.service.Service;

public class Application {

	public static void main(String[] args) {

		Service s = new Service();
		s.readExcel();
		s.readPsv();

	}

}
