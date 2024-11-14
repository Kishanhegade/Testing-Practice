package com.kh.mockito.spy;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.spy;

public class SpyTest {

    @Test
    void demoSpyWithMockito() {
        BookRepository bookRepository = spy(BookRepository.class);
        BookService bookService = new BookService(bookRepository);

        Book book1 = new Book();
        Book book2 = new Book();

        book1.setBookId(101);
        book1.setTitle("Mockito in action");
        book1.setPrice(500);
        book1.setAuthor("Kishan");

        book2.setBookId(102);
        book2.setTitle("Junit in action");
        book2.setPrice(400);
        book2.setAuthor("Raman");

        bookService.addBook(book1);
        bookService.addBook(book2);

        Mockito.verify(bookRepository, Mockito.times(1)).save(book2);
        Mockito.verify(bookRepository, Mockito.times(0)).save(book1);


    }
}
