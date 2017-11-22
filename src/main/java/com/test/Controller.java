package com.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

@RestController
public class Controller {

    @RequestMapping("/search")
    public List search(@RequestParam(value="path") String path, @RequestParam(value="searchWords") String [] searchWords) {
        try {
			System.out.println("Path to be searched is: " + path);
			System.out.println("Search words are: " + Arrays.asList(searchWords));

			ServiceFactory sFactory = new ServiceFactory();
			SearchService service = sFactory.getSearchService();
			System.out.println("Beginning search...");
         	List<String> result = service.findFiles(path, searchWords);
         	System.out.println("Result obtained is: " + result);
         	return result;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
}