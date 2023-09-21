package com.example.securitytask.filter;


import com.example.securitytask.models.User;
import com.example.securitytask.repositories.UserRepository;
import com.example.securitytask.services.DefaultUserServiceImpl;
import com.example.securitytask.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private DefaultUserServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String jwtfromHeader = null;

        if(authorizationHeader!= null && authorizationHeader.startsWith("Bearer ")){
            jwtfromHeader = authorizationHeader.substring(7);
            System.out.println("From Header: "+ jwtfromHeader);

        }
        System.out.println("Filter:"+request.getRequestURI());


        Cookie [] cookies = request.getCookies();

        if(cookies!= null){
            System.out.println("Cookies not null");
            List<Cookie> listOfCookies = List.of(cookies);
            Cookie jwtCookie = null;
            boolean jwtPresent = false;
            boolean v1JwtPresent = false;
            for (Cookie temp: cookies) {
                if ((temp.getName()).equals("BusinessJwtToken")) {
                    jwtPresent = true;
                    jwtCookie = temp;

                }
            }

            String username = null;
            String jwt = null;

            if(jwtPresent){
                jwt = jwtCookie.getValue();
                username = jwtUtil.extractUsername(jwt);
                System.out.println("Extracted username: "+ username);
            }



//            if((request.getRequestURI()).equals("/v1")){
//                System.out.println("v1 condition "+ jwtPresent +" "+jwtCookie.getName() +" "+ username);
//            if(jwtPresent && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                System.out.println();
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username.replace("_v1user",""));
//                String validatingName = userDetails.getUsername() +"_v1user";
//
//                if (jwtUtil.validateToken(jwt, validatingName)) {
//                    System.out.println("validated");
//                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//                    usernamePasswordAuthenticationToken
//                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//                }
//            }
//                if(userDetailsService.isSecure(jwtfromHeader)){
//                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username.replace("_v1user",""));
//
//
//                    System.out.println("validated");
//                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//                    usernamePasswordAuthenticationToken
//                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
//                }
//
//            }
            System.out.println("Before if: "+request.getRequestURI());
            if((request.getRequestURI().equals("/dashboard")) || (request.getRequestURI().equals("/v1/token/generate"))){
//            System.out.println("dashboard condition "+ jwtPresent +" "+jwtCookie.getName() +" "+ username);
                System.out.println("dash: "+ username);
                if(jwtPresent && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                    System.out.println("/dash "+ userDetails.getUsername());
                    if (jwtUtil.validateToken(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }

            }


        }
        else {
            System.out.println("cookies == null");
            System.out.println("cookiesnull:"+(request.getRequestURI()));
            if((request.getRequestURI()).startsWith("/v1")) {
                System.out.println("/v1 entered ");
                if (userDetailsService.isSecure(jwtfromHeader)) {
                    System.out.println("jwtFromHeader2: " + jwtfromHeader);

                    Optional<User> user = userDetailsService.getUserDetailsFromJwt(jwtfromHeader);
//                    System.out.println(user.get().getUserName());
                    if(user.get().isApiUser()){
                        UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.get().getEmail());
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    }
                    System.out.println("Not an api user");

                }
//                else{
//                    response.sendRedirect("/login");
//                }
            }

        }



//            if(jwtPresent && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                System.out.println();
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username.replace("_v1user",""));
//                String validatingName = userDetails.getUsername() +"_v1user";
//
//                if (jwtUtil.validateToken(jwt, validatingName)) {
//                    System.out.println("validated");
//                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//                    usernamePasswordAuthenticationToken
//                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//                }
//            }
//            if(userDetailsService.isSecure(jwtfromHeader)){
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username.replace("_v1user",""));
//
//
//                System.out.println("validated");
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
//            }






        filterChain.doFilter(request, response);


    }
}

