package space.sviridovskiy.capital.expense.controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import space.sviridovskiy.capital.expense.payload.CreateExpenseRequest;
import space.sviridovskiy.capital.expense.payload.UpdateExpenseRequest;
import space.sviridovskiy.capital.expense.domain.Expense;
import space.sviridovskiy.capital.expense.exeption.CategoryNotFoundException;
import space.sviridovskiy.capital.expense.exeption.ExpenseNotFoundException;
import space.sviridovskiy.capital.expense.service.ExpenseService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/expenses")
public class ExpenseController {
  private final ExpenseService expenseService;

  private String getUsername(UsernamePasswordAuthenticationToken authenticationToken) {
    return authenticationToken.getPrincipal().toString();
  }

  @GetMapping("{id}")
  public ResponseEntity<Expense> getById(
    @PathVariable UUID id,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws ExpenseNotFoundException {
    return ResponseEntity.ok(
      expenseService.findById(
        getUsername(authenticationToken),
        id
      )
    );
  }

  @GetMapping("{start}/{end}")
  public ResponseEntity<List<Expense>> getByPeriod(
    @PathVariable String start,
    @PathVariable String end,
    UsernamePasswordAuthenticationToken authenticationToken
  ) {
    LocalDate startDate = LocalDate.parse(start);
    LocalDate endDate = LocalDate.parse(end);

    return ResponseEntity.ok(
      expenseService.findByPeriod(
        getUsername(authenticationToken),
        startDate,
        endDate
      )
    );
  }

  @PostMapping
  public ResponseEntity<Expense> create(
    UsernamePasswordAuthenticationToken authenticationToken,
    @RequestBody CreateExpenseRequest createExpenseRequest
  ) throws CategoryNotFoundException {
    return ResponseEntity.ok(
      expenseService.create(
        getUsername(authenticationToken),
        createExpenseRequest
      )
    );
  }

  @PutMapping("{id}")
  public ResponseEntity<Expense> update(
    @PathVariable UUID id,
    @RequestBody UpdateExpenseRequest updateExpenseRequest,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws ExpenseNotFoundException, CategoryNotFoundException {
    updateExpenseRequest.setId(id);

    return ResponseEntity.ok(
      expenseService.update(
        getUsername(authenticationToken),
        updateExpenseRequest
      )
    );
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(
    @PathVariable UUID id,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws ExpenseNotFoundException {
    expenseService.delete(
      getUsername(authenticationToken),
      id
    );

    return ResponseEntity.ok().build();
  }
}
