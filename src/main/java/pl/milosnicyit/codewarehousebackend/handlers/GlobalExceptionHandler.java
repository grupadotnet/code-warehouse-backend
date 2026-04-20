package pl.milosnicyit.codewarehousebackend.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HandlerDTO> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(new HandlerDTO(exception.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<HandlerDTO> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        return ResponseEntity.badRequest().body(new HandlerDTO(exception.getMessage()));
    }
}
