package com.dxs.auth.core.service.double_factor_authentication;

public interface IProvider2FAService<T, I> {
    void send(T providerData, I credential);
}