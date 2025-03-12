package vn.studentmanagement.api.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vn.studentmanagement.api.entity.UserToken;
import vn.studentmanagement.api.repository.UserTokenRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class JwtUtils {

    private final UserTokenRepository userTokenRepository;
    public String generateToken(String jwtSecret, Long userId, long time) {
        return Jwts.builder().setClaims(new HashMap<>())
                .setSubject(userId.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public Boolean isTokenValid(String jwtSecret, String token, String userId) {
        final String usernameFromToken = extractUsername(jwtSecret, token);
        return usernameFromToken.equals(userId) && !isTokenExpired(jwtSecret, token);
    }

    public String extractUsername(String jwtSecret, String token) {
        return extractClaim(jwtSecret, token, Claims::getSubject);
    }
    private Boolean isTokenExpired(String jwtSecret, String token) {
        var expiration = extractClaim(jwtSecret, token, Claims::getExpiration);
        if (expiration == null) return true;
        return expiration.before(new Date());
    }
    private Claims extractAllClaims(String jwtSecret, String token) {
        if (token == null) return null;

        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            return null;
        }
    }

    public <T> T extractClaim(String jwtSecret, String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtSecret, token);
        if (claims == null)
            return null;
        return claimsResolver.apply(claims);
    }

    public Boolean isActiveToken(String token, String userId) {
        Optional<UserToken> userTokenOpt = userTokenRepository.findByTokenAndUserId(token, userId);
        if (userTokenOpt.isPresent()){
            UserToken userToken = userTokenOpt.get();
            return userToken.isActive();
        }
        return false;
    }
}
