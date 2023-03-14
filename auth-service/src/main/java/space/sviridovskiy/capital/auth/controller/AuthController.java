package space.sviridovskiy.capital.auth.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.sviridovskiy.capital.auth.config.jwt.JwtProvider;
import space.sviridovskiy.capital.auth.controller.payload.AuthRequest;
import space.sviridovskiy.capital.auth.controller.payload.AuthResponse;
import space.sviridovskiy.capital.auth.domain.User;
import space.sviridovskiy.capital.auth.service.UserService;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	private final UserService userService;
	private final JwtProvider jwtProvider;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
		userService.create(request.toUser());
		String token = jwtProvider.generateToken(request.getUsername());

		return ResponseEntity.ok(new AuthResponse(request.getUsername(), token));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
		Optional<User> optionalUser = userService.findByUsernameAndPassword(request.getUsername(), request.getPassword());

		return optionalUser
			.map(User::getUsername)
			.map(username -> ResponseEntity.ok(new AuthResponse(username, jwtProvider.generateToken(username))))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
}
