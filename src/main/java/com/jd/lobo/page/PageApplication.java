package com.jd.lobo.page;

import org.glassfish.jersey.server.ResourceConfig;

public class PageApplication extends ResourceConfig {
	
	public PageApplication() {
		packages("com.jd.lobo.page");
	}
}
