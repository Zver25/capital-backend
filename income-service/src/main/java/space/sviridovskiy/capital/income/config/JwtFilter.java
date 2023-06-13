package space.sviridovskiy.capital.income.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import space.sviridovskiy.capital.income.domain.CustomGrantedAuthority;
import space.sviridovskiy.capital.income.domain.User;
import space.sviridovskiy.capital.income.service.JwtCheckService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class JwtFilter extends GenericFilterBean {
  public static final String AUTHORIZATION = "Authorization";
  private final JwtCheckService jwtCheckService;

  private String getTokenFromRequest(HttpServletRequest request) {
    String bearer = request.getHeader(AUTHORIZATION);

    if (bearer != null && bearer.startsWith("Bearer ")) {
      return bearer.substring(7);
    }

    return null;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    String token = getTokenFromRequest((HttpServletRequest) request);

    if (token != null && !token.isEmpty()) {
      User user = jwtCheckService.check(token);

      if (user != null && user.getUsername() != null && !user.getUsername().isEmpty()) {
        List<GrantedAuthority> grantedAuthorities = user.getGrantedAuthorities()
          .stream()
          .map(CustomGrantedAuthority::new)
          .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
          user.getUsername(),
          null,
          grantedAuthorities
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    chain.doFilter(request, response);
  }
}
