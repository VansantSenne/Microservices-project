package fact.it.beoordelingservice.service;


import fact.it.beoordelingservice.dto.BeoordelingsResponse;
import fact.it.beoordelingservice.model.Beoordeling;
import fact.it.beoordelingservice.repository.BeoordelingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BeoordelingsService {

    private final BeoordelingsRepository beoordelingsRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .skuCode(productRequest.getSkuCode())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    public List<ProductResponse> getAllProductsBySkuCode(List<String> skuCode) {
        List<Product> products = productRepository.findBySkuCodeIn(skuCode);

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .skuCode(product.getSkuCode())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
