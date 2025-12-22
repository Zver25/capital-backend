package space.sviridovskiy.capital.report.infrastructure.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {
  private final String authority;

  @Override
  public String getAuthority() {
    return authority;
  }

  @Override
  public String toString() {
    return authority;
  }
}
