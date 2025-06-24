package com.itv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itv.entity.Registration;
import com.itv.entity.User;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUserId(Long userId);
    List<Registration> findByEventId(Long eventId);
    @Query("SELECT r.user FROM Registration r WHERE r.event.id = :eventId")
    List<User> findUsersByEventId(@Param("eventId") Long eventId);


}
