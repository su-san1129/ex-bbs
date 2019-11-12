package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * 記事を操作するリポジトリ.
 * 
 * @author takahiro.suzuki
 *
 */
@Repository
public class ArticleRepository {


	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 記事のオブジェクトを生成するrow_mapper.
	 * 
	 */
	private final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		return article;
	};
	
	
	/*
	 * ResultSetExtractorを使用すると、検索結果まるごとのリザルトセットをもらうことができる。
	 * 
	 * 
	 */
	private final ResultSetExtractor<List<Article>> ARTICLE_EXTRA_SET = (rs) -> {

		Article article = new Article();
		Comment comment = new Comment();
		List<Comment> commentList = new ArrayList<>();
		List<Article> articleList = new ArrayList<>();
		while(rs.next()) {
			if(rs.previous()) {
				rs.previous();
				Integer previousId = rs.getInt("id");
				rs.next();
				if( previousId == rs.getInt("id") ) {
					comment.setId(rs.getInt("com_id"));
					comment.setName(rs.getString("com_name"));
					comment.setContent(rs.getString("com_content"));
					comment.setArticleId(rs.getInt("article_id"));
					commentList.add(comment);
					
				} else {
					article.setId(rs.getInt("id"));
					article.setName(rs.getString("name"));
					article.setContent(rs.getString("content"));
					comment.setId(rs.getInt("com_id"));
					comment.setName(rs.getString("com_name"));
					comment.setContent(rs.getString("com_content"));
					comment.setArticleId(rs.getInt("article_id"));
					commentList.add(comment);
					article.setComments(commentList);			
					articleList.add(article);
				}
			}
			article.setId(rs.getInt("id"));
			article.setName(rs.getString("name"));
			article.setContent(rs.getString("content"));
			comment.setId(rs.getInt("com_id"));
			comment.setName(rs.getString("com_name"));
			comment.setContent(rs.getString("com_content"));
			comment.setArticleId(rs.getInt("article_id"));
			commentList.add(comment);
			article.setComments(commentList);
			articleList.add(article);
		}
		return articleList;
	};

	/**
	 * 記事を全件取得する.
	 * 
	 * idの降順で表示される。
	 * 
	 * @return 全件取得された記事。もしなければ0件を返す。
	 */
	public List<Article> findAll() {
		String sql = "SELECT id, name, content FROM articles ORDER BY id DESC;";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;
	}

	/**
	 * 記事を投稿する.
	 * 
	 * @param article 新規作成する記事
	 */
	public void insert(Article article) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		String insertSql = "INSERT INTO articles (name, content) VALUES (:name, :content);";
		template.update(insertSql, param);
	}

	/**
	 * 記事を削除する.
	 * 
	 * @param id 記事ID
	 */
	public void deleteById(Integer id) {
		String sql = "DELETE FROM articles WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

	public List<Article> findAll2() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.id AS id, a.name AS name, a.content AS content, com.id AS com_id, ");
		sql.append("com.name AS com_name, com.content AS com_content, article_id FROM articles a ");
		sql.append("INNER JOIN comments com ON a.id = com.article_id;");
		List<Article> articles = template.query(sql.toString(), ARTICLE_EXTRA_SET);
		return articles;

	}

}
