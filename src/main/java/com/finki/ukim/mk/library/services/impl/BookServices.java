package com.finki.ukim.mk.library.services.impl;

import com.finki.ukim.mk.library.models.Author;
import com.finki.ukim.mk.library.models.Book;
import com.finki.ukim.mk.library.models.enums.Category;
import com.finki.ukim.mk.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServices implements com.finki.ukim.mk.library.services.BookServices {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Override
    public Optional<Book> findByID(Long ID) {
        return this.bookRepository.findById(ID);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Book addBook(String name,String imageUrl, Category category, Long authorID,int availableCopies) throws Exception {
        Author author = this.authorService.findByID(authorID);
        Book book = new Book(name,imageUrl,category, author,availableCopies);
        return this.bookRepository.save(book);
    }

    @Override
    public Book editBookData(Long ID,String name,String imageUrl, Category category, Long authorID,int availableCopies) throws Exception {
        Book book = this.bookRepository.findById(ID).orElseThrow(Exception::new);
        Author author = (book.getAuthor().getID() != authorID) ? this.authorService.findByID(authorID) : book.getAuthor();
        book.setName(name);
        book.setImageUrl(imageUrl);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        return this.bookRepository.save(book);
    }

    @Override
    public void removeBook(Long ID) throws Exception {
        Book book = this.bookRepository.findById(ID).orElseThrow(Exception::new);
        this.bookRepository.delete(book);
    }

    @Override
    public void loanBook(Long bookID, Long userID) throws Exception {
        Book book = this.bookRepository.findById(bookID).orElseThrow(Exception::new);
        if(book.getAvailableCopies() == 0){
            throw new Exception("No available copies");
        }
        //:TODO IMPLEMENT LOANING FROM USERS
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        this.bookRepository.save(book);
    }

    @Override
    public void returnBook(Long bookID, Long userID) throws Exception {
        Book book = this.bookRepository.findById(bookID).orElseThrow(Exception::new);
        if(book.getAvailableCopies() == 0){
            throw new Exception("No available copies");
        }
        //:TODO IMPLEMENT RETURNING FROM USERS
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        this.bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> findAllByNameContaining(String name) {
        return this.bookRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Book> findAllByAuthor(Author author) {
        return this.bookRepository.findAllByAuthor(author);
    }
}
