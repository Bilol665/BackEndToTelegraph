package uz.pdp.backendtotelegraph.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.backendtotelegraph.service.AuthenticationService;
import uz.pdp.backendtotelegraph.service.JwtService;

import java.io.IOException;

@AllArgsConstructor
public class JwtFilterToken extends OncePerRequestFilter {
    private AuthenticationService authenticationService;
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }
        token = token.substring(7);
        Jws<Claims> claimsJws = jwtService.extractToken(token);

        authenticationService.authenticate(claimsJws.getBody(),request);
        filterChain.doFilter(request,response);
    }
}
