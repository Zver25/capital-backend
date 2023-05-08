package space.sviridovskiy.capital.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserPayload {
	private String username;
	private List<String> grantedAuthorities;

	public UserPayload() {
		username = "";
		grantedAuthorities = new ArrayList<>();
	}
}
