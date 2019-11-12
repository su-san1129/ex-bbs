package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

@Service
@Transactional
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	/**
	 * 記事を全件検索します.
	 * 
	 * @return 全件取得した記事。一件もない場合は、0件を返す。
	 */
	public List<Article> findAllArticle(){
		return articleRepository.findAll();
	}
	
	/**
	 * 記事を新規投稿します.
	 * 
	 * @param article 投稿する記事のオブジェクト
	 */
	public void insertArticle(Article article) {
		articleRepository.insert(article);
		System.out.println("1件記事を新規投稿しました。");
	}
	
	/**
	 * 指定したIDの記事を削除する.
	 * 
	 * @param id 指定した記事のID
	 */
	public void deleteByIdArticle(Integer id) {
		articleRepository.deleteById(id);
		System.out.println("ID:" + id + "の削除が完了しました。");
	}
	
	/**
	 * 記事を全件検索し、article_idに紐づいたcommentを付与する.
	 * 
	 * @return コメントのArrayListをセットした記事一覧
	 */
	public List<Article> articleFindAllWithComments() {
		List<Article> articles = articleRepository.findAll();
		for (Article article : articles) {
			List<Comment> articleComments = commentRepository.findByArticleId(article.getId());
			article.setComments(articleComments);
		}
		return articles;
	}
	
	public List<Article> articleFindAll(){
		return articleRepository.findAll2();
	}

}
