package com.jd.lobo.page;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.jd.lobo.AppContext;
import com.jd.lobo.util.HtmlUtils;


@Consumes(MediaType.TEXT_HTML)
public abstract class AbstractPage implements Page {

	@Context
	protected ServletContext servletContext;

	@Context
	protected HttpServletRequest httpServletRequest;

	@Context
	protected HttpServletResponse httpServletResponse;

	@Context
	protected UriInfo uriInfo;

	/**
	 * 所有不需要动态数据的页面均可直接返回模板对象
	 */
	@GET
	public Response get() {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("staticRoot", AppContext.getInstance().getSetting("site.static.root"));

		String html = HtmlUtils.render(getTemplateName(), values, null);
		return Response.ok(html, MediaType.TEXT_HTML_TYPE).build();
	}
}
