package com.black.cattle.base;

import lombok.Data;

@Data
public class ResultDto<T> {

    private Integer code;

    private String msg;

    private T data;

    public ResultDto(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
