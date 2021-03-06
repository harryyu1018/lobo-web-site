package com.jd.lobo.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
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

@Path("/item/{id}")
public class ItemPage {

	@PathParam("id")
	private Long id;
	
	public String getTemplateName() {
		return "item";
	}
	
	@GET
	public Response get(@QueryParam("p") @DefaultValue("1") int p) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("staticRoot", AppContext.getInstance().getSetting("site.static.root"));
		
		ItemCommentClient client = new ItemCommentClient();
		List<Comment> comments = client.get(id, p, 10);
		
		String[] styles = { "粉色", "蓝色", "浅黄色", "绿色"};
		String[] sizes = { "XS", "S", "M", "L"};
		String[] levels = { "钻石会员", "金牌会员", "银牌会员", "铜牌会员" };
		
		for (Comment c : comments) {
			Long uid = c.getUser_id();
			int mod = (int)(uid % 4);
			
			c.setStyle(styles[mod]);
			c.setSize(sizes[mod]);
			c.setLevel(levels[mod]);
		}
		
		values.put("comments", comments);
		
		values.put("page", p);
		
		String html = HtmlUtils.render(getTemplateName(), values, null);
		return Response.ok(html, MediaType.TEXT_HTML_TYPE).build();
	}
	
	
}
