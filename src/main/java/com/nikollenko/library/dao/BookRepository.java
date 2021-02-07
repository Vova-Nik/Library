package com.nikollenko.library.dao;

import com.nikollenko.library.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {

}

