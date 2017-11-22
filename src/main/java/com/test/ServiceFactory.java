package com.test;

public class ServiceFactory {
	SearchService getSearchService(){
		return new SearchServiceImpl();
	}
}
