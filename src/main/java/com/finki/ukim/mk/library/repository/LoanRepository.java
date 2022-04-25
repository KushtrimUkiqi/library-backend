package com.finki.ukim.mk.library.repository;

import com.finki.ukim.mk.library.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan,Long> {
}
