package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.repository.CommentRepository;

/**
 * コメントを操作するサービスクラス.
 * 
 * @author takahiro.suzuki
 *
 */
@Service
@Transactional
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	/**
	 * 記事のIDに紐づいたコメントを投稿します.
	 * 
	 * @param comment 投稿するコメント
	 */
	public void insertComment(Comment comment) {
		commentRepository.insert(comment);
	}
	
	/**
	 * 記事にコメントを追加したオブジェクトを返します.
	 * 
	 * @return コメントを追加した記事オブジェクト
	 */
	public List<Article> findAllArticleWithComments(){
		return commentRepository.articleFindAllWithComments();
	}
	
	/**
	 * 指定したIDのコメントを削除します.
	 * 
	 * @param id 削除したいコメントのID
	 */
	public void deleteByIdCommets(Integer id) {
		commentRepository.deleteById(id);
	}

}
