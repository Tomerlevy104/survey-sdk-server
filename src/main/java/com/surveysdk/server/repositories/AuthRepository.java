package com.surveysdk.server.repositories;

import com.surveysdk.server.entities.AuthEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthRepository extends MongoRepository<AuthEntity, String> {
    Optional<AuthEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
