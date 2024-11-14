package com.kh.mockito.fake;


import com.kh.mockito.dummy.EmailService;

public class BookService {

    private final BookRepository bookRepository;
    private EmailService emailService;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public int findNumberOfBooks() {
        return bookRepository.findAll().size();
    }
}
