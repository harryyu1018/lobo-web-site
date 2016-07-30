package com.jd.lobo.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jd.lobo.AppContext;
import com.jd.lobo.client.ItemCommentClient;
import com.jd.lobo.pojo.Comment;
import com.jd.lobo.util.HtmlUtils;

@Path("/item/calendar/{id}")
public class ItemCommentsCalendarPage extends AbstractPage {

	@PathParam("id")
	private Long id;
	
	@Override
	public String getTemplateName() {
		return "item_comments_calendar";
	}
	
	@Override
	public Response get() {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("staticRoot", AppContext.getInstance().getSetting("site.static.root"));
		
		String html = HtmlUtils.render(getTemplateName(), values, null);
		return Response.ok(html, MediaType.TEXT_HTML_TYPE).build();
	}
	
	@GET
	@Path("/day")
	public Response getCommentsWithinDay(
			@QueryParam("page") Integer page, 
			@QueryParam("pageSize") Integer pageSize,
			@QueryParam("day") Long day) {
		
		ItemCommentClient client = new ItemCommentClient();
		String json = client.getComments(id, page, pageSize, day);
		return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
	}

}
