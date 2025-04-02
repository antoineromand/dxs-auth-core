package com.dxs.auth.core.external.repository;

import com.dxs.auth.core.entity.AbstractUser;

import java.util.Optional;

public interface AbstractUserRepository<T extends AbstractUser> {
    Optional<T> findByEmail(String email);
    T save(T user);
}
