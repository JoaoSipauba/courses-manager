package com.api.coursesmanager.exceptions.handle;

import com.api.coursesmanager.exceptions.EmailException;
import com.api.coursesmanager.models.Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<Exception> handleEmailException(EmailException exception) {
        Exception exceptionModel = Exception
                .builder()
                .timeStamp(LocalDateTime.now(ZoneId.of("UTC")))
                .message(exception.getMessage())
                .trace(exception.getCause().getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        log.error("System throws EmailException - {}", exceptionModel.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionModel);
    }
}
