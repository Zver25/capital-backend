package space.sviridovskiy.capital.currency.controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.sviridovskiy.capital.currency.domain.Rate;
import space.sviridovskiy.capital.currency.service.RateService;

import javax.ws.rs.QueryParam;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/currency-rate")
public class RateController {
  private final RateService rateService;

  @GetMapping("{sourceCode}/{targetCode}")
  public ResponseEntity<List<Rate>> getRateForPeriod(
    @PathVariable String sourceCode,
    @PathVariable String targetCode,
    @QueryParam("startDate")String startDateParam,
    @QueryParam("endDate")String endDateParam
  ) {
    LocalDate startDate = LocalDate.parse(startDateParam);
    LocalDate endDate = LocalDate.parse(endDateParam);

    return ResponseEntity.ok(
      rateService.getRateByCurrencyPairAndPeriod(sourceCode, targetCode, startDate, endDate)
    );
  }
}
