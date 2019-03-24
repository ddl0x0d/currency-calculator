package com.github.ddl0x0d.calculator.common.web;

import com.github.ddl0x0d.calculator.common.dto.Error;
import com.github.ddl0x0d.calculator.common.dto.ErrorResponse;
import com.github.ddl0x0d.calculator.common.dto.FieldError;
import com.github.ddl0x0d.calculator.common.validation.CalculatorException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.github.ddl0x0d.calculator.common.validation.ErrorCodes.UNEXPECTED_ERROR;
import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@Setter
@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler implements MessageSourceAware {

    private MessageSource messageSource;

    @ExceptionHandler
    public ResponseEntity<Object> handleCalculatorException(CalculatorException ex) {
        return internalServerError(ex.getErrorCode(), ex.getArguments());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleUnexpectedError(RuntimeException ex) {
        return internalServerError(UNEXPECTED_ERROR, ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        Stream<Error> errorStream = bindingResult.getGlobalErrors().stream()
            .map(error -> new Error(error.getDefaultMessage()));
        Stream<FieldError> fieldErrorStream = bindingResult.getFieldErrors().stream()
            .map(error -> new FieldError(error.getField(), error.getDefaultMessage()));
        Error[] errors = Stream.concat(errorStream, fieldErrorStream).toArray(Error[]::new);
        return errorResponse(status, errors);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {
        return errorResponse(status, new Error(getBaseMessage(ex)));
    }

    private ResponseEntity<Object> internalServerError(String code, Object... args) {
        return errorResponse(INTERNAL_SERVER_ERROR, createError(code, args));
    }

    private ResponseEntity<Object> errorResponse(HttpStatus status, Error... errors) {
        log.error("Request failed with status {} and errors: {}", status, Arrays.toString(errors));
        return new ResponseEntity<>(new ErrorResponse(asList(errors)), status);
    }

    private Error createError(String errorCode, Object... args) {
        return new Error(messageSource.getMessage(errorCode, args, null));
    }

    /**
     * Reverse operation of {@link NestedExceptionUtils#buildMessage(String, Throwable)}
     */
    private String getBaseMessage(Exception ex) {
        String message = ex.getMessage();
        return ex instanceof NestedRuntimeException
            ? message.substring(0, message.indexOf("; nested exception is"))
            : message;
    }
}
