package com.dxs.auth.core.output;

public class Response<T> {
    private boolean success;
    private String error;
    private T data;

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.success = true;
        response.data = data;
        return response;
    }

    public static <T> Response<T> error(String message) {
        Response<T> response = new Response<>();
        response.success = false;
        response.error = message;
        return response;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }
}
