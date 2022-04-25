package com.finki.ukim.mk.library.web;

import com.finki.ukim.mk.library.models.Book;
import com.finki.ukim.mk.library.models.dto.BookDto;
import com.finki.ukim.mk.library.models.enums.Category;
import com.finki.ukim.mk.library.services.BookServices;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private final BookServices bookServices;

    @GetMapping(path = "book")
    public List<Book> giveAllProducts() {
        return bookServices.findAll();
    }

    @GetMapping(path = "books")
    public Page<Book> giveAllProductsWithPagination(Pageable pageable)
    {
        return bookServices.findAll(pageable);
    }

    @GetMapping("books/{ID}")
    public  ResponseEntity<Book> getBook(@PathVariable Long ID)
    {
        Book book = null;
        try
        {
            book = this.bookServices.findByID(ID).orElseThrow(Exception::new);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(book);
    }

    @PostMapping("books/add")
    public ResponseEntity<Book> addNewBook(@RequestBody BookDto bookDto) throws Exception {
        return ResponseEntity.ok().body(this.bookServices.addBook(bookDto.getName(), bookDto.getImageUrl(),bookDto.getCategory(),bookDto.getAuthorID(),bookDto.getAvailableCopies()));
    }

    @PutMapping("books/edit/{ID}")
    public ResponseEntity<Book> editBook(@PathVariable Long ID,@RequestParam String name,@RequestParam String imageUrl,@RequestParam Category category,@RequestParam Long authorID,@RequestParam int availableCopies){
        Book book;
        try
        {
            book = this.bookServices.editBookData(ID,name,imageUrl,category,authorID,availableCopies);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("books/remove/{id}")
    public ResponseEntity<String> removeBook(@PathVariable Long ID)
    {
        try
        {
            this.bookServices.removeBook(ID);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.accepted().build();
    }

}
