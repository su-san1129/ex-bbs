package com.example.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Article;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleRepositoryTest {
	
	@Autowired
	private ArticleRepository articleRepository;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("insertのテストを開始します。");
		
		Article article = new Article();
		article.setName("鈴木貴大");
		article.setContent("掲示板サンプルです。");
		articleRepository.insert(article);
		
		System.out.println("insertのテストを終了します。");
	}


	
	@Test
	public void insertTest() {
	}
	
	@Test
	public void deleteTest() {
	}
	@Test
	public void findAllTest() {
		System.out.println("findAllのテストを開始します。");
		
		List<Article> articleList = articleRepository.findAll();
		assertThat("名前が一致しません。", articleList.size(), is(2));
		assertThat("名前が一致しません。", articleList.get(1).getName(), is("伊賀"));
		
		System.out.println("findAllのテストを終了します。");
	}
	@After
	public void tearDown() throws Exception {
		System.out.println("deleteのテストを開始します。");
		articleRepository.deleteById(6);
		System.out.println("deleteのテストを終了します。");
	}

}
