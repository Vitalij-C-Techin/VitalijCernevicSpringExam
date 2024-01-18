package lt.cv.exam.persistance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FavoriteSongRecord {
    @Id
    private int id;
    private String uuid;
    private String artist;
    private String song;
}
