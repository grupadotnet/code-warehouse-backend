package pl.milosnicyit.codewarehousebackend.controllers.v1.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.milosnicyit.codewarehousebackend.controllers.v1.RestConstant.PATH;
import static pl.milosnicyit.codewarehousebackend.helpers.JsonHelper.toJson;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerE2ETest {
    private static final String REGISTER_ENDPOINT = PATH + "auth/register";
    private static final String LOGIN_ENDPOINT = PATH + "auth/login";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRegisterNewUserEndToEnd() throws Exception {
        String uniqueUsername = "e2e_ddffgdfdfdf";
        String password = "realSecretPassword123!";

        mockMvc.perform(post(REGISTER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUserRequestJson(uniqueUsername, password)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.token").isString());
    }

    @Test
    void shouldReturnEmptyTokenWhenUserAlreadyExistsEndToEnd() throws Exception {
        String duplicateUsername = "e2e_duplicate_";
        String password = "realSecretPassword123!";
        String jsonRequest = getUserRequestJson(duplicateUsername, password);

        mockMvc.perform(post(REGISTER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());

        mockMvc.perform(post(REGISTER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void shouldLoginSuccessfullyEndToEnd() throws Exception {
        String username = "login_e2e_user";
        String password = "securePassword123";

        mockMvc.perform(post(REGISTER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUserRequestJson(username, password)))
                .andExpect(status().isOk());

        mockMvc.perform(post(LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUserRequestJson(username, password)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    void shouldFailLoginWhenPasswordIsIncorrectEndToEnd() throws Exception {
        String username = "wrong_pass_user";
        String password = "correctPassword";

        mockMvc.perform(post(REGISTER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUserRequestJson(username, password)))
                .andExpect(status().isOk());

        mockMvc.perform(post(LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUserRequestJson(username, "securePassword123")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldFailLoginWhenUserDoesNotExistEndToEnd() throws Exception {
        String unknownUser = "i_dont_exist_in_db";

        mockMvc.perform(post(LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUserRequestJson(unknownUser, "securePassword123")))
                .andExpect(status().isNotFound());
    }

    private String getUserRequestJson(String username, String password) throws JsonProcessingException {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(username);
        userRequest.setPassword(password);
        return toJson(userRequest);
    }
}