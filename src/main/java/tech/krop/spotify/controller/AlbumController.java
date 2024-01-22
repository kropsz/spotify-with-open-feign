package tech.krop.spotify.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.krop.spotify.client.AlbumSpotifyClient;
import tech.krop.spotify.client.AuthSpotifyClient;
import tech.krop.spotify.entities.LoginRequest;
import tech.krop.spotify.entities.albums.Album;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> helloWorld() {
        var request = new LoginRequest(
                "client_credentials",
                "f7e9856a90ce447a9de963c0111bdcea",
                "46e7cfd035d641d3ba79b1e2bed44944");

        var token = authSpotifyClient.login(request).getAccessToken();
        var response = albumSpotifyClient.getReleases("Bearer " + token);

        if (response != null && response.getAlbums() != null) {
            return ResponseEntity.ok(response.getAlbums().getItems());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
