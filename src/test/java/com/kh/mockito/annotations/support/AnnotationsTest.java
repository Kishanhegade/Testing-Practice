package com.kh.mockito.annotations.support;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnnotationsTest {


    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void demoCreateMocksUsingAnnotations() {
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

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        when(bookRepository.findNewBooks(7)).thenReturn(books);

        List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10,7);
        assertEquals(2, newBooksWithAppliedDiscount.size());
        assertEquals(450, newBooksWithAppliedDiscount.get(0).getPrice());
        assertEquals(360, newBooksWithAppliedDiscount.get(1).getPrice());
    }
}
