package com.example.controller;

import com.example.model.*;
import com.example.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/getAll")
    public List<Company> getAllCompanies() {
        return companyService.getALL();
    }

    @GetMapping("/get/{companyId}")
    public Optional<Company> getById(@PathVariable("companyId") Long id) {
        return companyService.getById(id);
    }

    @DeleteMapping("/delete/{companyId}")
    public void deleteById(@PathVariable("companyId") Long id) {
        companyService.deleteById(id);
    }

    @PostMapping("/save")
    public Long saveCompany(@RequestBody Company company) {
        companyService.saveCompany(company);
        return company.getCompanyId();
    }

    @PutMapping(path = "{companyId}")
    public void updatePassenger(@PathVariable("companyId") Long companyId,
                                @RequestParam(required = false) String companyName,
                                @RequestParam(required = false) String foundingDate) {
        companyService.updateCompany(companyId, companyName, foundingDate);
    }
}
