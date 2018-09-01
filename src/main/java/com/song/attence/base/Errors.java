package com.song.attence.base;

/**
 * 错误码
 */
public enum Errors {

    SUCCESS(0, "操作成功"),
    DATA_NOT_EXISTED(1, "数据不存在");

    public Integer code;

    public String message;

    Errors(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
