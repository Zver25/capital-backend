package space.sviridovskiy.capital.expense.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.expense.domain.Category;
import space.sviridovskiy.capital.expense.exeption.CategoryNotFoundException;
import space.sviridovskiy.capital.expense.payload.CategoryResponse;
import space.sviridovskiy.capital.expense.payload.CreateCategoryRequest;
import space.sviridovskiy.capital.expense.payload.UpdateCategoryRequest;
import space.sviridovskiy.capital.expense.repository.CategoryRepository;
import space.sviridovskiy.capital.expense.service.CategoryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;

  private CategoryResponse map(Category category) {
    return CategoryResponse.builder()
      .id(category.getId())
      .name(category.getName())
      .build();
  }

  @Override
  public List<CategoryResponse> findByUsername(String username) {
    return categoryRepository.findByUsername(username)
      .stream()
      .map(this::map)
      .collect(Collectors.toList());
  }

  @Override
  public CategoryResponse findById(String username, UUID id) throws CategoryNotFoundException {
    return categoryRepository.findById(id)
      .filter(category -> category.getUsername().equals(username))
      .map(this::map)
      .orElseThrow(CategoryNotFoundException::new);
  }

  @Override
  public CategoryResponse create(String username, CreateCategoryRequest request) {
    final Category category = new Category(UUID.randomUUID());

    category.setUsername(username);
    category.setName(request.getName());

    return map(categoryRepository.save(category));
  }

  @Override
  public CategoryResponse update(String username, UpdateCategoryRequest request) throws CategoryNotFoundException {
    Category currentCategory = categoryRepository
      .findById(request.getId())
      .filter(optionalCategory -> optionalCategory.getUsername().equals(username))
      .orElseThrow(CategoryNotFoundException::new);

    currentCategory.setName(request.getName());

    return map(categoryRepository.save(currentCategory));
  }

  @Override
  public void delete(String username, UUID categoryId) throws CategoryNotFoundException {
    Category currentCategory = categoryRepository
      .findById(categoryId)
      .filter(optionalCategory -> optionalCategory.getUsername().equals(username))
      .orElseThrow(CategoryNotFoundException::new);

    categoryRepository.delete(currentCategory);
  }
}
