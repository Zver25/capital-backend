package space.sviridovskiy.capital.income.controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import space.sviridovskiy.capital.income.domain.Income;
import space.sviridovskiy.capital.income.exeption.IncomeNotFoundException;
import space.sviridovskiy.capital.income.service.IncomeService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/incomes")
public class IncomeController {
  private final IncomeService expenseService;

  private String getUsername(UsernamePasswordAuthenticationToken authenticationToken) {
    return authenticationToken.getPrincipal().toString();
  }

  @GetMapping("{id}")
  public ResponseEntity<Income> getById(
    @PathVariable UUID id,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws IncomeNotFoundException {
    return ResponseEntity.ok(
      expenseService.findById(
        getUsername(authenticationToken),
        id
      )
    );
  }

  @GetMapping("{start}/{end}")
  public ResponseEntity<List<Income>> getByPeriod(
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
  public ResponseEntity<Income> create(
    UsernamePasswordAuthenticationToken authenticationToken,
    @RequestBody Income bodyExpense
  ) {
    return ResponseEntity.ok(
      expenseService.create(
        getUsername(authenticationToken),
        bodyExpense
      )
    );
  }

  @PutMapping("{id}")
  public ResponseEntity<Income> update(
    @PathVariable UUID id,
    @RequestBody Income bodyExpense,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws IncomeNotFoundException {
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
    @PathVariable UUID id,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws IncomeNotFoundException {
    expenseService.delete(
      getUsername(authenticationToken),
      id
    );

    return ResponseEntity.ok().build();
  }
}
