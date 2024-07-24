package space.sviridovskiy.capital.auth.controller.payload;

import lombok.Data;
import space.sviridovskiy.capital.auth.domain.User;

@Data
public class AuthenticationRequest {
	private String username;
	private String fullname;
	private String password;

	public User toUser() {
		User user = new User();

		user.setUsername(username);
		user.setFullname(fullname);
		user.setPassword(password);

		return user;
	}
}
