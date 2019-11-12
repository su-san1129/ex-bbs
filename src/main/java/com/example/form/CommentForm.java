package com.example.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

/**
 * コメントのフォーム.
 * 
 * @author takahiro.suzuki
 *
 */
public class CommentForm {
	
	/** 記事ID */
	private Integer id;
	/** 名前 */
	@NotBlank(message="名前を入力してください")
	@Length(message="名前は1文字以上50文字以内で入力してください。", min=1, max=50)
	private String name;
	/** 投稿内容 */
	@NotBlank(message="投稿内容を入力してください")
	private String content;
	/** 記事ID */
	private Integer articleId;
	
	
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
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
	

}
