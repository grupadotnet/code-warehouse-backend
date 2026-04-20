package pl.milosnicyit.codewarehousebackend.password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PasswordEncoderSpecificationTest {

    @Test
    void shouldCreatePasswordEncoderServiceBean() {
        PasswordEncoderConfiguration configuration = new PasswordEncoderConfiguration();

        PasswordEncoderService service = configuration.passwordEncoderService();


        assertNotNull(service, "PasswordEncoderService bean should not be null");
        assertInstanceOf(PasswordEncoderBasicService.class, service, "Bean should be an instance of PasswordEncoderBasicService");
    }
}