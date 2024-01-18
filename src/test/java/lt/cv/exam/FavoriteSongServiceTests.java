package lt.cv.exam;

import lt.cv.exam.model.Song;
import lt.cv.exam.persistance.FavoriteSongRepository;
import lt.cv.exam.persistance.model.FavoriteSongRecord;
import lt.cv.exam.service.FavoriteSongService;
import lt.cv.exam.service.UserService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest

public class FavoriteSongServiceTests {
    @Autowired
    FavoriteSongService favoriteSongService;

    @Autowired
    FavoriteSongRepository favoriteSongRepository;

    @Autowired
    UserService userService;

    @Test
    void serviceGetRecord() {
        userService.saveUser("11111");

        favoriteSongRepository.save(
                FavoriteSongRecord.builder()
                        .uuid("11111")
                        .artist("artist1")
                        .song("song1")
                        .build()
        );

        List<Song> songs = favoriteSongService.getFavoriteSongs("11111");

        assertEquals(1, songs.size());
        assertEquals("artist1", songs.get(0).getArtist());
        assertEquals("song1", songs.get(0).getTitle());
    }

    @Test
    void serviceSaveRecord() {
        userService.saveUser("22222");

        List<Song> songsRecords = favoriteSongService.getFavoriteSongs("22222");

        assertEquals(0, songsRecords.size());

        /* --- */

        ArrayList<Song> songs = new ArrayList<>();

        songs.add(Song.builder()
                .artist("artist1")
                .title("song1")
                .build()
        );

        favoriteSongService.addFavoriteSongs("22222", songs);

        /* --- */

        songsRecords = favoriteSongService.getFavoriteSongs("22222");

        assertEquals(1, songsRecords.size());
        assertEquals("artist1", songs.get(0).getArtist());
        assertEquals("song1", songs.get(0).getTitle());
    }

    @Test
    void serviceDeleteRecord() {
        userService.saveUser("33333");

        favoriteSongRepository.save(FavoriteSongRecord.builder()
                .uuid("33333")
                .artist("artist1")
                .song("song1")
                .build());

        List<Song> songs = favoriteSongService.getFavoriteSongs("33333");

        assertEquals(1, songs.size());

        favoriteSongService.deleteFavoriteSongs("33333", songs);

        songs = favoriteSongService.getFavoriteSongs("33333");

        assertEquals(0, songs.size());
    }

}
