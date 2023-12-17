package co.com.likeapro.likeaprorecordings.models;

import reactor.core.publisher.Mono;

public interface BestEventInterface {

    Mono<Event> getBestEvent();
}
