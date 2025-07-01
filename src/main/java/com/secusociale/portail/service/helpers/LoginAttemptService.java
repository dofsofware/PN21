package com.secusociale.portail.service.helpers;

import com.google.common.cache.LoadingCache;
import com.secusociale.portail.domain.AttemptCache;
import com.secusociale.portail.repository.AttemptCacheRepository;
import com.secusociale.portail.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 10;
    @Autowired
    UserRepository userService;
    @Autowired
    AttemptCacheRepository cacheRepository;
    private LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService() {

    }

    public void loginSucceeded(String key, String address) {
        String k = String.format("%s_%s", key, address);
        cacheRepository.findByKey(k).ifPresent(attemptCache -> {
            userService.findOneByLogin(key).ifPresent(user -> {
                if (user.getLocked()) {
                    user.setLocked(false);
                    userService.save(user);
                }
            });
            cacheRepository.delete(attemptCache);
        });
    }

    public void loginFailed(String key, String address) {
        String k = String.format("%s_%s", key, address);
        int attempts = 0;
        if (cacheRepository.findByKey(k).isPresent()) {
            AttemptCache attemptCache = cacheRepository.findByKey(k).get();
            int val = attemptCache.getValue() + 1;
            cacheRepository.save(attemptCache.value(val));
            if (val >= MAX_ATTEMPT) {
                userService.findOneByLogin(key).ifPresent(user -> {
                    user.setLocked(true);
                    userService.save(user);
                });
            }
        } else {
            cacheRepository.save(new AttemptCache().key(k).value(1));
        }
    }

    public boolean isBlocked(String key) {
        return cacheRepository.findByKey(key.split("_")[0]).map(AttemptCache::getValue).isPresent() && cacheRepository.findByKey(key.split("_")[0]).map(AttemptCache::getValue).get() >= MAX_ATTEMPT;
    }
}

