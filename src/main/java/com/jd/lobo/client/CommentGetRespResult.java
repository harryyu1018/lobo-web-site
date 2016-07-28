package com.jd.lobo.client;

import java.util.List;

import com.jd.lobo.pojo.Comment;
import com.jd.lobo.util.JsonUtils;

public class CommentGetRespResult {

	private Long spuId;
	private Integer number;
	private List<Comment> result;
	
	public Long getSpuId() {
		return spuId;
	}
	
	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public List<Comment> getResult() {
		return result;
	}
	
	public void setResult(List<Comment> result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return JsonUtils.writeString(this);
	}
}
