package uz.interier.services.impls;

import uz.interier.models.Company;
import uz.interier.payloads.CompanyDto;
import uz.interier.repositories.AddressRepository;
import uz.interier.repositories.AttachmentRepository;
import uz.interier.repositories.CompanyRepository;
import uz.interier.repositories.ProductRepository;
import uz.interier.services.CompanyService;
import uz.interier.utils.ApiResponse;
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