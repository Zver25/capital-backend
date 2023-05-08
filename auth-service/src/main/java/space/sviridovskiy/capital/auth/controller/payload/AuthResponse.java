package space.sviridovskiy.capital.auth.controller.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
	private String username;
	private String token;
}
