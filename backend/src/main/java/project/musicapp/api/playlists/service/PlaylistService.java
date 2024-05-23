package project.musicapp.api.playlists.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.musicapp.api.playlists.dto.PlaylistDTO;
import project.musicapp.api.playlists.mapper.PlaylistMapper;
import project.musicapp.api.playlists.repository.PlaylistRepository;

import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    private List<Object[]> getPlaylists(String value, int limit, int offset) {
        return this.playlistRepository.findAllPlaylistsByName(value, limit, offset);
    }

    public List<PlaylistDTO> findAllPlayListsByName(String value, int limit, int offset){
        List<Object[]> playlists = getPlaylists(value, limit, offset);
        return new PlaylistMapper(playlists).toPlaylistDTOs();
    }
}