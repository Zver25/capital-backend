package space.sviridovskiy.capital.auth.controller.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
	private String username;
	private String accessToken;
	private String refreshToken;
}
