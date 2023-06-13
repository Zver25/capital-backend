package space.sviridovskiy.capital.income.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import space.sviridovskiy.capital.income.domain.User;

@FeignClient("auth-service")
public interface JwtCheckService {

  @RequestMapping(method = RequestMethod.GET, value = "/api/auth/check-token/{token}")
  User check(@PathVariable String token);

}
