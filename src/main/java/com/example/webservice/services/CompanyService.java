package com.example.webservice.services;

import com.example.webservice.models.Company;
import com.example.webservice.payloads.CompanyDto;
import com.example.webservice.utils.ApiResponse;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    Company getCompanyById(Long companyId);

    Company getCompanyByCompanyName(String companyName);

    ApiResponse addCompany(CompanyDto companyDto);

    ApiResponse updateCompanyById(Long companyId, CompanyDto companyDto);

    ApiResponse deleteCompany(Long companyId);
}