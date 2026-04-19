package pl.milosnicyit.codewarehousebackend.controllers.v1.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TokenResponse {
    private String token;
}
