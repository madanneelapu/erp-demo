package io.madan.erpdemo.ordermanagement.repository.impl;

import io.madan.erpdemo.ordermanagement.repository.DocOrderJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DocOrderJdbcRepositoryImpl implements DocOrderJdbcRepository {


    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public DocOrderJdbcRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void meth1() {

    }


}
