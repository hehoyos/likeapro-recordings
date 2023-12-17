package co.com.likeapro.likeaprorecordings.controllers;

import co.com.likeapro.likeaprorecordings.models.Customer;
import co.com.likeapro.likeaprorecordings.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;

    private Customer customer;
    private Mono<Customer> expectedCustomer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        LocalDateTime now = LocalDateTime.now();
        customer = new Customer(1L, "John Smith", "johnsmith1@example.com", "password1", "1234567890",
                "role1", true, now, now);
        expectedCustomer = Mono.just(customer);
    }

    @Test
    void createCustomerTest() {
        when(customerService.saveCustomer(customer)).thenReturn(expectedCustomer);

        Mono<Customer> result = customerController.createCustomer(customer);
        result.subscribe();

        assertEquals(expectedCustomer, result);
    }

    @Test
    void readAllCustomersTest() {
        Flux<Customer> expectedCustomers = Flux.just(customer, customer);
        when(customerService.findAllCustomers()).thenReturn(expectedCustomers);

        Flux<Customer> result = customerController.readAllCustomers();
        result.subscribe();

        assertEquals(expectedCustomers, result);
    }

    @Test
    void readCustomerTest() {
        when(customerService.findCustomerById(1L)).thenReturn(expectedCustomer);

        Mono<Customer> result = customerController.readCustomer(1L);
        result.subscribe();

        assertEquals(expectedCustomer, result);
    }

    @Test
    void updateCustomerTest() {
        when(customerService.findCustomerById(1L)).thenReturn(expectedCustomer);
        when(customerService.updateCustomer(1L, customer)).thenReturn(expectedCustomer);

        Mono<Customer> result = customerController.updateCustomer(1L, customer);
        result.subscribe();

        assertEquals(expectedCustomer, result);
    }

    @Test
    void deleteCustomerTest() {
        when(customerService.findCustomerById(1L)).thenReturn(expectedCustomer);
        when(customerService.deleteCustomer(1L)).thenReturn(Mono.empty());

        Mono<Void> result = customerController.deleteCustomer(1L);
        result.subscribe();

        assertEquals(Mono.empty(), result);
    }
}
