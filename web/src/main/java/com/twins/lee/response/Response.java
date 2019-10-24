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
     *
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
}
