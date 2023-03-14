package space.sviridovskiy.capital.auth.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import space.sviridovskiy.capital.auth.command.JwtCheckCommand;
import space.sviridovskiy.capital.auth.domain.UserPayload;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class CheckTokenController {
	private final JwtCheckCommand jwtCheckCommand;

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
