package com.project.EMS.security.jwt;

import com.project.EMS.repository.UserRepository;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        log.info("Authorization header : {}", header);

        if (header != null && header.startsWith("Bearer ")){
            String token = header.substring(7);

            if (!jwtService.isAccessToken(token)){
                filterChain.doFilter(request, response);
                return;
            }
            try{
               Jws<Claims> parse =  jwtService.parse(token);
              Claims payload =  parse.getPayload();
              String userId = payload.getSubject();
              Long userLongId = Long.parseLong(userId);

              userRepository.findById(userLongId).ifPresent( user-> {
                  if (user.isEnabled()){
                      List<GrantedAuthority> authorities = user.getRoles() == null ? List.of():
                              user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());

                      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                              user.getUsername(),
                              null,
                              authorities
                      );
                      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        if (SecurityContextHolder.getContext().getAuthentication() == null)
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                  }
              });


            }catch (ExpiredJwtException e) {
                request.setAttribute("error", "Token Expired");
//                e.printStackTrace();

            }catch (MalformedJwtException e){

                request.setAttribute("error", "Invalid Token");
//                e.printStackTrace();
            } catch (JwtException e) {
                request.setAttribute("error", "Invalid Token");
//            e.printStackTrace();

            }catch (Exception e){
                request.setAttribute("error", "Invalid Token");
            }


        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{
        return request.getRequestURI().startsWith("/api/v1/auth");
    }




}
