package com.jd.lobo.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jd.lobo.AppContext;
import com.jd.lobo.client.ItemCommentClient;
import com.jd.lobo.pojo.Comment;
import com.jd.lobo.util.HtmlUtils;

@Path("/item/{id}")
public class ItemPage extends AbstractPage {

	@PathParam("id")
	private Long id;
	
	@Override
	public String getTemplateName() {
		return "item";
	}
	
	@Override
	public Response get() {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("staticRoot", AppContext.getInstance().getSetting("site.static.root"));
		
		ItemCommentClient client = new ItemCommentClient();
		List<Comment> comments = client.get(id, 0, 10);
		
		String html = HtmlUtils.render(getTemplateName(), values, null);
		return Response.ok(html, MediaType.TEXT_HTML_TYPE).build();
	}
	
	
}
