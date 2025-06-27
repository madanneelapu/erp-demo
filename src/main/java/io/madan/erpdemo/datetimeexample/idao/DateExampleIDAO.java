package io.madan.erpdemo.datetimeexample.idao;

import io.madan.erpdemo.datetimeexample.entity.DateExampleEntity;

import java.util.List;

public interface DateExampleIDAO {
    public void saveEventDate(DateExampleEntity entity);
    public List<DateExampleEntity> findAllEvents();
}
