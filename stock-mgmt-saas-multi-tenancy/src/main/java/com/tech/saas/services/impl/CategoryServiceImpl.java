package com.tech.saas.services.impl;

import com.tech.saas.common.PageResponse;
import com.tech.saas.entities.Category;
import com.tech.saas.exceptions.DuplicateResourceException;
import com.tech.saas.mappers.CategoryMapper;
import com.tech.saas.repositories.CategoryRepository;
import com.tech.saas.requests.CategoryRequest;
import com.tech.saas.responses.CategoryResponse;
import com.tech.saas.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void create(final CategoryRequest request) {
        // check if category already exists
        checkIfCategoryAlreadyExistsByName(request.getName());

        final Category entity = this.categoryMapper.toEntity(request);
        this.categoryRepository.save(entity);

    }

    @Override
    public void update(final String id, final CategoryRequest request) {
        // check if category already exists by ID
        final Optional<Category> existingCategory = this.categoryRepository.findById(id);
        if (existingCategory.isEmpty()) {
            log.debug("Category does not exist");
            throw new EntityNotFoundException("Category does Not exist");
        }

        // check if category already exists
        if (!existingCategory.get().getName().equalsIgnoreCase(request.getName())) {
            checkIfCategoryAlreadyExistsByName(request.getName());
        }

        final Category categoryToUpdate = this.categoryMapper.toEntity(request);
        categoryToUpdate.setId(id);
        this.categoryRepository.save(categoryToUpdate);

    }

    @Override
    public PageResponse<CategoryResponse> findAll(final int page, final int size) {
        final PageRequest pageRequest = PageRequest.of(page, size);
        final Page<Category> categories = this.categoryRepository.findAll(pageRequest);
        final Page<CategoryResponse> categoryResponses = categories.map(this.categoryMapper::toResponse);
        return PageResponse.of(categoryResponses);
    }

    @Override
    public CategoryResponse findById(final String id) {
        return this.categoryRepository.findById(id)
                .map(this.categoryMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Category does not exist"));
    }

    @Override
    public void delete(final String id) {
        final Category category = this.categoryRepository.findById(id)
                               .orElseThrow(() -> new EntityNotFoundException("Category does not exist"));
        this.categoryRepository.delete(category);
    }

    private void checkIfCategoryAlreadyExistsByName(final String categoryName) {
        final Optional<Category> category = this.categoryRepository.findByNameIgnoreCase(categoryName);
        if (category.isPresent()) {
            log.debug("Category already exists");
            throw new DuplicateResourceException("Category already exists");
        }
    }
}
