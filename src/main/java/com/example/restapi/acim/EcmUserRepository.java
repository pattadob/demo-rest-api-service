package com.example.restapi.acim;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

interface EcmUserRepository extends ListCrudRepository<EcmUser, String> {
    
    Optional<EcmUser> findById(String id);
}
