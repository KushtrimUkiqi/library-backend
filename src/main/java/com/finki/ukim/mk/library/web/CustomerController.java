package com.finki.ukim.mk.library.web;

import com.finki.ukim.mk.library.models.Book;
import com.finki.ukim.mk.library.models.Customer;
import com.finki.ukim.mk.library.models.Loan;
import com.finki.ukim.mk.library.services.BookServices;
import com.finki.ukim.mk.library.services.LoanService;
import com.finki.ukim.mk.library.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;
    private final BookServices bookServices;
    private final LoanService loanService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    @GetMapping("users")
    public ResponseEntity<List<Customer>> getAll()
    {
        return ResponseEntity.ok(this.customerService.findAll());
    }

    @PostMapping("users/add")
    public ResponseEntity<Customer> addUser(@RequestParam String name, @RequestParam String surname){
        Customer customer = null;
        try {
            customer = this.customerService.addUser(name,surname);
        }
        catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(customer);
    }

    @PatchMapping("users/{ID}/add")
    public ResponseEntity<Customer> editUserData(@PathVariable Long ID, @RequestParam String name, @RequestParam String surname){
        Customer customer = null;
        try {
            customer = this.customerService.updateUser(ID,name,surname);
        }
        catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("users/{ID}/delete")
    public ResponseEntity<String> removeUser(@PathVariable Long ID)
    {
        try
        {
            this.customerService.removeUser(ID);
        }

        catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("users/{ID}/loans")
    public ResponseEntity<List<Loan>> getUserLoans(@PathVariable Long ID)
    {
        List<Loan> loans = this.customerService.getUserLoans(ID);
        return ResponseEntity.ok(loans);
    }

    @PostMapping("users/{userID}/loans/add")
    public ResponseEntity<Loan> addUserLoans(@PathVariable Long userID,@RequestParam Long bookID,@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateDue)
    {
        Customer customer = null;
        Book book = null;
        Loan loan = null;

        LocalDate localDateDue = LocalDate.of(dateDue.getYear(),dateDue.getMonth(),dateDue.getDate());


        try{
            customer = this.customerService.findByID(userID);
            book = this.bookServices.findByID(bookID).orElseThrow(Exception::new);
            loan = this.loanService.createLoan(book, customer,localDateDue);
        }
        catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(loan);
    }

}
