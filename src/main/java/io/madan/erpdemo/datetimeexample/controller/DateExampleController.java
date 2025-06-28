package io.madan.erpdemo.datetimeexample.controller;

import io.madan.erpdemo.datetimeexample.dto.DateExampleDto;
import io.madan.erpdemo.datetimeexample.ibo.DateExampleIBO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-events")
public class DateExampleController {

    private static final Logger logger = LogManager.getLogger(DateExampleController.class);


    private final DateExampleIBO ibo;

    public DateExampleController(DateExampleIBO ibo) {
        this.ibo = ibo;
    }

    @PostMapping
    public ResponseEntity<?> saveDate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                      @RequestBody DateExampleDto reqDto) {
        logger.debug(reqDto);
        logger.debug(authHeader);
        ibo.saveEventDate(reqDto, authHeader);
        return ResponseEntity.ok("Saved successfully");
    }

    @GetMapping
    public ResponseEntity<List<DateExampleDto>> listAllDates(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        List<DateExampleDto> list = ibo.findAllEvents(authHeader);
        return ResponseEntity.ok(list);
    }
}
