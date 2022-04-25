package com.finki.ukim.mk.library.repository;

import com.finki.ukim.mk.library.models.Author;
import com.finki.ukim.mk.library.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>
{
    List<Book> findAll();
    Page<Book> findAll(Pageable pageable);
    List<Book> findAllByNameContaining(String name);
    List<Book> findAllByAuthor(Author author);
    void deleteByID(Long ID);
}
