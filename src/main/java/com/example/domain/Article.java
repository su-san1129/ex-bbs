package com.example.domain;

import java.util.List;

/**
 * 記事のドメインクラス.
 * 
 * @author takahiro.suzuki
 *
 */
public class Article {
	
	/** 記事ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** 投稿内容 */
	private String content;
	/** コメントリスト */
	private List<Comment> commentList;
	
	
	
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", commentList=" + commentList + "]";
	}
	public List<Comment> getComments() {
		return commentList;
	}
	public void setComments(List<Comment> comments) {
		this.commentList = comments;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
