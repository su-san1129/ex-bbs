package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	 * 指定したIDのコメントを削除します.
	 * 
	 * @param id 削除したいコメントのID
	 */
	public void deleteByIdCommets(Integer id) {
		commentRepository.deleteById(id);
	}

}
