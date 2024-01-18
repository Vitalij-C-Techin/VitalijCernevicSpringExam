package lt.cv.exam.persistance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.management.ConstructorParameters;

@Getter
@Setter
@Builder
@Entity
public class UserRecord {
    private String id;

    @Id
    private String uuid;
}
