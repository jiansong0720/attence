package com.song.attence.base;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误码的说明
     */
    private String message;

    /**
     * 异常信息
     */
    private String errorsMessage;

    public BusinessException() {
        super();
        this.code = Errors.SUCCESS.code;
        this.message = Errors.SUCCESS.message;
    }

    public BusinessException(Errors errors) {
        super(errors.message);
        this.code = errors.code;
        this.message = errors.message;
    }

    public BusinessException(Errors errors, String message) {
        super(message);
        this.code = errors.code;
        this.message = errors.message;
        this.errorsMessage=message;
    }

    public BusinessException(Errors errors, Throwable cause) {
        super(errors.message, cause);
        this.code = errors.code;
        this.message = errors.message;
    }

}
