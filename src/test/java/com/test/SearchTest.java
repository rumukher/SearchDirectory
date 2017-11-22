package com.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class SearchTest {

	@Test
	public void testSearch() {
		String [] searchWords = {"Hello","World"};
		try {
			ServiceFactory sFactory = new ServiceFactory();
			SearchService service = sFactory.getSearchService();
         	List<String> result = service.findFiles("C:\\Test Folder", searchWords);
         	assertFalse(result.isEmpty());
			System.out.println(" Tested Okay ");
		}
		catch (IOException e) {
			System.out.println(" Error ");
			e.printStackTrace();
		}
	}

}