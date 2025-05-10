package com.dxs.auth.core.repository;

import java.util.Optional;

import com.dxs.auth.core.entity.AbstractUser;

public interface AbstractUserRepository<T extends AbstractUser> {
    Optional<T> findByEmail(String email);
    T save(T user);
}
