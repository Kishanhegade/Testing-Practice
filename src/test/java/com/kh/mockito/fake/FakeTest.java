package com.kh.mockito.fake;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FakeTest {

    @Test
    public void testFakeWithMockito() {
        BookRepository bookRepository = mock(BookRepository.class);
        BookService bookService = new BookService(bookRepository);
        Book book1 = new Book();
        book1.setBookId(101);
        book1.setTitle("Mockito in action");
        book1.setPrice(250);
        book1.setAuthor("Kishan");

        Book book2 = new Book();
        book2.setBookId(102);
        book2.setTitle("Junit in action");
        book2.setPrice(200);
        book2.setAuthor("Raman");

        Collection<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        when(bookRepository.findAll()).thenReturn(books);
        assertEquals(2, bookService.findNumberOfBooks());
    }
}
