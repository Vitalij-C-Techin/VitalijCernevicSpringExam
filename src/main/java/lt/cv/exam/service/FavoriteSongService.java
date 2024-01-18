package lt.cv.exam.service;

import lombok.RequiredArgsConstructor;
import lt.cv.exam.exception.UserNotFoundException;
import lt.cv.exam.exception.ValidationException;
import lt.cv.exam.model.Song;
import lt.cv.exam.model.mapper.SongMapper;
import lt.cv.exam.persistance.FavoriteSongRepository;
import lt.cv.exam.persistance.model.FavoriteSongRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class FavoriteSongService {
    private final UserService userService;
    private final FavoriteSongRepository favoriteSongRepository;

    public List<Song> getFavoriteSongs(String uuid) {
        final String sanitizedUuid = sanitizeUuid(uuid);

        validateUuid(sanitizedUuid);
        validateUser(sanitizedUuid);

        List<FavoriteSongRecord> songRecords = favoriteSongRepository.findByUuid(sanitizedUuid);

        if (null == songRecords) {
            return new ArrayList<Song>();
        }

        return SongMapper.map(songRecords);
    }

    public void addFavoriteSongs(String uuid, List<Song> songs) {
        final String sanitizedUuid = sanitizeUuid(uuid);

        validateUuid(sanitizedUuid);

        userService.saveUser(sanitizedUuid);

        songs.forEach(song -> {
            FavoriteSongRecord songRecord = favoriteSongRepository.findByUuidAndArtistAndSong(sanitizedUuid, song.getArtist(), song.getTitle());

            if (null == songRecord) {
                favoriteSongRepository.save(
                        FavoriteSongRecord.builder()
                                .uuid(sanitizedUuid)
                                .artist(song.getArtist())
                                .song(song.getTitle())
                                .build()
                );
            }
        });
    }

    public void deleteFavoriteSongs(String uuid, List<Song> songs) {
        final String sanitizedUuid = sanitizeUuid(uuid);

        validateUuid(sanitizedUuid);

        songs.forEach(song -> {
            FavoriteSongRecord songRecord = favoriteSongRepository.findByUuidAndArtistAndSong(sanitizedUuid, song.getArtist(), song.getTitle());

            if (null != songRecord) {
                favoriteSongRepository.delete(songRecord);
            }
        });
    }

    private void validateUser(String uuid) {
        if (!userService.hasUser(uuid)) {
            throw new UserNotFoundException("User with uuid " + uuid + " not found");
        }
    }

    private void validateUuid(String uuid) {
        if (uuid.isBlank()) {
            throw new ValidationException("UUID is empty");
        }

        if (uuid.length() < 5) {
            throw new ValidationException("UUID is to short: " + uuid);
        }

        if (uuid.length() > 30) {
            throw new ValidationException("UUID is to long: " + uuid);
        }
    }

    private String sanitizeUuid(String uuid) {
        return uuid
                .toUpperCase(Locale.ROOT)
                .strip()
                .replaceAll(" ", "_");
    }
}
