package space.sviridovskiy.capital.expense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CurrencyApplication {

  public static void main(String[] args) {
    SpringApplication.run(CurrencyApplication.class, args);
  }

}
