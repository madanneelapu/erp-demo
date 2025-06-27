package io.madan.erpdemo.datetimeexample.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DateExampleDto {
    private String eventName;
    private String eventDate;


}
