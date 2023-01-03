package uz.interier.services.impls;

import uz.interier.models.Attachment;
import uz.interier.models.Brand;
import uz.interier.models.Category;
import uz.interier.models.Product;
import uz.interier.models.templates.Status;
import uz.interier.payloads.ProductDto;
import uz.interier.repositories.AttachmentRepository;
import uz.interier.repositories.BrandRepository;
import uz.interier.repositories.CategoryRepository;
import uz.interier.repositories.ProductRepository;
import uz.interier.services.ProductService;
import uz.interier.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AttachmentRepository attachmentRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              AttachmentRepository attachmentRepository,
                              CategoryRepository categoryRepository,
                              BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.attachmentRepository = attachmentRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Product getProductByProductName(String productName) {
        return productRepository.getProductsByProductName(productName);
    }

    @Override
    public ApiResponse addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setAbout(productDto.getAbout());
        product.setCharacteristic(productDto.getCharacteristic());
        product.setPrice(productDto.getPrice());
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        optionalCategory.ifPresent(product::setCategoryId);

        Optional<Brand> optionalBrand = brandRepository.findById(productDto.getBrandId());
        optionalBrand.ifPresent(product::setBrands);

        List<Attachment> attachmentList = new ArrayList<>();
        for (Long value : productDto.getAttachmentId()) {
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(value);
            optionalAttachment.ifPresent(attachmentList::add);
        }
        product.setAttachmentsId(attachmentList);
        product.setStatus(Status.ACTIVE);
        productRepository.save(product);
        return new ApiResponse("Product successfully SAVED!", true);
    }

    @Override
    public ApiResponse updateProductById(Long productId, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductName(productDto.getProductName());
            product.setAbout(productDto.getAbout());
            product.setCharacteristic(productDto.getCharacteristic());
            product.setPrice(productDto.getPrice());
            Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
            optionalCategory.ifPresent(product::setCategoryId);

            Optional<Brand> optionalBrand = brandRepository.findById(productDto.getBrandId());
            optionalBrand.ifPresent(product::setBrands);

            List<Attachment> attachmentList = new ArrayList<>();
            for (Long value : productDto.getAttachmentId()) {
                Optional<Attachment> optionalAttachment = attachmentRepository.findById(value);
                optionalAttachment.ifPresent(attachmentList::add);
            }
            product.setAttachmentsId(attachmentList);
            productRepository.save(product);
            return new ApiResponse("Product successfully UPDATED!", true);
        }
        return new ApiResponse("Product not found!", false);
    }

    @Override
    public ApiResponse deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(productId);
            return new ApiResponse("Product DELETED!", true);
        }
        return new ApiResponse("Product not found!", false);
    }
}