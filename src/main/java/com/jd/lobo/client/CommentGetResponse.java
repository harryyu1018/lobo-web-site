package com.jd.lobo.client;

import java.util.List;

import com.jd.lobo.pojo.Comment;
import com.jd.lobo.util.JsonUtils;

/**
 * 调用商品评价服务，返回结果
 */

public class CommentGetResponse {
	
	private Integer code;			// 服务请求返回码
	private String message;			// 服务请求message
	private List<Comment> data;		// 评价结果列表
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<Comment> getData() {
		return data;
	}
	
	public void setData(List<Comment> data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return JsonUtils.writeString(this);
	}
	
}
