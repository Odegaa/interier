package com.example.webservice.repositories;

import com.example.webservice.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company getCompanyByCompanyName(String companyName);

    boolean existsByCompanyName(String companyName);

    Company getCompanyByAbout(String about);
}