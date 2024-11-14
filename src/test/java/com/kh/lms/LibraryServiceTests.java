package com.kh.lms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryService libraryService;

    @Test
    void borrowBookTestForSuccess() {
        Book book = new Book(101,"Dune","Herbert",1000.0,true);
        when(bookRepository.findById(101)).thenReturn(Optional.of(book));
        assertEquals(libraryService.borrowBook(101),"Book borrowed successfully.");
        assertFalse(book.isAvailable());
        verify(bookRepository).save(book);

    }

    @Test
    void borrowBookTestForFailure() {
        Book book = new Book(101,"Dune","Herbert",1000.0,false);
        when(bookRepository.findById(101)).thenReturn(Optional.of(book));
        assertEquals(libraryService.borrowBook(101),"Book is already borrowed.");
        verify(bookRepository, never()).save(any(Book.class));

    }

    @Test
    void returnBookTest() {
        Book book = new Book(101,"Dune","Herbert",1000.0,true);
        when(bookRepository.findById(101)).thenReturn(Optional.of(book));
        assertEquals(libraryService.returnBook(101),"Book returned successfully.");
        assertTrue(book.isAvailable());
        verify(bookRepository).save(book);

    }
}
