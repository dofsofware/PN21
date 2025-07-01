package com.secusociale.portail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class SqlService {
    private EntityManager entityManager;

    public void execute(String sql) {
        entityManager.createNativeQuery("").executeUpdate();
    }
}
