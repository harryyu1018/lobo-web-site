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
import com.jd.lobo.util.JerseyUtils;
import com.jd.lobo.util.JsonUtils;

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
	
	public String getComments(long spuId, int page, int pageSize, long day) {
		
		String url = String.format("%s/lobo/day/comment", getServer());
		WebTarget wt = clientInstance.target(url);
		
		MultivaluedHashMap<String, String> formData = new MultivaluedHashMap<>();
		formData.add("spu_id", String.valueOf(spuId));
		formData.add("page", String.valueOf(page));
		formData.add("page_size", String.valueOf(pageSize));
		formData.add("day_time", String.valueOf(day));
		
		Response resp = wt.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.form(formData));	
		String json = JerseyUtils.readStringAndClose(resp, clientInstance);
		
		return json;
	}
	
	/**
	 * 获取指定商品的评论.
	 * @param skuId	商品id.
	 * @param page	分页id.
	 * @param pageSize	每个分页评论数量.
	 */
	public List<Comment> get(long spuId, int page, int pageSize) {
		
		String url = String.format("%s%s", getServer(), resourceUri);
		WebTarget wt = clientInstance.target(url);
		
		MultivaluedHashMap<String, String> formData = new MultivaluedHashMap<>();
		formData.add("spu_id", String.valueOf(spuId));
		formData.add("page", String.valueOf(page));
		formData.add("page_size", String.valueOf(pageSize));
		
		List<Comment> comments = null;
		
		try {
			Response resp = wt.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.form(formData));
			
			String json = JerseyUtils.readStringAndClose(resp, clientInstance);
			CommentGetResponse commentGetResponse = JsonUtils.readObject(json, CommentGetResponse.class);
			
			if (commentGetResponse != null && commentGetResponse.getCode() == 0) {
				comments = commentGetResponse.getData().getResult();
			}
			
		} catch (Exception e) {
			LOGGER.error("请求商品评价异常, skuId: {}, page: {}, pageSize: {}.", spuId, page, pageSize);
		}
		
		return comments;
	}
}
