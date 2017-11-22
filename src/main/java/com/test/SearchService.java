package com.test;

import java.io.IOException;
import java.util.List;

public interface SearchService {

	final static String pathName= "C:\\Test Folder";
	final static String [] arrayWord = {"Hello", "World"};

	List<String> findFiles (String pathName, String [] arrayWord) throws IOException;

}
