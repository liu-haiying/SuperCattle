package com.black.cattle.utils;

import com.black.cattle.base.ResultDto;

public class ResultUtils {

    public static <T> ResultDto<T> ok(T data) {
        return new ResultDto<T>(200, "", data);
    }

    public static ResultDto fail(String msg) {
        return new ResultDto(500, msg, null);
    }
}
