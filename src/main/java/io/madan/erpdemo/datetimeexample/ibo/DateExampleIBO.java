package io.madan.erpdemo.datetimeexample.ibo;

import io.madan.erpdemo.datetimeexample.dto.DateExampleDto;

public interface DateExampleIBO {
    public void saveEventDate(DateExampleDto request, String token);
}
