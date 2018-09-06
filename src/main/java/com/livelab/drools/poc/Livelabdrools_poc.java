package com.livelab.drools.poc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

 class Livelabdrools_poc {

public static void main(String args[]) throws IOException {


    BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\672845\\Desktop\\personinput.psv"));

    String line = "";

    while ((line = br.readLine()) != null) {

        System.out.println(line);
    }
}
}
