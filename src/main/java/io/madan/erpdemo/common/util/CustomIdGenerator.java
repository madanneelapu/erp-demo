package io.madan.erpdemo.common.util;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class CustomIdGenerator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("yyyyMMdd'T'HHmmssSSS'Z'")
            .withZone(ZoneOffset.UTC);

    public static String generateId(String prefix) {
        String timestamp = FORMATTER.format(Instant.now());
        return prefix + "-" + timestamp;
    }

}
