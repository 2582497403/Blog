package com.fanqie.blog.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
