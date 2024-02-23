package com.cosminpetrea.event_planning.security;

import com.cosminpetrea.event_planning.entities.User;
import com.cosminpetrea.event_planning.exceptions.UnauthorizedException;
import com.cosminpetrea.event_planning.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String autHeader = request.getHeader("Authorization");
        if (autHeader == null || !autHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Please insert token");
        } else {
            String accessToken = autHeader.substring(7);
            //verifico la signature se viene manipolata o scaduto
            jwtTools.verifyToken(accessToken);

            //Cerco l'id per trovare l'utente nel db
            String id = jwtTools.extractIdFromToken(accessToken);
            User user = userService.findById(UUID.fromString(id));

            //Avvisare spring che l'utente è autenticato

            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        //Spiego al programma in che situazioni non attivare il filtro
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }

}
