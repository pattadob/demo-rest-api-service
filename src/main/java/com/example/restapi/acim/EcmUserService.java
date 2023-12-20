package com.example.restapi.acim;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EcmUserService {

    private static final Logger log = LoggerFactory.getLogger(EcmUserService.class);
    private final EcmUserRepository ecmUserRepository;

    public EcmUserService(EcmUserRepository ecmUserRepository) {
        this.ecmUserRepository = ecmUserRepository;
    }

    public List<AcimUser> findAll() {
        return ecmUserRepository.findAll()
                .stream()
                .map(internalUserToAcimUser)
                .collect(Collectors.toList());
    }

    Function<EcmUser, AcimUser> internalUserToAcimUser = new Function<EcmUser, AcimUser>() {
        @Override
        public AcimUser apply(EcmUser ecmUser) {
            return new AcimUser(ecmUser.id, ecmUser.firstName, ecmUser.lastName, null, null, null, null, null, null,
                    null, null);
        }
    };

    public List<String> createUser(List<AcimUser> users) {
        List<EcmUser> ecmUserList = transformAcimUserToEcmUser(users);
        List<EcmUser> saved = ecmUserRepository.saveAll(ecmUserList);
        return saved.stream()
                .filter(user -> user.createdDate == null)
                .map(user -> user.id)
                .collect(Collectors.toList());
    }

    Function<AcimUser, EcmUser> externalUserToUserEcm = new Function<AcimUser, EcmUser>() {
        @Override
        public EcmUser apply(AcimUser acimUser) {
            return new EcmUser(
                    acimUser.username(),
                    acimUser.firstName(),
                    acimUser.lastName(),
                    "Active");
        }
    };

    List<EcmUser> transformAcimUserToEcmUser(List<AcimUser> users) {
        return users.stream()
                .map(externalUserToUserEcm)
                .collect(Collectors.toList());
    }

    public List<String> updateUser(List<AcimUser> users) {
        List<String> failList = new ArrayList<String>();
        for (AcimUser acimUser : users) {
            Optional<EcmUser> existing = ecmUserRepository.findById(acimUser.username());
            if (existing.isPresent()) {
                EcmUser updateEcmUser = new EcmUser(existing.get().id, acimUser.firstName(), acimUser.lastName(),
                        existing.get().status);
                ecmUserRepository.save(updateEcmUser);
            } else {
                failList.add(acimUser.username());
            }
        }
        return failList;
    }

    public List<String> deleteUser(List<AcimUser> users) {
        List<String> failList = new ArrayList<String>();
        for (AcimUser acimUser : users) {
            ecmUserRepository.deleteById(acimUser.username());
            Optional<EcmUser> existing = ecmUserRepository.findById(acimUser.username());
            if (!existing.isPresent()) {
                failList.add(acimUser.username());
            }
        }
        return failList;
    }

}
