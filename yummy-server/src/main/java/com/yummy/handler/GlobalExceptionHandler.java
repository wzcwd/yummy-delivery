package com.yummy.handler;

import com.yummy.exception.BaseException;
import com.yummy.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  Global Exception Handler，handle with the exception to service
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * catch the exception to service
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("exception message：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

}
