package com.huige.erp.common.exception;

import com.huige.erp.common.dto.BaseResponseResult;
import com.huige.erp.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @Author Z.xichao
 * @Create 2018-9-10
 * @Comments
 */

@RestControllerAdvice
public class AppExceptionHandler {

    @Value("${com.huige.erp.ac.loginEndpoint}")
    private String LoginEndpoint;

    @ExceptionHandler(BindException.class)
    public ResponseResult handlerBindException(BindException ex) {
        ex.printStackTrace();
        BindingResult bindingResult = ex.getBindingResult();
        String errorMessage = "";
        String dataMessage = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage += fieldError.getDefaultMessage() + "</br>";
            dataMessage += String.format("%s[%s],无效值%s  ", fieldError.getDefaultMessage(), fieldError.getField(), fieldError.getRejectedValue());
        }
        return new ResponseResult<>(BaseResponseResult.ERROR_PARM, errorMessage, dataMessage);
    }

    @ExceptionHandler(BaseAppException.class)
    public ResponseResult handlerBaseAppException(BaseAppException ex) {
        ex.printStackTrace();
        if (ex instanceof AppNotAuthException) {
            return new ResponseResult(BaseResponseResult.ERROR_AUTH, ex.getMessage());
        } else if (ex instanceof AppParmMissException) {
            return new ResponseResult(BaseResponseResult.ERROR_PARM, ex.getMessage());
        } else if (ex instanceof AppCommonException) {
            return new ResponseResult(BaseResponseResult.ERROR, ex.getMessage());
        } else if (ex instanceof AppRedirectException) {
            return new ResponseResult<>(BaseResponseResult.REDIRECT, ex.getMessage(), ((AppRedirectException) ex).getUrl());
        } else {
            return new ResponseResult(BaseResponseResult.FAILURE, ex.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult handlerBaseAppException(Exception ex) {
        ex.printStackTrace();
        return new ResponseResult(BaseResponseResult.FAILURE, ex.getMessage());
    }
}
