package com.nikollenko.library.dao;

import com.nikollenko.library.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book,Long> {
    List<Book> findAllByBusy(boolean busy);
}

