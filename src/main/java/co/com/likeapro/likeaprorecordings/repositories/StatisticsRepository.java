package co.com.likeapro.likeaprorecordings.repositories;

import co.com.likeapro.likeaprorecordings.models.Statistics;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StatisticsRepository extends R2dbcRepository<Statistics, Long> {

    @Query("SELECT data, COUNT(data) as count FROM statistics GROUP BY data ORDER BY count DESC LIMIT 1")
    Mono<Statistics> findMvp();

    @Query("SELECT recording, COUNT(recording) as count FROM statistics GROUP BY recording ORDER BY count DESC LIMIT 1")
    Mono<Statistics> findBestEvent();
}
