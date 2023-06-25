package space.sviridovskiy.capital.report.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private String username;
  private Collection<String> grantedAuthorities;
}
