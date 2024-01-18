package lt.cv.exam.persistance;

import lt.cv.exam.persistance.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserRecord, Integer> {
    UserRecord getByUuid(String uuid);
}
