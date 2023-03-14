package space.sviridovskiy.capital.auth.config.jwt;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import space.sviridovskiy.capital.auth.command.JwtCheckCommand;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class JwtFilter extends GenericFilterBean {
	public static final String AUTHORIZATION = "Authorization";
	private final JwtCheckCommand jwtCheckCommand;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String token = getTokenFromRequest((HttpServletRequest) request);

		Optional<UsernamePasswordAuthenticationToken> authenticationTokenOptional = jwtCheckCommand.execute(token);
		authenticationTokenOptional.ifPresent(authenticationToken -> SecurityContextHolder.getContext().setAuthentication(authenticationToken));

		chain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String bearer = request.getHeader(AUTHORIZATION);

		if (bearer != null && bearer.startsWith("Bearer ")) {
			return bearer.substring(7);
		}

		return null;
	}

}
