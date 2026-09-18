package com.tech.saas.mappers;

import com.tech.saas.entities.Category;
import com.tech.saas.entities.Product;
import com.tech.saas.requests.ProductRequest;
import com.tech.saas.responses.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(final ProductRequest request) {
        return Product.builder()
                      .name(request.getName())
                      .reference(request.getReference())
                      .description(request.getDescription())
                      .price(request.getPrice())
                      .alertThreshold(request.getAlertThreshold())
                      .category(Category.builder()
                                        .id(request.getCategoryId())
                                        .build())
                      .deleted(false)
                      .build();
    }

    public ProductResponse toResponse(final Product product) {
        final int availableQuantity = product.getStockMovements() == null ? 0 :
                product.getStockMovements().stream()
                       .mapToInt(m -> "IN".equals(m.getTypeMvt().name()) ? m.getQuantity() : -m.getQuantity())
                       .sum();

        return ProductResponse.builder()
                              .id(product.getId())
                              .name(product.getName())
                              .reference(product.getReference())
                              .description(product.getDescription())
                              .price(product.getPrice())
                              .alertThreshold(product.getAlertThreshold())
                              .categoryId(product.getCategory()
                                                 .getId())
                              .categoryName(product.getCategory()
                                                   .getName())
                              .availableQuantity(availableQuantity)
                              .build();
    }
}
