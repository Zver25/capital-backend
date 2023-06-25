package space.sviridovskiy.capital.currency.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.sviridovskiy.capital.currency.domain.Currency;
import space.sviridovskiy.capital.currency.service.CurrencyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/currency")
public class CurrencyController {
  private final CurrencyService currencyService;

  private String getUsername(UsernamePasswordAuthenticationToken authenticationToken) {
    return authenticationToken.getPrincipal().toString();
  }

  @GetMapping
  public ResponseEntity<List<String>> getAll(UsernamePasswordAuthenticationToken authenticationToken) {
    return ResponseEntity.ok(
      currencyService
        .getAllForUser(
          getUsername(authenticationToken)
        )
        .stream()
        .map(Currency::getCode)
        .collect(Collectors.toList())
    );
  }
}
