package com.example.domain;

/**
 * コメントのドメインクラス.
 * 
 * @author takahiro.suzuki
 *
 */
public class Comment {
	
	/** コメントID */
	private Integer id;
	/** 名前 */
	private String name;
	/** 投稿内容 */
	private String content;
	/** 記事ID */
	private Integer articleId;
	
	
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + "]";
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
