package com.bci.user.exception;

import com.bci.user.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDTO handleGenericException(Exception e) {
        return new ErrorResponseDTO("Error interno. Contacte al administrador.");
    }

    @ExceptionHandler({FormatValidationException.class,
            DuplicatedEmailException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDTO handleInvalidParameters(Exception e) {
        return new ErrorResponseDTO(e.getLocalizedMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDTO handleUserNotFoundException(Exception e) {
        return new ErrorResponseDTO(e.getLocalizedMessage());
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponseDTO handleUnauthorizedUserException(Exception e) {
        return new ErrorResponseDTO(e.getLocalizedMessage());
    }
}
