package lt.cv.exam;

import lt.cv.exam.controller.SongController;
import lt.cv.exam.service.FavoriteSongService;
import lt.cv.exam.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ExamApplicationTests {
    @Autowired
    SongController songController;

    @Autowired
    FavoriteSongService favoriteSongService;

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        assertNotNull(songController);
        assertNotNull(favoriteSongService);
        assertNotNull(userService);
    }
}