package com.twins.lee.response;


import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
public class Response<T> {
    public static final int Success = 0;
    public static final int Failed = 1;
    public static final int Exception = 2;
    public static final int NoAuthen = 3;
    public static final int NoRight = 4;
    public static final int UnknownUser = 5;
    public static final int PasswordError = 6;
    public static final int UserStateError = 7;
    @NotNull
    int code;
    @Nullable
    T data;
    @Nullable
    String msg;

    /**
     * @param code
     * @param data
     */
    public Response(int code, T data) {
        this(code, data, "success");
    }

    public Response(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> Response custom(int code, T data, String msg) {
        return new Response(code, data, msg);
    }

    public static <T> Response success(T data) {
        return Response.success(data, "success");
    }

    public static Response success(String msg) {
        return Response.success(null, msg);
    }

    public static Response success() {
        return Response.success(null, "success");
    }

    public static <T> Response success(T data, String msg) {
        return new Response(Response.Success, data, msg);
    }


    public static Response error() {
        return Response.error(Response.Failed, null, null);
    }

    public static <T> Response error(int code, T error, String msg) {
        return new Response<>(code, error, msg);
    }

    public static Response error(int code, String msg) {
        return Response.error(code, null, msg);
    }
}
