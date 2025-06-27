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
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class DateExampleBO implements DateExampleIBO {

    private final DateExampleIDAO idao;
    private final JwtUtil jwtUtil = new JwtUtil();

    public DateExampleBO(DateExampleIDAO idao) {
        this.idao = idao;
    }

    @Transactional
    public void saveEventDate(DateExampleDto dto, String token) {

        Claims claims = jwtUtil.extractClaims(token);
        String timeZone = claims.get("timeZone", String.class);
        String dateFormat = claims.get("dateFormat", String.class);

        Instant utcInstant = DateTimeConversionUtil.convertUserDateToUtc(dto.getEventDate(), dateFormat, timeZone);

        DateExampleEntity dateExampleEntity = new DateExampleEntity();
        dateExampleEntity.setEventName(dto.getEventName());
        dateExampleEntity.setEventDateTime(utcInstant);

        idao.saveEventDate(dateExampleEntity);
    }


    public List<DateExampleDto> findAllEvents(String token) {
        Claims claims = jwtUtil.extractClaims(token);
        String timeZone = claims.get("timeZone", String.class);
        String dateFormat = claims.get("dateFormat", String.class);

       return idao.findAllEvents().stream()
                .map( e -> DateExampleDto.builder()
                        .eventName(e.getEventName())
                        .eventDate(
                                DateTimeConversionUtil.convertUtcToUserDateString(
                                        e.getEventDateTime(),
                                        dateFormat,
                                        timeZone
                                )
                        )
                        .build()
                ).toList();

    }
}
