package co.com.likeapro.likeaprorecordings.services;

import co.com.likeapro.likeaprorecordings.models.Customer;
import co.com.likeapro.likeaprorecordings.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;

    private Customer customer;
    private Mono<Customer> expectedCustomer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerService(customerRepository);
        LocalDateTime now = LocalDateTime.now();
        customer = new Customer(1L, "John Smith", "johnsmith1@example.com", "password1", "1234567890", "role1", true,
                now, now);
        expectedCustomer = Mono.just(customer);
    }

    @Test
    void saveCustomerTest() {
        when(customerRepository.save(customer)).thenReturn(expectedCustomer);

        Mono<Customer> result = customerService.saveCustomer(customer);
        result.subscribe();

        verify(customerRepository).save(customer);
    }

    @Test
    void findCustomerByIdTest() {
        when(customerRepository.findById(1L)).thenReturn(expectedCustomer);

        Mono<Customer> result = customerService.findCustomerById(1L);
        result.subscribe();

        verify(customerRepository).findById(1L);
    }

    @Test
    void findAllCustomersTest() {
        Flux<Customer> expectedCustomers = Flux.just(customer, customer);
        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        Flux<Customer> result = customerService.findAllCustomers();
        result.subscribe();

        verify(customerRepository).findAll();
    }

    @Test
    void updateCustomerTest() {
        when(customerRepository.findById(1L)).thenReturn(expectedCustomer);
        when(customerRepository.save(customer)).thenReturn(expectedCustomer);

        Mono<Customer> result = customerService.updateCustomer(1L, customer);
        result.subscribe();

        verify(customerRepository).findById(1L);
        verify(customerRepository).save(customer);
    }

    @Test
    void deleteCustomerTest() {
        when(customerRepository.findById(1L)).thenReturn(expectedCustomer);
        when(customerRepository.delete(customer)).thenReturn(Mono.empty());

        Mono<Void> result = customerService.deleteCustomer(1L);
        result.subscribe();

        verify(customerRepository).findById(1L);
        verify(customerRepository).delete(customer);
    }
}
