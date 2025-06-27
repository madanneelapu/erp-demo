package io.madan.erpdemo.datetimeexample.bo;

import io.jsonwebtoken.Claims;
import io.madan.erpdemo.common.util.DateTimeConversionUtil;
import io.madan.erpdemo.common.util.JwtUtil;
import io.madan.erpdemo.datetimeexample.dto.DateExampleDto;
import io.madan.erpdemo.datetimeexample.entity.DateExampleEntity;
import io.madan.erpdemo.datetimeexample.ibo.DateExampleIBO;
import io.madan.erpdemo.datetimeexample.idao.DateExampleIDAO;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class DateExampleBO implements DateExampleIBO {

    private final DateExampleIDAO idao;
    private final JwtUtil jwtUtil = new JwtUtil();

    public DateExampleBO(DateExampleIDAO idao) {
        this.idao = idao;
    }

    public void saveEventDate(DateExampleDto request, String token) {
        Claims claims = jwtUtil.extractClaims(token);
        String timeZone = claims.get("timeZone", String.class);
        String dateFormat = claims.get("dateFormat", String.class);

        Instant utcInstant = DateTimeConversionUtil.convertUserDateToUtc(
                request.getEventDate(),
                dateFormat,
                timeZone
        );

        DateExampleEntity dateExampleEntity = new DateExampleEntity();
        dateExampleEntity.setEventDateTime(utcInstant);

        idao.saveEventDate(dateExampleEntity);
    }

    // Optional: method to retrieve formatted UTC date in user's timezone
    public String getFormattedDateForUser(Instant storedUtcInstant, String token) {
        Claims claims = jwtUtil.extractClaims(token);
        String timeZone = claims.get("timeZone", String.class);
        String dateFormat = claims.get("dateFormat", String.class);

        return DateTimeConversionUtil.convertUtcToUserDateString(
                storedUtcInstant,
                dateFormat,
                timeZone
        );
    }
}
