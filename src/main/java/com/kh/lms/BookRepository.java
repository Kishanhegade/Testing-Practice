package com.kh.lms;

import java.util.Optional;

public interface BookRepository {

    Optional<Book> findById(int bookId);
    Book save(Book book);
}
