package space.sviridovskiy.capital.auth.config.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {
	private final String jwtSecret;

	private final long accessTokenExpiration;

	private final long refreshTokenExpiration;

	JwtProvider(
		@Value("${jwt.secret}") String jwtSecret,
		@Value("${jwt.access_expiration}") long accessTokenExpiration,
		@Value("${jwt.refresh_expiration}") long refreshTokenExpiration
	) {
		this.jwtSecret = jwtSecret;
		this.accessTokenExpiration = accessTokenExpiration;
		this.refreshTokenExpiration = refreshTokenExpiration;
	}

	public String generateToken(String username) {
		return Jwts.builder()
			.setSubject(username)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
			.signWith(SignatureAlgorithm.HS512, jwtSecret)
			.compact();
	}

	public String generateRefreshToken(String username) {
		return Jwts.builder()
			.setSubject(username)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
			.signWith(SignatureAlgorithm.HS512, jwtSecret)
			.compact();
	}

	public String refreshToken(String refreshToken) {
		try {
			String username = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(refreshToken)
				.getBody()
				.getSubject();

			return generateToken(username);
		} catch (Exception e) {
			return null;
		}
	}

	public String getUsernameFromToken(String token) {
		return Jwts.parser()
			.setSigningKey(jwtSecret)
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);

		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		final Date expiration = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getExpiration();

		return expiration.before(new Date());
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException expEx) {
			log.debug("Token expired");
		} catch (UnsupportedJwtException unsEx) {
			log.debug("Unsupported jwt");
		} catch (MalformedJwtException mjEx) {
			log.debug("Malformed jwt");
		} catch (SignatureException sEx) {
			log.debug("Invalid signature");
		} catch (Exception e) {
			log.debug("invalid token");
		}
		return false;
	}
}
