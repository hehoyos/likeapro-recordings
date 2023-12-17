package co.com.likeapro.likeaprorecordings.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTest {

    private Customer customer;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        now = LocalDateTime.now();
        customer = new Customer(1L, "John Smith", "johnsmith1@example.com", "password1", "1234567890", "role1", true,
                now, now);
        customer.setId(1L);
    }

    @Test
    void getId() {
        assertEquals(1L, customer.getId());
    }

    @Test
    void getName() {
        assertEquals("John Smith", customer.getName());
    }

    @Test
    void getEmail() {
        assertEquals("johnsmith1@example.com", customer.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("password1", customer.getPassword());
    }

    @Test
    void getPhone() {
        assertEquals("1234567890", customer.getPhone());
    }

    @Test
    void getRole() {
        assertEquals("role1", customer.getRole());
    }

    @Test
    void getStatus() {
        assertTrue(customer.getStatus());
    }

    @Test
    void getCreatedAt() {
        assertEquals(now, customer.getCreatedAt());
    }

    @Test
    void getUpdatedAt() {
        assertEquals(now, customer.getUpdatedAt());
    }
}
