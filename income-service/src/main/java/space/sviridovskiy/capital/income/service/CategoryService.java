package space.sviridovskiy.capital.income.service;

import space.sviridovskiy.capital.income.domain.Category;
import space.sviridovskiy.capital.income.exeption.CategoryNotFoundException;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
  List<Category> findByUsername(String username);

  Category create(String username, Category category);

  Category update(String username, Category category) throws CategoryNotFoundException;

  void delete(String username, UUID categoryId) throws CategoryNotFoundException;
}
