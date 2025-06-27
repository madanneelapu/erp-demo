package io.madan.erpdemo.datetimeexample.dao;

import io.madan.erpdemo.datetimeexample.entity.DateExampleEntity;
import io.madan.erpdemo.datetimeexample.idao.DateExampleIDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class DateExampleDAO implements DateExampleIDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveEventDate(DateExampleEntity entity) {
        entityManager.persist(entity);
    }


}
