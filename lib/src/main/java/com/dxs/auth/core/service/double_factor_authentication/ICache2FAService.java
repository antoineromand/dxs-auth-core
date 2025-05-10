package com.dxs.auth.core.service.double_factor_authentication;

public interface ICache2FAService<T> {
    void store2FAContext(T context);
    T getContextId(String contextId);
}
