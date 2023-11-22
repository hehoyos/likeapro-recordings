package co.com.likeapro.likeaprorecordings.services;

import co.com.likeapro.likeaprorecordings.models.Customer;
import co.com.likeapro.likeaprorecordings.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CustomerService {

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;

    public Mono<Customer> saveCustomer(Customer customer) {
    return customerRepository.save(customer)
                .onErrorResume(throwable -> {
                    logger.error("Error saving the customer.", throwable);
                    return Mono.empty();
                });
    }

    public Mono<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id)
                .onErrorResume(throwable -> {
                    logger.error("Error finding the customer with ID: .{}", id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer with ID: " + id + " not found.").getMostSpecificCause()));
    }

    public Flux<Customer> findAllCustomers() {
        return customerRepository.findAll()
                .onErrorResume(throwable -> {
                    logger.error("Error finding all customers.", throwable);
                    return Flux.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customers not found.").getMostSpecificCause()));
    }

    public Mono<Customer> updateCustomer(Long id, Customer customer) {
        return this.findCustomerById(id)
                .flatMap(existingCustomer -> {
                    existingCustomer.setName(customer.getName());
                    existingCustomer.setEmail(customer.getEmail());
                    existingCustomer.setPassword(customer.getPassword());
                    existingCustomer.setPhone(customer.getPhone());
                    existingCustomer.setRole(customer.getRole());
                    existingCustomer.setStatus(customer.getStatus());
                    return customerRepository.save(existingCustomer);
                })
                .onErrorResume(throwable -> {
                    logger.error("Error updating the customer with ID: .{}", id, throwable);
                    return Mono.empty();
                });
    }

    public Mono<Void> deleteCustomer(Long id) {
        return this.findCustomerById(id)
                .flatMap(customerRepository::delete)
                .onErrorResume(throwable -> {
                    logger.error("Error deleting the customer with ID: .{}", id, throwable);
                    return Mono.empty();
                });
    }
}
