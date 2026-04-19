package pl.milosnicyit.codewarehousebackend.handlers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void shouldHandleIllegalArgumentExceptionAndReturnBadRequest() {
        String expectedMessage = "Nieprawidłowy argument testowy";
        IllegalArgumentException exception = new IllegalArgumentException(expectedMessage);

        ResponseEntity<HandlerDTO> response = exceptionHandler.handleIllegalArgumentException(exception);

        assertNotNull(response, "Response entity should not be null");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "HTTP status should be 400 BAD REQUEST");

        HandlerDTO body = response.getBody();
        assertNotNull(body, "Response body should not be null");

        assertEquals(expectedMessage, body.getError(), "Exception message should be mapped to DTO");
    }
}