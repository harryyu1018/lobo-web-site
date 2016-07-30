package com.jd.lobo.page;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jd.lobo.AppContext;
import com.jd.lobo.util.HtmlUtils;

@Path("/item/{id}/comment")
public class CommentPage extends AbstractPage {

	@Override
	public String getTemplateName() {
		return "comment";
	}
	
	@Override
	public Response get() {
	
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("staticRoot", AppContext.getInstance().getSetting("site.static.root"));

		String html = HtmlUtils.render(getTemplateName(), values, null);
		return Response.ok(html, MediaType.TEXT_HTML_TYPE).build();
	}

}
