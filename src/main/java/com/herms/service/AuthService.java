package com.herms.service;

import com.herms.entity.UserEntity;
import com.herms.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.security.KeyPairGenerator;
import java.util.UUID;

@ApplicationScoped
public class AuthService {

    @Inject
    private UserRepository userRepository;

    public String generateNewApiSecret(){
        String apiSecret;
        do {
            apiSecret = UUID.randomUUID().toString();
        } while (userRepository.getByApiSecret(apiSecret) != null);

        return apiSecret;
    }
}
