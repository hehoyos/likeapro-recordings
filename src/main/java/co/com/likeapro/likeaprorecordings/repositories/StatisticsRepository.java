package co.com.likeapro.likeaprorecordings.repositories;

import co.com.likeapro.likeaprorecordings.models.Statistics;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends R2dbcRepository<Statistics, Long> {
}
