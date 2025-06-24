package com.itv.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.itv.entity.Event;
import com.itv.service.EventService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }
    

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id) {
        return eventService.getEventById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully!");
    }
    @PostMapping("/upload")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            String uploadDir = "uploads/";
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File dest = new File(uploadDir + filename);
            dest.getParentFile().mkdirs(); // create folder if it doesn’t exist
            file.transferTo(dest);
            return ResponseEntity.ok(filename); // Only return filename
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }


}
