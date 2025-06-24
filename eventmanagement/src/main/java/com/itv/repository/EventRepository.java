package com.itv.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.itv.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
