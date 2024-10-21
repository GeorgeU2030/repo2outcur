package co.edu.icesi.dev.outcome_curr_mgmt.saamfi.util;

import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.delegate.SaamfiClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class SaamfiJwtTools {
    //TODO add test coverage
    protected final Log logger = LogFactory.getLog(this.getClass());
    private static final String ROLE_KEYS = "role";

    private static final String INSTITUTION_CLAIM = "institution";

    private static final String SYSTEM_CLAIM = "system";

    private static final String USERNAME_CLAIM = "username";

    private final SaamfiClient saamfiClient;

    public String getUsernameFromJWT(String authToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey(saamfiClient.getPublicKey()).build()
                .parseClaimsJws(authToken).getBody();
        return (String) claims.get(USERNAME_CLAIM);
    }

    public long getSysIdFromJWT(String authToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey(saamfiClient.getPublicKey()).build()
                .parseClaimsJws(authToken).getBody();
        return (int) claims.get(SYSTEM_CLAIM);
    }

    public Collection<SimpleGrantedAuthority> getPermissionNamesFromJWT(String authToken) {
        final JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(saamfiClient.getPublicKey()).build();
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(authToken);
        final Claims claims = claimsJws.getBody();
        logger.info("\nroles: " + claims.get(ROLE_KEYS).toString() + "\n\n");
        Stream<SimpleGrantedAuthority> stream = Arrays.stream(claims.get(ROLE_KEYS).toString().split(","))
                .map(SimpleGrantedAuthority::new);
        Collection<SimpleGrantedAuthority> authorities;
        try {
            authorities = stream.toList();
        } catch (Exception e) {
            authorities = Collections.emptyList();
        }
        return authorities;
    }

    public boolean validateToken(String authToken) {
        Jwts.parserBuilder().setSigningKey(saamfiClient.getPublicKey()).build().parseClaimsJws(authToken);
        return true;
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String authToken, UserDetails userDetails) {

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return new UsernamePasswordAuthenticationToken(userDetails, authToken.trim(), authorities);
    }

    public long getInstIdFromJWT(String authToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey(saamfiClient.getPublicKey()).build()
                .parseClaimsJws(authToken).getBody();
        return (int) claims.get(INSTITUTION_CLAIM);
    }

    public boolean tokenHasPermission(String token, String permissionName) {
        Claims claims = Jwts.parserBuilder().setSigningKey(saamfiClient.getPublicKey()).build().parseClaimsJws(token)
                .getBody();

        String roles = claims.get("role", String.class);

        return Arrays.asList(roles.split(",")).contains(permissionName);
    }

    public boolean loggedInUserHasPermission(List<String> permissionNames) {
        return permissionNames.stream().anyMatch(permissionName -> tokenHasPermission(
                (String) SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                permissionName));
    }

    public String getLoggedInUserUsername() {
        return getUsernameFromJWT((String) SecurityContextHolder.getContext().getAuthentication().getCredentials());
    }

}
