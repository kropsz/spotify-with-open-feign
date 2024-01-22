package tech.krop.spotify.entities.albums;

import java.util.List;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AlbumWrapper {
    private List<Album> items;

    public List<Album> getItems() {
        return items;
    }

    public void setItems(List<Album> items) {
        this.items = items;
    }

    public AlbumWrapper() {
    }

    public AlbumWrapper(List<Album> items) {
        this.items = items;
    }

    
    
}
