package com.dxs.auth.core.external.repository;

import com.dxs.auth.core.entity.AbstractUser;

import java.util.Optional;

public interface UserRepository<T extends AbstractUser> {
    Optional<T> findUserByEmail(String email);
    T save(T user);
}
