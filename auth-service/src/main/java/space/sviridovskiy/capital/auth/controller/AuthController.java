package space.sviridovskiy.capital.auth.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import space.sviridovskiy.capital.auth.command.JwtCheckCommand;
import space.sviridovskiy.capital.auth.config.jwt.JwtProvider;
import space.sviridovskiy.capital.auth.controller.payload.AuthenticationRequest;
import space.sviridovskiy.capital.auth.controller.payload.AuthenticationResponse;
import space.sviridovskiy.capital.auth.controller.payload.RefreshTokenRequest;
import space.sviridovskiy.capital.auth.domain.User;
import space.sviridovskiy.capital.auth.domain.UserPayload;
import space.sviridovskiy.capital.auth.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/auth")
public class AuthController {
	private final UserService userService;
	private final JwtProvider jwtProvider;
	private final JwtCheckCommand jwtCheckCommand;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
		log.info("Register user: {}", request.getUsername());
		userService.create(request.toUser());
		String token = jwtProvider.generateToken(request.getUsername());
		String refreshToken = jwtProvider.generateRefreshToken(request.getUsername());

		return ResponseEntity.ok(
			AuthenticationResponse.builder()
				.username(request.getUsername())
				.accessToken(token)
				.refreshToken(refreshToken)
				.build()
		);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
		log.info("Login user: {}", request.getUsername());
		Optional<User> optionalUser = userService.findByUsernameAndPassword(request.getUsername(), request.getPassword());

		return optionalUser
			.map(User::getUsername)
			.map(username -> ResponseEntity.ok(
				AuthenticationResponse.builder()
					.username(username)
					.accessToken(jwtProvider.generateToken(username))
					.refreshToken(jwtProvider.generateRefreshToken(username))
					.build()
			))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		String refreshedToken = jwtProvider.refreshToken(refreshTokenRequest.getRefreshToken());
		String username = jwtProvider.getUsernameFromToken(refreshedToken);

		if (refreshedToken != null) {
			return ResponseEntity.ok(
				AuthenticationResponse.builder()
					.username(username)
					.accessToken(refreshedToken)
					.refreshToken(refreshTokenRequest.getRefreshToken())
					.build()
			);
		} else {
			throw new RuntimeException("Invalid refresh token");
		}
	}

	@GetMapping("/check-token/{token}")
	public ResponseEntity<UserPayload> checkToken(@PathVariable String token) {
		Optional<UsernamePasswordAuthenticationToken> authenticationTokenOptional = jwtCheckCommand.execute(token);

		return authenticationTokenOptional
			.map(authenticationToken -> {
				List<String> grantedAuthorities = authenticationToken.getAuthorities()
					.stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList());
				UserPayload payload = new UserPayload(authenticationToken.getPrincipal().toString(), grantedAuthorities);

				return ResponseEntity.ok(payload);
			})
			.orElse(ResponseEntity.ok(new UserPayload()));
	}
}
