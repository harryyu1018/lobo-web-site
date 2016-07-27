package com.jd.lobo.service;

import org.glassfish.jersey.server.ResourceConfig;

public class ServiceApplication extends ResourceConfig {

	public ServiceApplication() {
		packages("com.jd.lobo.service");
	}
}
