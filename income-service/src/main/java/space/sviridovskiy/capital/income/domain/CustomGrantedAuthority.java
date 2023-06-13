package space.sviridovskiy.capital.income.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {
  private final String authority;

  @Override
  public String getAuthority() {
    return null;
  }

  @Override
  public String toString() {
    return authority;
  }
}
