package com.revature.amsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> meth1(AccountNotFoundException accountNotFoundException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = accountNotFoundException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> meth2(CustomerNotFoundException customerNotFoundException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = customerNotFoundException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> meth3(RoleNotFoundException roleNotFoundException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = roleNotFoundException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TransactionNotFoundException.class)
    public ResponseEntity<ErrorResponse> meth4(TransactionNotFoundException transactionNotFoundException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = transactionNotFoundException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> meth5(UserNotFoundException userNotFoundException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = userNotFoundException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidLoginException.class)
    public ResponseEntity<ErrorResponse> meth6(InvalidLoginException invalidLoginException) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message = invalidLoginException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = MissingFieldException.class)
    public ResponseEntity<ErrorResponse> meth7(MissingFieldException missingFieldException) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = missingFieldException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity<ErrorResponse> meth8(InvalidInputException invalidInputException) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = invalidInputException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
