package space.sviridovskiy.capital.expense.service;

import space.sviridovskiy.capital.expense.exeption.CategoryNotFoundException;
import space.sviridovskiy.capital.expense.payload.CategoryResponse;
import space.sviridovskiy.capital.expense.payload.CreateCategoryRequest;
import space.sviridovskiy.capital.expense.payload.UpdateCategoryRequest;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
  List<CategoryResponse> findByUsername(String username);

  CategoryResponse findById(String username, UUID id) throws CategoryNotFoundException;

  CategoryResponse create(String username, CreateCategoryRequest createCategoryRequest);

  CategoryResponse update(String username, UpdateCategoryRequest updateCategoryRequest) throws CategoryNotFoundException;

  void delete(String username, UUID categoryId) throws CategoryNotFoundException;
}
