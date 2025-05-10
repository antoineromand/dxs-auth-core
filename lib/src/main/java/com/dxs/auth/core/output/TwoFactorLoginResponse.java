package com.dxs.auth.core.output;

import java.util.UUID;

public record  TwoFactorLoginResponse(UUID sessionId, String code) {}
