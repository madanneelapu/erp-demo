package io.madan.erpdemo.common.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtil {
    public static String formatInstantToZone(Instant instant, String zoneIdString) {
        if (instant == null) return null;

        ZoneId zoneId = resolveZoneId(zoneIdString);
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z", Locale.ENGLISH);
        return formatter.format(zonedDateTime);
    }

    public static ZoneId resolveZoneId(String dateFormat) {
        // Fallback if unknown
        return switch (dateFormat.toUpperCase()) {
            case "IST" -> ZoneId.of("Asia/Kolkata");
            case "UTC" -> ZoneId.of("UTC");
            case "PST" -> ZoneId.of("America/Los_Angeles");
            default -> ZoneId.of("UTC");
        };
    }
}
