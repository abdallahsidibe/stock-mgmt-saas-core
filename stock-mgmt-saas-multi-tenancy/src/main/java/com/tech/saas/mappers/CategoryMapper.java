package com.tech.saas.mappers;

import com.tech.saas.entities.Category;
import com.tech.saas.requests.CategoryRequest;
import com.tech.saas.responses.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(final CategoryRequest request) {
        return Category.builder()
                       .name(request.getName())
                       .description(request.getDescription())
                       .deleted(false)
                       .build();
    }

    public CategoryResponse toResponse(final Category entity) {
        final int nbProduct = 0;// entity.getProducts() == null ? 0 : entity.getProducts().size();
        return CategoryResponse.builder()
                               .id(entity.getId())
                               .name(entity.getName())
                               .description(entity.getDescription())
                               .nbProducts(nbProduct)
                               .build();
    }
}
