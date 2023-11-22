package co.com.likeapro.likeaprorecordings.repositories;

import co.com.likeapro.likeaprorecordings.models.Customer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends R2dbcRepository<Customer, Long> {
}
