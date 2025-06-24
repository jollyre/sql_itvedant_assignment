package com.itv.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itv.entity.Event;
import com.itv.entity.Registration;
import com.itv.entity.User;
import com.itv.repository.EventRepository;
import com.itv.repository.RegistrationRepository;
import com.itv.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistrationService {
	

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public Registration registerUserForEvent(Long userId, Long eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Registration registration = Registration.builder()
                .user(user)
                .event(event)
                .registrationDate(LocalDateTime.now())
                .build();

        return registrationRepository.save(registration);
    }

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public List<Registration> getRegistrationsByUserId(Long userId) {
        return registrationRepository.findByUserId(userId);
    }
    public List<Registration> getRegistrationsByEvent(Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }
    public List<User> getUsersByEvent(Long eventId) {
        return registrationRepository.findUsersByEventId(eventId);
    }
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }



}
