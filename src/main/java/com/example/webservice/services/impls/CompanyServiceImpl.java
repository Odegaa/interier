package com.example.webservice.services.impls;

import com.example.webservice.models.Company;
import com.example.webservice.payloads.CompanyDto;
import com.example.webservice.repositories.AddressRepository;
import com.example.webservice.repositories.AttachmentRepository;
import com.example.webservice.repositories.CompanyRepository;
import com.example.webservice.repositories.ProductRepository;
import com.example.webservice.services.CompanyService;
import com.example.webservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final AttachmentRepository attachmentRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository,
                              AttachmentRepository attachmentRepository,
                              ProductRepository productRepository,
                              AddressRepository addressRepository) {
        this.companyRepository = companyRepository;
        this.attachmentRepository = attachmentRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return null;
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return null;
    }

    @Override
    public Company getCompanyByCompanyName(String companyName) {
        return null;
    }

    @Override
    public ApiResponse addCompany(CompanyDto companyDto) {
        return null;
    }

    @Override
    public ApiResponse updateCompanyById(Long companyId, CompanyDto companyDto) {
        return null;
    }

    @Override
    public ApiResponse deleteCompany(Long companyId) {
        return null;
    }
}