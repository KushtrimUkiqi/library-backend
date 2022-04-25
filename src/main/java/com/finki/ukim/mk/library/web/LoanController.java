package com.finki.ukim.mk.library.web;

import com.finki.ukim.mk.library.models.Book;
import com.finki.ukim.mk.library.models.Loan;
import com.finki.ukim.mk.library.services.BookServices;
import com.finki.ukim.mk.library.services.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoanController {

    private final LoanService loanService;
    private final BookServices bookServices;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    @PostMapping("loans/{loanID}/edit")
    public ResponseEntity<Loan> editUserLoans(@PathVariable Long loanID, @RequestParam Long bookID, @RequestParam String dateDue)
    {
        Book book = null;
        Loan loan = null;
        try{
            book = this.bookServices.findByID(bookID).orElseThrow(Exception::new);
            LocalDate localDateDue  = LocalDate.parse(dateDue, this.formatter);
            loan = this.loanService.editLoan(loanID,book,localDateDue);
        }
        catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(loan);
    }

    @DeleteMapping("loans/{loanID}/return")
    public ResponseEntity<String> returnLoan(@PathVariable Long loanID){
        try {
            this.loanService.removeLoan(loanID);
        }

        catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
}
