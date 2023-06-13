package space.sviridovskiy.capital.income.controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import space.sviridovskiy.capital.income.domain.Category;
import space.sviridovskiy.capital.income.exeption.CategoryNotFoundException;
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
    @PathVariable UUID id,
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
    @PathVariable UUID id,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws CategoryNotFoundException {
    categoryService.delete(
      getUsername(authenticationToken),
      id
    );

    return ResponseEntity.ok().build();
  }
}
