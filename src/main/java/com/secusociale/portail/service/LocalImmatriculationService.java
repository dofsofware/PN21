package com.secusociale.portail.service;

import com.secusociale.portail.repository.LocalimmatriculationRepository;
import com.secusociale.portail.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LocalImmatriculationService {
    private final UserRepository userRepository;
    private final LocalimmatriculationRepository localimmatriculationRepository;


    public LocalImmatriculationService(UserRepository userRepository, LocalimmatriculationRepository localimmatriculationRepository) {
        this.userRepository = userRepository;
        this.localimmatriculationRepository = localimmatriculationRepository;
    }

    public Integer countLocalImmatsByUser(String username) {
        if (userRepository.findOneByLogin(username).isPresent()) {
            return localimmatriculationRepository.findAllByUser(userRepository.findByLogin(username).getId()).size();
        }
        return 0;
    }


}
