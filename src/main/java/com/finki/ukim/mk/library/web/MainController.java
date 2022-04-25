package com.finki.ukim.mk.library.web;

import com.finki.ukim.mk.library.models.enums.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {
    @GetMapping("categories")
    public ResponseEntity<List<String>> categoryList()
    {
        return ResponseEntity.ok(Arrays.stream(Category.values()).map(category -> category.name()).collect(Collectors.toList()));
    }
}
