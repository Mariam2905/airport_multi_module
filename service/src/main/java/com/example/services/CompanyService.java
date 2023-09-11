package com.example.services;

import com.example.model.*;
import com.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final TripRepository tripRepository;

    private final PassInTripRepository passInTripRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, TripRepository tripRepository, PassInTripRepository passInTripRepository) {
        this.companyRepository = companyRepository;
        this.tripRepository = tripRepository;
        this.passInTripRepository = passInTripRepository;
    }

    /**
     * Gets all companies from company table
     *
     * @return list of companies
     */
    public List<Company> getALL() {
        return companyRepository.findAll();
    }

    /**
     * Gets a company from a table by given id.
     *
     * @param id;
     * @return company;
     */
    public Optional<Company> getById(Long id) {
        boolean exists = companyRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Company with id " + id + " does not exist.");
        }
        return companyRepository.findById(id);
    }

    /**
     * Deletes a Company by given id.
     *
     * @param id;
     */
    @Transactional
    public void deleteById(Long id) {
        boolean exists = companyRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Company with id " + id + " does not exist.");
        }
        List<Trip> tripList = tripRepository.findAllByCompanyId(id);
        for (Trip trip : tripList) {
            passInTripRepository.deletePassInTripByTripId(trip.getId());
        }
        tripRepository.deleteAll(tripList);
        companyRepository.deleteById(id);
    }

    /**
     * saves a new company into the table
     *
     * @param company;
     */
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    /**
     * Updates all the fields or only needed fields of a company object
     *
     * @param id;
     * @param companyName;
     * @param foundingDate;
     */
    @Transactional
    public void updateCompany(Long id, String companyName, String foundingDate) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Company with id " + id + " does not exist."));
        if (foundingDate != null && foundingDate.length() > 0 && !Objects.equals(company.getFoundingDate(), foundingDate)) {
            company.setFoundingDate(foundingDate);
        }
        if (companyName != null && companyName.length() > 0 && !Objects.equals(company.getCompanyName(), companyName)) {
            Optional<Company> companyOptional = companyRepository.findCompanyByCompanyName(companyName);
            if (companyOptional.isPresent()) {
                throw new IllegalStateException("CompanyName number already exists.");
            }
            company.setCompanyName(companyName);
        }
    }
}
