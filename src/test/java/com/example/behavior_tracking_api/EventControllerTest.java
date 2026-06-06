package com.example.behavior_tracking_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerEventRepository repository;

    @Test
    void testRecordEvent() throws Exception {
        when(repository.save(any(CustomerEvent.class))).thenReturn(new CustomerEvent());

        String json = """
                {"userId": "user_999", "eventType": "CLICK", "productId": "book_1"}
                """;

        mockMvc.perform(
                        post("/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
        )
                        .andExpect(status().isCreated())
                        .andExpect(content().string("Event für User user_999 gespeichert!"));
    }

    @Test
    void testGetEvents() throws Exception {
        CustomerEvent fakeEvent = new CustomerEvent("user_999", "CLICK", "book_1");

        when(repository.findByUserId("user_999")).thenReturn(List.of(fakeEvent));

        mockMvc.perform(get("/events/user_999"))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].userId").value("user_999"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].eventType").value("CLICK"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].productId").value("book_1"));
    }
}