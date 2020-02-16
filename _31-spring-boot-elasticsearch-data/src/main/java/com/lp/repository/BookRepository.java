package com.lp.repository;

import com.lp.pojo.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book,Integer> {

    List<Book> findByBookNameLike(String bookName);

}
