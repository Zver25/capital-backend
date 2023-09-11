package space.sviridovskiy.capital.income.controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import space.sviridovskiy.capital.income.domain.Category;
import space.sviridovskiy.capital.income.exeption.CategoryNotFoundException;
import space.sviridovskiy.capital.income.exeption.IncomeNotFoundException;
import space.sviridovskiy.capital.income.payload.CreateIncomeRequest;
import space.sviridovskiy.capital.income.payload.IncomeResponse;
import space.sviridovskiy.capital.income.payload.UpdateIncomeRequest;
import space.sviridovskiy.capital.income.service.IncomeService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/incomes")
public class IncomeController {
  private final IncomeService incomeService;

  private String getUsername(UsernamePasswordAuthenticationToken authenticationToken) {
    return authenticationToken.getPrincipal().toString();
  }

  @GetMapping("{id}")
  public ResponseEntity<IncomeResponse> getById(
    @PathVariable UUID id,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws IncomeNotFoundException {
    final String username = getUsername(authenticationToken);

    return ResponseEntity.ok(
      incomeService.findById(username, id)
    );
  }

  @GetMapping("{start}/{end}")
  public ResponseEntity<List<IncomeResponse>> getByPeriod(
    @PathVariable String start,
    @PathVariable String end,
    UsernamePasswordAuthenticationToken authenticationToken
  ) {
    final String username = getUsername(authenticationToken);
    final LocalDate startDate = LocalDate.parse(start);
    final LocalDate endDate = LocalDate.parse(end);

    return ResponseEntity.ok(
      incomeService.findByPeriod(username, startDate, endDate)
    );
  }

  @GetMapping("{category}/{start}/{end}")
  public ResponseEntity<List<IncomeResponse>> getByCategoryAndPeriod(
    @PathVariable String category,
    @PathVariable String start,
    @PathVariable String end,
    UsernamePasswordAuthenticationToken authenticationToken
  ) {
    final String username = getUsername(authenticationToken);
    final LocalDate startDate = LocalDate.parse(start);
    final LocalDate endDate = LocalDate.parse(end);

    return ResponseEntity.ok(
      incomeService.findByCategoryAndPeriod(username, new Category(UUID.fromString(category)), startDate, endDate)
    );
  }

  @PostMapping
  public ResponseEntity<IncomeResponse> create(
    UsernamePasswordAuthenticationToken authenticationToken,
    @RequestBody CreateIncomeRequest createIncomeRequest
  ) throws CategoryNotFoundException {
    String username = getUsername(authenticationToken);

    return ResponseEntity.ok(
      incomeService.create(username, createIncomeRequest)
    );
  }

  @PutMapping("{id}")
  public ResponseEntity<IncomeResponse> update(
    @PathVariable UUID id,
    @RequestBody UpdateIncomeRequest updateIncomeRequest,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws IncomeNotFoundException, CategoryNotFoundException {
    final String username = getUsername(authenticationToken);

    updateIncomeRequest.setId(id);

    return ResponseEntity.ok(
      incomeService.update(username, updateIncomeRequest)
    );
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(
    @PathVariable UUID id,
    UsernamePasswordAuthenticationToken authenticationToken
  ) throws IncomeNotFoundException {
    incomeService.delete(
      getUsername(authenticationToken),
      id
    );

    return ResponseEntity.ok().build();
  }
}
