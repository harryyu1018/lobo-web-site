package com.jd.lobo.pojo;

import java.util.List;

/**
 * 评论pojo对象.
 */

public class Comment {
	public long id;

	public Long sku_id;

	public Long spu_id;

	public Long user_id;

	public Long create_time;

	public String comment;

	public List<String> replies;

	public List<String> pic_path;

	public Integer score;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getSku_id() {
		return sku_id;
	}

	public void setSku_id(Long sku_id) {
		this.sku_id = sku_id;
	}

	public Long getSpu_id() {
		return spu_id;
	}

	public void setSpu_id(Long spu_id) {
		this.spu_id = spu_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
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

	public List<String> getPic_path() {
		return pic_path;
	}

	public void setPic_path(List<String> pic_path) {
		this.pic_path = pic_path;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}

