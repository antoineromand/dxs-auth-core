package com.dxs.auth.core.response;

public class Response<T> {
    private boolean success;
    private T data;

    public Response(boolean isSuccess, T data) {
        this.success = isSuccess;
        this.data = data;
    }
}
