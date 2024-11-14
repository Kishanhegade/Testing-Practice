package com.kh.lms;

public class LibraryService {

    private BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String borrowBook(int bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        if (!book.isAvailable()) {
            return "Book is already borrowed.";
        }
        book.setAvailable(false);
        bookRepository.save(book);
        return "Book borrowed successfully.";
    }
    String returnBook(int bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        book.setAvailable(true);
        bookRepository.save(book);
        return "Book returned successfully.";
    }
}
