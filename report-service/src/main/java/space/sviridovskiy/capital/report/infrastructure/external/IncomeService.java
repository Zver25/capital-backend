package space.sviridovskiy.capital.report.infrastructure.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import space.sviridovskiy.capital.report.application.dto.TransactionDto;

import java.util.List;

@FeignClient("income-service")
public interface IncomeService {
  @RequestMapping(method = RequestMethod.GET, value = "/api/incomes/{start}/{end}")
  List<TransactionDto> getByPeriod(
    @RequestHeader(value = "Authorization") String authorizationHeader,
    @PathVariable String start,
    @PathVariable String end
  );
}
