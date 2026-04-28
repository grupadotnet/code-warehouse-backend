package pl.milosnicyit.codewarehousebackend.controllers.v1.location;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import pl.milosnicyit.codewarehousebackend.location.Location;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.milosnicyit.codewarehousebackend.helpers.JsonHelper.toJson;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LocationControllerE2ETest {

    private static final String LOCATION_ENDPOINT = "/api/locations";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void shouldCreateLocationEndToEnd() throws Exception {
        Location location = new Location();
        location.setName("Magazyn E2E");

        mockMvc.perform(post(LOCATION_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(location)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Magazyn E2E"));
    }

    @Test
    @WithMockUser
    void shouldGetAllLocationsEndToEnd() throws Exception {
        mockMvc.perform(get(LOCATION_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}