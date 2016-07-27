package com.jd.lobo.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

public abstract class AbstractService {

	@Context
	protected ServletContext servletContext;
	
	@Context
	protected HttpServletRequest httpServletRequest;
	
	@Context
	protected HttpServletResponse httpServletResponse;
	
	@Context
	protected UriInfo uriInfo;
}
