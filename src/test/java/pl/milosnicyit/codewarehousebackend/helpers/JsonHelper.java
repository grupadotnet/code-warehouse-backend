package pl.milosnicyit.codewarehousebackend.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;

public final class JsonHelper {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(@NonNull final Object object) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(object);
    }
}
