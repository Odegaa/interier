package uz.interier.services;

import uz.interier.models.Company;
import uz.interier.payloads.CompanyDto;
import uz.interier.utils.ApiResponse;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    Company getCompanyById(Long companyId);

    Company getCompanyByCompanyName(String companyName);

    ApiResponse addCompany(CompanyDto companyDto);

    ApiResponse updateCompanyById(Long companyId, CompanyDto companyDto);

    ApiResponse deleteCompany(Long companyId);
}