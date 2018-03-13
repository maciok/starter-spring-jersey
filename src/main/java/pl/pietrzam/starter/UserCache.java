package pl.pietrzam.starter;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
class UserCache {
    private static final AtomicInteger indexer = new AtomicInteger(0);

    private final static Cache<Integer, String> users = CacheBuilder
            .newBuilder()
            .maximumSize(100)
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .build();

    String getUser(final int id) {
        try {
            return users.get(id, () -> "");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    int createUser(final String user) {
        final int id = indexer.incrementAndGet();
        users.put(id, user);
        return id;
    }

    void updateUser(final int id, final String user) {
        users.put(id, user);
    }
}
