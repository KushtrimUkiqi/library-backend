package com.finki.ukim.mk.library.services;

import com.finki.ukim.mk.library.models.Author;
import com.finki.ukim.mk.library.models.Book;
import com.finki.ukim.mk.library.models.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookServices {
    public Optional<Book> findByID(Long ID);
    public List<Book> findAll();
    Page<Book> findAll(Pageable pageable);
    public Book addBook(String name,String imageUrl, Category category, Long authorID,int availableCopies) throws Exception;
    public Book editBookData(Long ID,String name,String imageUrl, Category category, Long authorID,int availableCopies) throws Exception;
    public void removeBook(Long ID) throws Exception;
    public void loanBook(Long bookID,Long userID) throws Exception;
    public void returnBook(Long bookID,Long userID) throws Exception;
    List<Book> getAllBooks();
    List<Book> findAllByNameContaining(String name);
    List<Book> findAllByAuthor(Author author);
}
