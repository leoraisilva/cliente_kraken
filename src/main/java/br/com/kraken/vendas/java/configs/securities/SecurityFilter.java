package br.com.kraken.vendas.java.configs.securities;

import br.com.kraken.vendas.java.repository.interfaces.iClienteRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenServices tokenServices;
    private final iClienteRepository repository;

    public SecurityFilter (TokenServices tokenServices, iClienteRepository repository) {
        this.tokenServices = tokenServices;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        String path = request.getRequestURI();
        String method = request.getMethod();
        if(path.equals("/cliente") && method.equals("POST")){
            filterChain.doFilter(request, response);
            return;
        }
        if(token != null){
            var subject = tokenServices.ValidationToken(token);
            UserDetails user = repository.findByUsuario(subject);

            var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);

    }

    private String recoverToken (HttpServletRequest request) {
        var authHeader = request.getHeader("authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}