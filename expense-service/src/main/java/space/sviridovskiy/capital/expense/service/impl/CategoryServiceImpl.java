package space.sviridovskiy.capital.expense.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.expense.domain.Category;
import space.sviridovskiy.capital.expense.exeption.CategoryNotFoundException;
import space.sviridovskiy.capital.expense.repository.CategoryRepository;
import space.sviridovskiy.capital.expense.service.CategoryService;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;

  @Override
  public List<Category> findByUsername(String username) {
    return categoryRepository.findByUsername(username);
  }

  @Override
  public Category create(String username, Category category) {
    category.setUsername(username);

    return categoryRepository.save(category);
  }

  @Override
  public Category update(String username, Category category) throws CategoryNotFoundException {
    Category currentCategory = categoryRepository
      .findById(category.getId())
      .filter(optionalCategory -> optionalCategory.getUsername().equals(username))
      .orElseThrow(CategoryNotFoundException::new);

    currentCategory.update(category);

    return categoryRepository.save(currentCategory);
  }

  @Override
  public void delete(String username, long categoryId) throws CategoryNotFoundException {
    Category currentCategory = categoryRepository
      .findById(categoryId)
      .filter(optionalCategory -> optionalCategory.getUsername().equals(username))
      .orElseThrow(CategoryNotFoundException::new);

    categoryRepository.delete(currentCategory);
  }
}
