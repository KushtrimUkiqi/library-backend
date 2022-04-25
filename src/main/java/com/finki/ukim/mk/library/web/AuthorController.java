package com.finki.ukim.mk.library.web;

import com.finki.ukim.mk.library.models.Author;
import com.finki.ukim.mk.library.services.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("authors")
    public ResponseEntity<List<Author>> getAuthors()
    {
        List<Author> list = null;
        try {
            list = this.authorService.findAll();
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("authors/{ID}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long ID)
    {
        Author author = null;
        try{
            author = this.authorService.findByID(ID);
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(author);
    }

    @PostMapping("authors/add")
    public ResponseEntity<Author> addAuthor(@RequestParam String name,@RequestParam String surname,@RequestParam Long countryID)
    {
        Author author = null;
        try {
            author = this.authorService.addAuthor(name,surname,countryID);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(author);
    }

    @PutMapping("authors/edit/{ID}")
    public ResponseEntity<Author> editAuthor(@PathVariable Long ID, @RequestParam String name,@RequestParam String surname,@RequestParam Long countryID)
    {
        Author author = null;
        try {
            author = this.authorService.updateAuthor(ID,name,surname,countryID);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("authors/delete/{ID}")
    public ResponseEntity<String> removeAuthor(@PathVariable Long ID)
    {
        try
        {
            this.authorService.removeAuthor(ID);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.accepted().build();
    }
}
