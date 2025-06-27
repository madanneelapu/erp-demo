package io.madan.erpdemo.datetimeexample.ibo;

import io.madan.erpdemo.datetimeexample.dto.DateExampleDto;

import java.util.List;

public interface DateExampleIBO {
    public void saveEventDate(DateExampleDto request, String token);
    public List<DateExampleDto> findAllEvents(String token);
}
