package co.com.likeapro.likeaprorecordings.repositories;

import co.com.likeapro.likeaprorecordings.models.Recording;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordingRepository extends R2dbcRepository<Recording, Long> {
}
