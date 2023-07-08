package com.example.thread.Auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
import com.example.thread.User.service.UserService;
=======
import com.example.thread.User.model.User;
import com.example.thread.User.repo.UserRepo;
>>>>>>> f495601 (Added Role in JwtToken)

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Autowired
<<<<<<< HEAD
    private UserService userService;
=======
    private UserRepo userRepo;
>>>>>>> f495601 (Added Role in JwtToken)

    private static final String SECRET_KEY = "mohit_raghav_lcs32";

    private static final int TOKEN_VALIDITY = 3600 * 5;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
<<<<<<< HEAD

=======
        User user = userRepo.findByUserName(userDetails.getUsername());
>>>>>>> f495601 (Added Role in JwtToken)
        // Token Validity = 18 sec
        // EXpiration = 5 hrs
        List<String> roles = new ArrayList<>();
        userDetails.getAuthorities().forEach(r -> {
            roles.add(new String(r.getAuthority()));
        });
<<<<<<< HEAD
        claims.put("roles", roles);
=======
        String userProfile = user.getProfileImage();
        String userName = user.getUserName();
        String userFullName = user.getUserFullName();
        claims.put("roles", roles);
        claims.put("userName", userName);
        claims.put("userFullName", userFullName);
        claims.put("userProfileImage", userProfile);
>>>>>>> f495601 (Added Role in JwtToken)

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
