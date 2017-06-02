package com.xy.inc.controller.handle;

import com.xy.inc.exception.ValidationException;
import com.xy.inc.utils.Utils;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Matheus Xavier
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {ValidationException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<String> handleValidationParamsException(Exception e) {
        String message = "";
        
        if (e instanceof ValidationException) {
            message = e.getMessage();
        } else if (e instanceof MissingServletRequestParameterException) {
            message = "Infome o parametro ".concat(((MissingServletRequestParameterException) e).getParameterName()).concat(".");
        }
        
        return buildResponse(message, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
        return buildResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> buildResponse(String message, HttpStatus status) {
        Map responseParams = new HashMap();
        responseParams.put("message", message);

        return buildResponse(responseParams, status);
    }

    private ResponseEntity<String> buildResponse(Map responseParams, HttpStatus status) {
        return new ResponseEntity(Utils.convertToJsonString(responseParams), status);
    }
}
