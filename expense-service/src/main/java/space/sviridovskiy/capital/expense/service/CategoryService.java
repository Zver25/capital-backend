package space.sviridovskiy.capital.expense.service;

import space.sviridovskiy.capital.expense.domain.Category;
import space.sviridovskiy.capital.expense.exeption.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {
  List<Category> findByUsername(String username);

  Category create(String username, Category category);

  Category update(String username, Category category) throws CategoryNotFoundException;

  void delete(String username, long categoryId) throws CategoryNotFoundException;
}
