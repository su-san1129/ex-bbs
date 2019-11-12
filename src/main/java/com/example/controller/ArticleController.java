package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleService;
import com.example.service.CommentService;

/**
 * 記事を操作するコントローラー
 * 
 * @author takahiro.suzuki
 *
 */
@Controller
@RequestMapping("/")
public class ArticleController {
	
	@ModelAttribute
	private ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	
	@ModelAttribute
	private CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 掲示板の投稿画面を表示します.
	 * 
	 * @param model モデル
	 * @return 掲示板の投稿画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleService.articleFindAllWithComments();
		model.addAttribute("articleList", articleList);
		return "bbs-index";
	}
	
	/**
	 * 掲示板に新規投稿します.
	 * 
	 * @param form フォーム
	 * @param result エラーのバインディング
	 * @param article 記事のオブジェクト
	 * @param model モデル
	 * @return 掲示板の投稿ページへのリダイレクト
	 */
	@RequestMapping("/post")
	public String postBbs(
			@Validated ArticleForm form
			, BindingResult result
			, Article article
			, Model model) {
		if(result.hasErrors()) {
			return index(model);
		}
		BeanUtils.copyProperties(form, article);
		articleService.insertArticle(article);
		return "redirect:/";
	}
	
	/**
	 * リクエストパラメーターで受け取った記事を削除します.
	 * 
	 * @param id リクエストパラメーターで受け取ったID
	 * @return 削除後に投稿ページに戻る
	 */
	@RequestMapping("/delete")
	public String deleteArticle(Integer id) {
		System.out.println("debug:"+id);
		commentService.deleteByIdCommets(id);
		articleService.deleteByIdArticle(id);
		return "redirect:/";
	}
	
	
	/**
	 * 記事IDに紐づいたコメントを投稿します.
	 * 
	 * @param form フォーム
	 * @param result エラーを格納するオブジェクト
	 * @param comment 投稿したコメント
	 * @return コメントを投稿後、記事一覧ページに戻る
	 */
	@RequestMapping("/comment")
	public String articleComment(
			@Validated CommentForm form
			, BindingResult result
			, Comment comment) {
		BeanUtils.copyProperties(form, comment);
		commentService.insertComment(comment);
		return "redirect:/";
		
	}
	

}
