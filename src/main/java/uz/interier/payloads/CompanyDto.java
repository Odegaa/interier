package uz.interier.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private String companyName;
    private String about;
    private List<Long> attachmentId;
    private List<Long> productId;
    private Long addressId;
}