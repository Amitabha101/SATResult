package com.precize.io.app.exceptions;

import com.precize.io.app.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class CentralExeptionHandler {
    @ExceptionHandler(InvalidAddressException.class)
    public ResponseEntity<ErrorDTO> InvalidArgumentExceptionHandler(InvalidAddressException exception) {
        ErrorDTO dto = new ErrorDTO(HttpStatus.BAD_REQUEST, "Invalid address provided kindly check");

        dto.setDetailedMessages(Collections.singletonList(exception.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> AccountNotFoundExceptionHandler(NotFoundException exception) {
        ErrorDTO dto = new ErrorDTO(HttpStatus.NOT_FOUND, "Resource not found error");

        dto.setDetailedMessages(Collections.singletonList(exception.getMessage()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }
}
