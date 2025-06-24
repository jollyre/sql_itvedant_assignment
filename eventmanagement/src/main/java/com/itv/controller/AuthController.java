package com.itv.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itv.entity.Event;
import com.itv.entity.User;
import com.itv.repository.EventRepository;
import com.itv.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Optional<User> user = userRepository.findByEmailAndPassword(email, password);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id, @RequestParam String role) {
        if (!"ADMIN".equalsIgnoreCase(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
        if (!eventRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
        eventRepository.deleteById(id);
        return ResponseEntity.ok("Event deleted successfully");
    }


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}