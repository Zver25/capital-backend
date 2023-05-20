package space.sviridovskiy.capital.expense.controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import space.sviridovskiy.capital.expense.domain.Category;
import space.sviridovskiy.capital.expense.exeption.CategoryNotFoundException;
import space.sviridovskiy.capital.expense.service.CategoryService;

import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/expense-category")
public class CategoryController {
  private final CategoryService categoryService;

  private String getUsername(UsernamePasswordAuthenticationToken authenticationToken) {
    return authenticationToken.getPrincipal().toString();
  }

  @GetMapping
  public ResponseEntity<List<Category>> getAll(UsernamePasswordAuthenticationToken authenticationToken) {
    return ResponseEntity.ok(
      categoryService.findByUsername(getUsername(authenticationToken))
    );
  }

  @PostMapping
  public ResponseEntity<Category> create(
    @RequestBody Category bodyCategory,
    UsernamePasswordAuthenticationToken authenticationToken
  ) {
    String username = getUsername(authenticationToken);
    Category createdCategory = categoryService.create(username, bodyCategory);

    return ResponseEntity.ok(createdCategory);
  }

  @PutMapping("{id}")
  public ResponseEntity<Category> update(
    @PathVariable long id,
    @RequestBody Category bodyCategory,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws CategoryNotFoundException {
    bodyCategory.setId(id);

    return ResponseEntity.ok(
      categoryService.update(
        getUsername(authenticationToken),
        bodyCategory
      )
    );
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(
    @PathVariable long id,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws CategoryNotFoundException {
    categoryService.delete(
      getUsername(authenticationToken),
      id
    );

    return ResponseEntity.ok().build();
  }
}
