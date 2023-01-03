package uz.interier.controllers;

import uz.interier.models.Company;
import uz.interier.payloads.CompanyDto;
import uz.interier.services.CompanyService;
import uz.interier.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company")
@PreAuthorize(value = "hasAnyRole('ADMIN', 'DIRECTOR')")
public class CompanyController {

    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping
    public HttpEntity<?> addCompany(@Valid @RequestBody CompanyDto companyDto) {
        ApiResponse apiResponse = service.addCompany(companyDto);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.CREATED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(service.getAllCompanies());
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long companyId) {
        return ResponseEntity.ok(service.getCompanyById(companyId));
    }

    @GetMapping("/{companyName}")
    public ResponseEntity<Company> getCompanyByCompanyName(@PathVariable String companyName) {
        return ResponseEntity.ok(service.getCompanyByCompanyName(companyName));
    }

    @PutMapping("/{companyId}")
    public HttpEntity<?> updateCompany(@PathVariable Long companyId,
                                       @Valid @RequestBody CompanyDto companyDto) {
        ApiResponse apiResponse = service.updateCompanyById(companyId, companyDto);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @DeleteMapping("/{companyId}")
    public HttpEntity<?> deleteCompany(@PathVariable Long companyId) {
        ApiResponse apiResponse = service.deleteCompany(companyId);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }
}