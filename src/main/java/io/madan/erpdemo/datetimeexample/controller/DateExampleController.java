package io.madan.erpdemo.datetimeexample.controller;

import io.madan.erpdemo.datetimeexample.dto.DateExampleDto;
import io.madan.erpdemo.datetimeexample.ibo.DateExampleIBO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-date")
public class DateExampleController {
    private final DateExampleIBO ibo;

    public DateExampleController(DateExampleIBO ibo) {
        this.ibo = ibo;
    }

    @PostMapping
    public ResponseEntity<?> saveDate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                      @RequestBody DateExampleDto reqDto) {
        ibo.saveEventDate(reqDto, authHeader);
        return ResponseEntity.ok("Saved successfully");
    }
}
