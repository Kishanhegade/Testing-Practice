package com.kh.mockito.stub;

import java.util.List;

public interface BookRepository {
    List<Book> findNewBooks(int days);

}
