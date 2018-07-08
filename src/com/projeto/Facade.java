package com.projeto;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import easyaccept.EasyAccept;

public class Facade {
	public static void main(String[] args) {
		args = new String[] { "com.projeto.Facade",
						      "acceptance_tests/use_case1_exception.txt",
							  "acceptance_tests/use_case1.txt",	  
							  "acceptance_tests/use_case2_exception.txt",
							  "acceptance_tests/use_case2.txt",
							  "acceptance_tests/use_case3_exception.txt",
							  "acceptance_tests/use_case3.txt",
							  "acceptance_tests/use_case4_exception.txt",
							  "acceptance_tests/use_case4.txt" };
		
		EasyAccept.main(args);
		
	}
}