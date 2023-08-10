package space.sviridovskiy.capital.income.controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import space.sviridovskiy.capital.income.domain.Category;
import space.sviridovskiy.capital.income.exeption.CategoryNotFoundException;
import space.sviridovskiy.capital.income.payload.CategoryResponse;
import space.sviridovskiy.capital.income.payload.CreateCategoryRequest;
import space.sviridovskiy.capital.income.payload.UpdateCategoryRequest;
import space.sviridovskiy.capital.income.service.CategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/income-categories")
public class CategoryController {
  private final CategoryService categoryService;

  private String getUsername(UsernamePasswordAuthenticationToken authenticationToken) {
    return authenticationToken.getPrincipal().toString();
  }

  @GetMapping
  public ResponseEntity<List<CategoryResponse>> getAll(UsernamePasswordAuthenticationToken authenticationToken) {
    return ResponseEntity.ok(
      categoryService.findByUsername(getUsername(authenticationToken))
    );
  }

  @PostMapping
  public ResponseEntity<CategoryResponse> create(
    @RequestBody CreateCategoryRequest createCategoryRequest,
    UsernamePasswordAuthenticationToken authenticationToken
  ) {
    String username = getUsername(authenticationToken);

    return ResponseEntity.ok(
      categoryService.create(username, createCategoryRequest)
    );
  }

  @PutMapping("{id}")
  public ResponseEntity<CategoryResponse> update(
    @PathVariable UUID id,
    @RequestBody UpdateCategoryRequest updateCategoryRequest,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws CategoryNotFoundException {
    updateCategoryRequest.setId(id);

    return ResponseEntity.ok(
      categoryService.update(
        getUsername(authenticationToken),
        updateCategoryRequest
      )
    );
  }
}
