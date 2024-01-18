package lt.cv.exam;

import lt.cv.exam.controller.SongController;
import lt.cv.exam.persistance.model.FavoriteSongRecord;
import lt.cv.exam.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import .

@SpringBootTest
class ExamApplicationTests {
	@Autowired
	SongController songController;

	FavoriteSongRecord favoriteSongRecord;

	UserService userService;


	@Test
	void contextLoads() {
		//assertNotNull(songController);
	}

}
