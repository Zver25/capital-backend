package space.sviridovskiy.capital.report.domain;

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
