package io.madan.erpdemo.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class DateTimeConversionUtil {

    /**
     * Converts a date string in user's timezone to UTC Instant.
     *
     * @param dateString   the input date string (e.g., "27-06-2025")
     * @param dateFormat   format of the input string (e.g., "dd-MM-yyyy")
     * @param userTimeZone timezone like "Asia/Kolkata"
     * @return Instant (in UTC)
     */
    public static Instant convertUserDateToUtc(String dateString, String dateFormat, String userTimeZone) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of(userTimeZone));
        return zonedDateTime.toInstant(); // UTC
    }

    /**
     * Converts a UTC Instant to formatted string in user's timezone.
     *
     * @param instant      UTC timestamp
     * @param dateFormat   desired format (e.g., "dd-MM-yyyy")
     * @param userTimeZone timezone like "Asia/Kolkata"
     * @return formatted date string in user's timezone
     */
    public static String convertUtcToUserDateString(Instant instant, String dateFormat, String userTimeZone) {
        ZonedDateTime userZonedDateTime = instant.atZone(ZoneId.of(userTimeZone));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH);
        return userZonedDateTime.format(formatter);
    }
}
