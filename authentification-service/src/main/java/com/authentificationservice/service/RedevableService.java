package com.authentificationservice.service;

import com.authentificationservice.entities.AuthenticationRequest;
import com.authentificationservice.entities.Redevable;
import com.authentificationservice.repository.RedevableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RedevableService {

    @Autowired
    private RedevableRepository redevableRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Boolean authenticate(AuthenticationRequest request) {
        Optional<Redevable> optionalRedevable = redevableRepository.findByCin(request.getCin());

        if (optionalRedevable.isPresent()) {
            Redevable redevable = optionalRedevable.get();

            boolean passwordMatches = passwordEncoder.matches(request.getPassword(), redevable.getPassword());

            if (passwordMatches) {
                log.info("Authentication successful for user with CIN {}", request.getCin());
                return true;
            } else {
                log.warn("Authentication failed for user with CIN {}: Incorrect password", request.getCin());
            }
        } else {
            log.warn("Authentication failed: User with CIN {} not found", request.getCin());
        }

        // Authentication failed
        return false;
    }

    public Redevable findByCin(String cin) {
        return this.redevableRepository.findByCin(cin).get();
    }

}
