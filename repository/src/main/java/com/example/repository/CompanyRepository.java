package com.example.repository;

import com.example.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("SELECT c FROM Company c where c.companyName = ?1")
    Optional<Company> findCompanyByCompanyName(String companyName);
}
