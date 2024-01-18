package lt.cv.exam.persistance;

import lt.cv.exam.persistance.model.FavoriteSongRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteSongRepository extends JpaRepository<FavoriteSongRecord, Integer> {
    List<FavoriteSongRecord> findByUuid(String uuid);

    FavoriteSongRecord findByUuidAndArtistAndSong(String uuid, String artist, String song);

}