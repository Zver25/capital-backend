package space.sviridovskiy.capital.expense.controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import space.sviridovskiy.capital.expense.domain.Expense;
import space.sviridovskiy.capital.expense.exeption.ExpenseNotFoundException;
import space.sviridovskiy.capital.expense.service.ExpenseService;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/expense")
public class ExpenseController {
  private final ExpenseService expenseService;

  private String getUsername(UsernamePasswordAuthenticationToken authenticationToken) {
    return authenticationToken.getPrincipal().toString();
  }

  @GetMapping("{id}")
  public ResponseEntity<Expense> getById(
    @PathVariable long id,
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
    @RequestBody Expense bodyExpense
  ) {
    return ResponseEntity.ok(
      expenseService.create(
        getUsername(authenticationToken),
        bodyExpense
      )
    );
  }

  @PutMapping("{id}")
  public ResponseEntity<Expense> update(
    @PathVariable long id,
    @RequestBody Expense bodyExpense,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws ExpenseNotFoundException {
    bodyExpense.setId(id);

    return ResponseEntity.ok(
      expenseService.update(
        getUsername(authenticationToken),
        bodyExpense
      )
    );
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(
    @PathVariable long id,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws ExpenseNotFoundException {
    expenseService.delete(
      getUsername(authenticationToken),
      id
    );

    return ResponseEntity.ok().build();
  }
}
