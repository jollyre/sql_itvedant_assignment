package com.itv;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itv.entity.Event;
import com.itv.repository.EventRepository;

@SpringBootTest
public class producttesting {
    Event p = new Event();

    @Autowired
    private EventRepository  repository;
    @Test
    void testReadAll(){

        List<Event>list= repository.findAll();
        boolean condition =list.size()>0;
        assertEquals(true, condition);
    }
    @Test
    void testread(){
        Event p = repository.findById((long) 14).get();
        assertEquals("T hane", p.getLocation());
    }
    @Test
    void testcreate(){
        
        p.setLocation("thane");
        p.setDescription("music, dance and celbration");
        p.setTitle("birthday party");
        repository.save(p);
        assertNotNull(repository.findById((long) 100).orElse(p));
    }
    @Test
    void testdelete(){
        repository.deleteById((long) 16);
        assertEquals(false, repository.existsById((long) 16));
    }

 @Test
void testupdate() {

    Event p = new Event(); 
    p.setId(14L);
    p.setTitle("Birthday Party");
    p.setLocation("Andheri");
    repository.save(p);


    p.setTitle("Wedding Anniversary");
    p.setLocation("Bandra");
    repository.save(p);

  
    Event updatedEvent = repository.findById(14L).orElse(null);

    
    assertNotNull(updatedEvent);
    assertEquals("Wedding Anniversary", updatedEvent.getTitle());
    assertEquals("Bandra", updatedEvent.getLocation());
}

}
