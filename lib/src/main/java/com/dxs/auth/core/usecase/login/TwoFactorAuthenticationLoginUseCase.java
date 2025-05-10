package com.dxs.auth.core.usecase.login;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

import com.dxs.auth.core.entity.AbstractUser;
import com.dxs.auth.core.output.Response;
import com.dxs.auth.core.output.TwoFactorLoginResponse;
import com.dxs.auth.core.service.double_factor_authentication.IProvider2FAService;

public class TwoFactorAuthenticationLoginUseCase<T extends AbstractUser>
        implements ILoginUseCase<T, TwoFactorLoginResponse> {

    private final IProvider2FAService<TwoFactorLoginResponse, String> iProvider2FAService;

    public TwoFactorAuthenticationLoginUseCase(IProvider2FAService<TwoFactorLoginResponse, String> iProvider2FAService) {
        this.iProvider2FAService = iProvider2FAService;
    }

    @Override
    public Response<TwoFactorLoginResponse> login(T user) {
        String code = generateAlphaNumericCode(6);
        UUID responseId = UUID.randomUUID();
        TwoFactorLoginResponse response = new TwoFactorLoginResponse(responseId, code);
        this.iProvider2FAService.send(response, user.getEmail());
        return Response.success(response);
    }

    public static String generateAlphaNumericCode(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

}
