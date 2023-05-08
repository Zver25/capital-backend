package space.sviridovskiy.capital.auth.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import space.sviridovskiy.capital.auth.config.jwt.JwtProvider;
import space.sviridovskiy.capital.auth.service.UserService;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class JwtCheckCommand {
	private final JwtProvider jwtProvider;
	private final UserService userService;

	public Optional<UsernamePasswordAuthenticationToken> execute(String token) {
		if (token != null && jwtProvider.validateToken(token)) {
			String username = jwtProvider.getUsernameFromToken(token);
			UserDetails userDetails = userService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

			log.debug("Token valid");
			return Optional.of(auth);
		}

		log.debug("Token invalid");
		return Optional.empty();
	}
}
