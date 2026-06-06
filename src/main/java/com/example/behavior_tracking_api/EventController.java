package com.example.behavior_tracking_api;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final CustomerEventRepository repository;

    public EventController(CustomerEventRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String recordEvent(@Valid @RequestBody EventRequest request){
        CustomerEvent newEvent = new CustomerEvent(
                request.userId(),
                request.eventType(),
                request.productId()
        );

        repository.save(newEvent);

        return "Event für User: " + request.userId() + " gespeichert! ";
    }

    @GetMapping("/{userId}")
    public List<CustomerEvent> getEventByUser(@PathVariable String userId){
        return repository.findByUserId(userId);
    }

}
