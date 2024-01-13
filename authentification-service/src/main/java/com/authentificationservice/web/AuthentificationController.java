package com.authentificationservice.web;

import com.authentificationservice.entities.AuthenticationRequest;
import com.authentificationservice.entities.Redevable;
import com.authentificationservice.service.RedevableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.TimeoutException;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/auth")
@Slf4j
// @RequiredArgsConstructor
public class AuthentificationController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private KafkaTemplate<String, Redevable> redevableKafkaTemplate;

    @Autowired
    private RedevableService redevableService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        if (authenticationRequest.getCin() == null || authenticationRequest.getPassword() == null) {
        return new ResponseEntity<>(Map.of("errorMessage", "Username and password are required"), HttpStatus.BAD_REQUEST);
    }

    // Authenticate the user using RedevableService
    boolean isAuthenticated = redevableService.authenticate(authenticationRequest);

    if (isAuthenticated) {
        // Authentication successful, generate token
        String subject = authenticationRequest.getCin(); // Assuming username is CIN
        String scope = "USER";
        Map<String, String> idToken = generateTokens(subject, scope);
        return new ResponseEntity<>(idToken, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(Map.of("errorMessage", "Authentication failed"), HttpStatus.UNAUTHORIZED);
    }
}

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Redevable redevable) {
        if (redevable == null || redevable.getCin() == null || redevable.getPassword() == null) {
            return new ResponseEntity<>(Map.of("errorMessage", "CIN and password are required"), HttpStatus.BAD_REQUEST);
        }
        String hashedPassword = passwordEncoder.encode(redevable.getPassword());
        redevable.setPassword(hashedPassword);
        redevableKafkaTemplate.send("authentication-service", redevable);

        String subject = redevable.getCin();
        String scope = "USER";

        Map<String, String> idToken = generateTokens(subject, scope);

        return new ResponseEntity<>(idToken, HttpStatus.OK);
    }

    private Map<String, String> generateTokens(String subject, String scope) {
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(60, ChronoUnit.MINUTES))
                .issuer("authentication-service")
                .claim("scope", scope)
                .build();

        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();

        JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(8, ChronoUnit.HOURS))
                .issuer("authentication-service")
                .build();

        String jwtRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();

        Map<String, String> idToken = new HashMap<>();
        idToken.put("accessToken", jwtAccessToken);
        idToken.put("refreshToken", jwtRefreshToken);

        return idToken;
    }

/*
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(String grantType, String username, String password, boolean withRefreshToken, String refreshToken){
        String subject=null;
        String scope=null;
        if(grantType.equals("password")){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            subject=authentication.getName();
            scope=authentication.getAuthorities()
                    .stream().map(aut -> aut.getAuthority())
                    .collect(Collectors.joining(" "));

        } else if(grantType.equals("refreshToken")){
            if(refreshToken==null) {
                return new ResponseEntity<>(Map.of("errorMessage","Refresh  Token is required"), HttpStatus.UNAUTHORIZED);
            }
            Jwt decodeJWT = null;
            try {
                decodeJWT = jwtDecoder.decode(refreshToken);
            } catch (JwtException e) {
                return new ResponseEntity<>(Map.of("errorMessage",e.getMessage()), HttpStatus.UNAUTHORIZED);
            }
            subject=decodeJWT.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            scope=authorities.stream().map(auth->auth.getAuthority()).collect(Collectors.joining(" "));
        }
        Map<String, String> idToken=new HashMap<>();
        Instant instant=Instant.now();
        JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(withRefreshToken?1:5, ChronoUnit.MINUTES))
                .issuer("authentification-service")
                .claim("scope",scope)
                .build();
        String jwtAccessToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        idToken.put("accessToken",jwtAccessToken);
        if(withRefreshToken){
            JwtClaimsSet jwtClaimsSetRefresh=JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
                    .issuer("authentification-service")
                    .build();
            String jwtRefreshToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
            idToken.put("refreshToken",jwtRefreshToken);
        }
        return new ResponseEntity<>(idToken,HttpStatus.OK);
    }
    */
}
