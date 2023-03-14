package space.sviridovskiy.capital.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPayload {
	private String username;
	private Collection<String> grantedAuthorities;
}
