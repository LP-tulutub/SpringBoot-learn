package com.lp.test;


import com.lp.pojo.Book;
import com.lp.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataElasticsearchTest {

	@Autowired
	private BookRepository bookRepository;

	//新增
	@Test
	public void save(){
		Book book = new Book();
		book.setId(1);
		book.setBookName("西游记");
		book.setAuthor("吴承恩");

		this.bookRepository.index(book);
	}

	//查询
	@Test
	public void findByBookNameLikeTest(){
		for (Book book : bookRepository.findByBookNameLike("游")) {
			System.out.println(book);
		}
	}

}
