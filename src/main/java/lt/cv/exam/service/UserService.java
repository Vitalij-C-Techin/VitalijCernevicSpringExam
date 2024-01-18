package lt.cv.exam.service;

import lombok.RequiredArgsConstructor;
import lt.cv.exam.persistance.UserRepository;
import lt.cv.exam.persistance.model.UserRecord;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean hasUser(String uuid) {
        return null != userRepository.getByUuid(uuid);
    }

    public void saveUser(String uuid) {
        userRepository.save(new UserRecord(uuid));
    }

    public void deleteUser(String uuid) {
        userRepository.delete(new UserRecord(uuid));
    }
}