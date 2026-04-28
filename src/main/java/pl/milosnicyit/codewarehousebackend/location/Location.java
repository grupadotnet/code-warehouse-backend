package pl.milosnicyit.codewarehousebackend.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private Long id;
    private String name;
    private boolean active = true;
    private boolean empty = true;

    public Location(Long id, String name) {
        this.id = id;
        this.name = name;
        this.active = true;
        this.empty = true;
    }

    void deactivate() {
        if (!this.empty) {
            throw new IllegalStateException("Cannot delete location: it is not empty");
        }
        this.active = false;
    }
}