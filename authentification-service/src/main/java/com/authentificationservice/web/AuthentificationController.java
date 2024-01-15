package com.authentificationservice.web;

import com.authentificationservice.entities.AuthenticationRequest;
import com.authentificationservice.entities.Redevable;
import com.authentificationservice.entities.Role;
import com.authentificationservice.service.RedevableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
@Slf4j
@CrossOrigin
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
        return new ResponseEntity<>(Map.of("errorMessage", "Cin and password are required"), HttpStatus.BAD_REQUEST);
    }
        log.info("User with CIN {} and password {}",authenticationRequest.getCin(),authenticationRequest.getPassword());
    boolean isAuthenticated = redevableService.authenticate(authenticationRequest);

    if (isAuthenticated) {
        String subject = authenticationRequest.getCin();
        String scope =redevableService.findByCin(authenticationRequest.getCin()).getRole().getLabel();
      //  Long id=redevableService.findByCin(authenticationRequest.getCin()).getId();
        Map<String, String> idToken = generateTokens(subject, scope);
        return new ResponseEntity<>(idToken, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(Map.of("errorMessage", "Authentication failed !"), HttpStatus.UNAUTHORIZED);
    }
}

        @PostMapping("/register")
        public ResponseEntity<Map<String, String>> register(@RequestBody Redevable redevable) {

            if (redevable.getCin() == null || redevable.getPassword() == null ||
                    redevable.getRole() == null) {
                return ResponseEntity.badRequest().body(Map.of("errorMessage", "CIN, password, role  are required"));
            }

            String hashedPassword = passwordEncoder.encode(redevable.getPassword());
            redevable.setPassword(hashedPassword);

            redevableKafkaTemplate.send("authentication-service", redevable);

            String subject = redevable.getCin();
            String scope = redevable.getRole().getLabel();
 //           Long id = redevable.getId();
            log.warn("redevable {} role {}",redevable.getRole().getLabel(),scope);
            log.info("subject {} role {}",subject,scope);
            Map<String, String> idToken = generateTokens(subject, scope);

            return ResponseEntity.ok(idToken);
        }

        private Map<String, String> generateTokens(String subject, String scope) {
            Instant instant = Instant.now();

            JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(60, ChronoUnit.MINUTES))
                    .issuer("authentication-service")
                    .claim("role", scope)
                  //  .claim("id", id)
                    .build();

            String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();

            JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(8, ChronoUnit.HOURS))
                    .issuer("authentication-service")
                    .build();

            // Encode Refresh Token
            String jwtRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();

            // Return tokens
            return Map.of("accessToken", jwtAccessToken, "refreshToken", jwtRefreshToken);
        }


}
