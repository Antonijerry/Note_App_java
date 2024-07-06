package com.Antoine.Jerry.note_app.config.filters;

import com.Antoine.Jerry.note_app.service.JwtTokenService;
import com.Antoine.Jerry.note_app.service.auth.MyCustomerDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;


    @Autowired
    private MyCustomerDetailsService myCustomerDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //set authentication header
        String authHeader = request.getHeader("Authorization");

        //get jwt property
        String jwtToken = null;

        //set username property
        String userEmail = null;

        //check / validate authorization header if block
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            //kill the code execution here
            return;
        }

        //set jwt value
        jwtToken = authHeader.substring(7);

        //extract username from token
        userEmail = jwtTokenService.extractUsername(jwtToken);

        //check if user is null and is authenticated
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() ==null){
            //set user details object
            UserDetails userDetails = myCustomerDetailsService.loadUserByUsername(userEmail);

            //validate token if block
            if (jwtTokenService.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken userAuthToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                userAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userAuthToken);
            }
            //end of validate token if block
        }
        //end of check if user is null

        //move to the next filter
        filterChain.doFilter(request,response);

    }
}
