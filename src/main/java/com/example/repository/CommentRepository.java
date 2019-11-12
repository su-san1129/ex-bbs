package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * コメントを操作するリポジトリ
 * @author takahiro.suzuki
 *
 */
@Repository
public class CommentRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	/**
	 * コメントオブジェクトを生成するRow_mapper
	 */
	private final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
        Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		return comment;
	};
	
	/**
	 * 記事を全件検索し、article_idに紐づいたcommentを付与する.
	 * 
	 * @return コメントのArrayListをセットした記事一覧
	 */
	public List<Article> articleFindAllWithComments() {
		List<Article> articles = articleRepository.findAll();
		for (Article article : articles) {
			List<Comment> articleComments = findByArticleId(article.getId());
			article.setComments(articleComments);
		}
		return articles;
	}
	
	/**
	 * コメントを新規投稿する.
	 * 
	 * @param comment 投稿するコメントオブジェクト
	 */
	public void insert(Comment comment) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		String insertSql = "INSERT INTO comments (name, content, article_id) VALUES (:name, :content, :articleId);";
		template.update(insertSql, param);
	}
	
	/**
	 * 記事のIDに紐づいたコメントリストを取得
	 * @param id 記事ID
	 * @return 記事のIDで取得したコメントリストを返す。ない場合は0件を返す。
	 */
	public List<Comment> findByArticleId(Integer id) {
		String sql = "SELECT id, name, content FROM comments WHERE article_id = :articleId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", id);
		List<Comment> articleComments = template.query(sql, param, COMMENT_ROW_MAPPER);
		return articleComments;
	}
	
	public void deleteById(Integer id) {
		String sql = "DELETE FROM commets WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	


}
