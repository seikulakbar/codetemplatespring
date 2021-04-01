package com.example.demo;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class DemoApplicationTest {
	public static void main(String argv[]) {		
		File theDir = new File("C:\\Projects\\Github_Project");
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
	}
}