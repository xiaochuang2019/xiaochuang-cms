package com.xiaochuang.cms.domain;

import java.util.Date;

public class Comment {

	private Integer id;
	
	/**所属文章**/
	private Article article;
	
	/**作者**/
	private User author;
	
	/**评论内容**/
	private String content;
	
	/**评论时间**/
	private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", article=" + article + ", author=" + author + ", content=" + content
				+ ", created=" + created + "]";
	}
	
	
	
}
