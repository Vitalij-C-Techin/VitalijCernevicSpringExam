package lt.cv.exam.model.mapper;

import lt.cv.exam.model.Song;
import lt.cv.exam.persistance.model.FavoriteSongRecord;

import java.util.List;

public class SongMapper {
    public static Song map(FavoriteSongRecord songRecord) {
        return Song.builder()
                .artist(songRecord.getArtist())
                .title(songRecord.getSong())
                .build();
    }

    public static List<Song> map(List<FavoriteSongRecord> songRecords) {
        return songRecords.stream()
                .map(SongMapper::map)
                .toList();
    }
}
