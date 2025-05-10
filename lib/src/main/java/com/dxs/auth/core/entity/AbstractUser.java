package com.dxs.auth.core.entity;

import java.util.UUID;

public abstract class AbstractUser {
    public abstract UUID getId();
    public abstract String getEmail();
    public abstract String getPassword();
    public abstract RoleEnum getRole();
    public abstract boolean is2FAActive();
}
