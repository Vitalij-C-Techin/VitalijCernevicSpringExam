package lt.cv.exam.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lt.cv.exam.model.Song;
import lt.cv.exam.service.FavoriteSongService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favourites")
@RequiredArgsConstructor
public class SongController {
    private final FavoriteSongService favoriteSongService;

    @GetMapping
    public List<Song> getSongs(
            @RequestParam String uuid
    ) {
        return favoriteSongService.getFavoriteSongs(uuid);
    }

    @PostMapping
    public List<Song> addSongs(
            @RequestParam String uuid,
            @RequestBody List<Song> songs
    ) {
        favoriteSongService.addFavoriteSongs(uuid, songs);

        return favoriteSongService.getFavoriteSongs(uuid);
    }

    @DeleteMapping
    public List<Song> deleteSongs(
            @RequestParam String uuid,
            @RequestBody List<Song> songs
    ) {
        favoriteSongService.deleteFavoriteSongs(uuid, songs);

        return favoriteSongService.getFavoriteSongs(uuid);
    }
}
