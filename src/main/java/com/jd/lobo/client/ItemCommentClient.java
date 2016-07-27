package com.jd.lobo.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.lobo.AppContext;
import com.jd.lobo.pojo.Comment;

/**
 * 调用商品评价数据的客户端.
 */

public class ItemCommentClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemCommentClient.class);
	
	private static final String resourceUri = "/lobo/comment";
	
	protected static Client clientInstance = ClientBuilder.newClient();
	
	protected String getServer() {
		return AppContext.getInstance().getSetting("comment.server");
	}
	
	/**
	 * 获取指定商品的评论.
	 * @param skuId	商品id.
	 * @param page	分页id.
	 * @param pageSize	每个分页评论数量.
	 */
	public List<Comment> get(long skuId, int page, int pageSize) {
		
		String url = String.format("%s", getServer(), resourceUri);
		WebTarget wt = clientInstance.target(url);
		
		MultivaluedHashMap<String, String> formData = new MultivaluedHashMap<>();
		formData.add("sku_id", String.valueOf(skuId));
		formData.add("page", String.valueOf(page));
		formData.add("page_size", String.valueOf(pageSize));
		
		List<Comment> comments = null;
		
		try {
			Response resp = wt.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.form(formData));
			CommentGetResponse commentGetResponse = resp.readEntity(CommentGetResponse.class);
			
			if (commentGetResponse != null && commentGetResponse.getCode() == 0) {
				comments = commentGetResponse.getData();
			}
			
		} catch (Exception e) {
			LOGGER.error("请求商品评价异常, skuId: {}, page: {}, pageSize: {}.", skuId, page, pageSize);
		}
		
		return comments;
	}
}
