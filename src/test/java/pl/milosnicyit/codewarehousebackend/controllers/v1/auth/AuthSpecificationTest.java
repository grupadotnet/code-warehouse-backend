package pl.milosnicyit.codewarehousebackend.controllers.v1.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.milosnicyit.codewarehousebackend.jwt.JWTService;
import pl.milosnicyit.codewarehousebackend.users.UsersService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.milosnicyit.codewarehousebackend.controllers.v1.RestConstant.PATH;
import static pl.milosnicyit.codewarehousebackend.helpers.JsonHelper.toJson;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthSpecificationTest {
    private static final String REGISTER_ENDPOINT = PATH + "auth/register";
    private static final String LOGIN_ENDPOINT = PATH + "auth/login";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsersService usersService;

    @MockitoBean
    private JWTService jwtService;

    @Test
    void shouldRegisterUserAndReturnOkWithToken() throws Exception {
        String username = "testUser";
        String password = "testPassword";
        String generatedToken = "mocked.jwt.token";

        when(usersService.registerUser(username, password)).thenReturn(generatedToken);

        mockMvc.perform(post(REGISTER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUserRequestJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(generatedToken));
    }

    @Test
    void shouldReturnBadRequestWhenRegistrationFails() throws Exception {

        when(usersService.registerUser("zajetyLogin", "testPassword")).thenReturn(null);

        mockMvc.perform(post(REGISTER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUserRequestJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isEmpty());
    }

    @Test
    void shouldLoginUserAndReturnOkWithToken() throws Exception {
        String username = "testUser";
        String password = "testPassword";
        String generatedToken = "mocked.jwt.token";

        when(usersService.loginUser(username, password)).thenReturn(generatedToken);

        mockMvc.perform(post(LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUserRequestJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(generatedToken));
    }

    @Test
    void shouldReturnUnauthorizedWhenLoginFails() throws Exception {
        String username = "testUser";
        String password = "testPassword";

        when(usersService.loginUser(username, password))
                .thenThrow(new org.springframework.security.authentication.BadCredentialsException("Bad credentials"));

        mockMvc.perform(post(LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUserRequestJson()))
                .andExpect(status().isUnauthorized());
    }

    private String getUserRequestJson() throws JsonProcessingException {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testUser");
        userRequest.setPassword("testPassword");
        return toJson(userRequest);
    }
}