package com.example.behavior_tracking_api;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer_events")
public class CustomerEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String eventType;

    private String productId;

    @Column (nullable = false)
    private LocalDateTime timestamp;

    public CustomerEvent(){}

    public CustomerEvent(String userId, String eventType, String productId){
        this.userId = userId;
        this.eventType = eventType;
        this.productId = productId;
        this.timestamp = LocalDateTime.now();
    }
    public Long getId() {return id;}
    public String getUserId() {return userId;}
    public String getEventType() {return eventType;}
    public String getProductId() {return productId;}
    public LocalDateTime getTimestamp() {return timestamp;}
}


