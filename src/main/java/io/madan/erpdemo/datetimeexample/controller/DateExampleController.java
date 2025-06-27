package io.madan.erpdemo.datetimeexample.controller;

public class DateExampleController {
    private final UserDateService userDateService;

    public UserDateController(UserDateService userDateService) {
        this.userDateService = userDateService;
    }

    @PostMapping
    public ResponseEntity<?> saveDate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                      @RequestBody UserDateRequest request) {
        userDateService.saveDate(request, authHeader);
        return ResponseEntity.ok("Saved successfully");
    }
}
