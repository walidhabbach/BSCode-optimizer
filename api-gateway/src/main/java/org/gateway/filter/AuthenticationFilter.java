package org.gateway.filter;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.gateway.exception.wrapper.JwtValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.security.Key;
import java.util.Date;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Value("${application.security.jwt.secret-key}")
	private String secretKey;

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) throws JwtValidationException{
		return (exchange, chain) -> {
			if (RouteValidator.isSecured(exchange.getRequest())) {
				// Check if the Authorization header is present
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					// Instead of throwing an exception, you can return a response
					throw new JwtValidationException("Missing authorization header");

				}

				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7); // Remove "Bearer " prefix
				} else {
					throw new JwtValidationException("Invalid authorization header format");
				}

				// Validate the token
				if (!isValidToken(authHeader)) {
					throw new JwtValidationException("Invalid JWT token");
				}
			}

			return chain.filter(exchange);
		};
	}

	private boolean isValidToken(String token) {
		try {
			// Parse the token
			Claims claims = Jwts.parser()
					.setSigningKey(getSignInKey())
					.build()
					.parseClaimsJws(token)
					.getBody();

			// Check if the token is expired
			Date expiration = claims.getExpiration();
			if (expiration == null || expiration.before(new Date())) {
				throw new JwtValidationException("Token has expired.");
			}

			return true; // Token is valid

		} catch (ExpiredJwtException e) {
			throw new JwtValidationException("Token has expired.");
		} catch (MalformedJwtException e) {
			throw new JwtValidationException("Invalid token format.");
		} catch (SignatureException e) {
			throw new JwtValidationException("Invalid token signature.");
		} catch (Exception e) {
			throw new JwtValidationException("Token validation error: " + e.getMessage());
		}
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public static class Config {
		// Add any configuration properties here if needed
	}
}
