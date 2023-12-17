package co.com.likeapro.likeaprorecordings.models;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MvpInterface {

    Mono<Customer> getMvp();
}
