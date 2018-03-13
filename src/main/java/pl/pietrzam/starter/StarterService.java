package pl.pietrzam.starter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class StarterService {

    private final UserCache userCache;

    String getUser(final int id) {
        return userCache.getUser(id);
    }

    int createUser(final String user) {
        return userCache.createUser(user);
    }

    void updateUser(final int id, final String user) {
        userCache.updateUser(id, user);
    }
}
