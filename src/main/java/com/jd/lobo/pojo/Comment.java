package com.jd.lobo.pojo;

import java.util.List;

/**
 * 评论pojo对象.
 */

public class Comment {
	public long id;

	public long skuId;

	public long spuId;

	public Long userId;

	public Long createTime;

	public String comment;

	public List<String> replies;

	public List<String> picPath;

	public int score;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSkuId() {
		return skuId;
	}

	public void setSkuId(long skuId) {
		this.skuId = skuId;
	}

	public long getSpuId() {
		return spuId;
	}

	public void setSpuId(long spuId) {
		this.spuId = spuId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<String> getReplies() {
		return replies;
	}

	public void setReplies(List<String> replies) {
		this.replies = replies;
	}

	public List<String> getPicPath() {
		return picPath;
	}

	public void setPicPath(List<String> picPath) {
		this.picPath = picPath;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}

