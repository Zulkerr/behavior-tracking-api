package com.example.behavior_tracking_api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerEventRepository extends JpaRepository<CustomerEvent, Long> {
    List<CustomerEvent> findByUserId(String userId);
}
