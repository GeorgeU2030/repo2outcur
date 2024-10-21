package co.edu.icesi.dev.outcome_curr_mgmt.saamfi.filters;


import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.util.SaamfiJwtTools;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class SaamfiAuthenticationFilter extends OncePerRequestFilter {
	//TODO add test coverage
	@Value("${saamfi.system.id}")
	private String systemId;

	@Value("${saamfi.institution.id}")
	private String institution;
	/**
	 * String to retrieve authentication in header
	 */
	public static final String HEADER_STRING = "Authorization";

	/**
	 * String to remove the token prefix
	 */
	public static final String TOKEN_PREFIX = "Bearer";

	private final SaamfiJwtTools saamfiJwtTools;

	/**
	 * provider of JWT methods
	 */

	@Override
	protected void doFilterInternal(HttpServletRequest request,@Nonnull HttpServletResponse response, @Nonnull  FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader(HEADER_STRING);
		String username = null;
		String authToken = null;

		long sysid = 1;
		long instid = 1;

		boolean tokenValid = false;
		if (header != null && !header.equals("Bearer ") && header.startsWith(TOKEN_PREFIX)) {
			authToken = header.replace(TOKEN_PREFIX, "").trim();
			logger.info("\n\ntoken: " + authToken + "\n\n");
			if (!authToken.trim().equals("null")) {
				try {
					if (saamfiJwtTools.validateToken(authToken)) {
						username = saamfiJwtTools.getUsernameFromJWT(authToken);
						sysid = saamfiJwtTools.getSysIdFromJWT(authToken);
						instid = saamfiJwtTools.getInstIdFromJWT(authToken);

						tokenValid = true;
					}
				} catch (Exception e) {
					manageTokenInvalid(e, response);
					return;
				}

			}
		} else {
			logger.warn("couldn't find bearer string, will ignore the header");
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (tokenValid && username != null && auth == null && systemId.equals(sysid+"")
				&& institution.equals(instid+"")) {
			Collection<SimpleGrantedAuthority> roles = saamfiJwtTools.getPermissionNamesFromJWT(authToken);
			if (roles == null) {
				roles = Collections.emptyList();
			}
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, "", roles);

			UsernamePasswordAuthenticationToken authentication = saamfiJwtTools.getAuthentication(authToken, userDetails);
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			logger.info("usr:" + username + ", module auth, path:" + request.getServletPath());
			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		filterChain.doFilter(request, response);

	}

	//TODO include logger
	private void manageTokenInvalid(Exception exception, HttpServletResponse response) {
		response.setContentType("application/json;charset=UTF-8");
		HashMap<String, String> responseBo = new HashMap<>();
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		responseBo.put("message", exception.getMessage());

		JSONObject responseJson = new JSONObject(responseBo);
		try {
			response.getWriter().write(responseJson.toJSONString());
		} catch (IOException e) {
			logger.error("error writing token"+ e.getMessage());
		}
	}

}
