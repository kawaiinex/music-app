package project.musicapp.api.playlists.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.musicapp.api.playlists.dto.PlaylistCreateDTO;
import project.musicapp.api.playlists.dto.PlaylistCreateResponseDTO;
import project.musicapp.api.playlists.dto.PlaylistDTO;
import project.musicapp.api.playlists.dto.PlaylistUserSongsDTO;
import project.musicapp.api.tokens.service.AccessTokenService;
import project.musicapp.api.users.model.User;
import project.musicapp.api.users.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final UserService userService;
    private final AccessTokenService accessTokenService;
    private final PlaylistQueryService playlistQueryService;

    public PlaylistUserSongsDTO findPlaylistUserSongsById(int id){
        return playlistQueryService.findPlaylistUserSongsById(id);
    }

    public List<PlaylistUserSongsDTO> findAllPlaylistUserSongsByUserId(int userId, int limit, int offset){
        return playlistQueryService.getAllPlaylistUserSongsByUserId(userId, limit, offset);
    }

    public List<PlaylistDTO> findAllPlayListsByName(String value, int limit, int offset){
        return this.playlistQueryService.findAllPlayListsByName(value, limit, offset);
    }

    public ResponseEntity<PlaylistCreateResponseDTO> createPlaylist(HttpHeaders header,
                                                                    PlaylistCreateDTO playlistCreateDTO) {
        String accessToken = this.accessTokenService.extractTokenFromHeaders(header);
        User user = this.userService.getUserFromAccessToken(accessToken).orElseThrow(
            () -> new IllegalArgumentException("Invalid access token")
        );
        return createPlaylistWithResponse(playlistCreateDTO, user);
    }

    private ResponseEntity<PlaylistCreateResponseDTO> createPlaylistWithResponse(
            PlaylistCreateDTO playlistCreateDTO, User user
    ) {
        if(this.playlistQueryService.isPresentPlaylist(playlistCreateDTO, user)){
            return ResponseEntity.badRequest().body(
                new PlaylistCreateResponseDTO("POST", "Playlist already exists", false)
            );
        }

        this.playlistQueryService.createPlaylist(playlistCreateDTO, user);

        return ResponseEntity.ok().body(
            new PlaylistCreateResponseDTO("POST", "Playlist created successfully", true)
        );
    }
}
