package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.CalendarDTO;
import com.medecineWebApp.Configuration.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calendars")
public class CalendarController {
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }
    @GetMapping("/calendar/{userId}")
    public ResponseEntity <CalendarDTO>  getCalendarByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(calendarService.getCalendarByUserId(userId));
    }
}
