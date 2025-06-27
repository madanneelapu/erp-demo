package io.madan.erpdemo.datetimeexample.dao;

import io.madan.erpdemo.datetimeexample.entity.DateExampleEntity;
import io.madan.erpdemo.datetimeexample.idao.DateExampleIDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DateExampleDAO implements DateExampleIDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveEventDate(DateExampleEntity entity) {
        entityManager.persist(entity);
    }

    public List<DateExampleEntity> findAllEvents() {
        return entityManager.createQuery("SELECT u FROM DateExampleEntity u", DateExampleEntity.class)
                .getResultList();
    }

}
