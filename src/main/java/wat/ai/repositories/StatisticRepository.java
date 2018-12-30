package wat.ai.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wat.ai.models.views.TopReaders_V;

import java.util.List;

@Repository
public interface StatisticRepository extends CrudRepository<TopReaders_V, String> {
    List<TopReaders_V> readAllByCardNumberIsNotNull();
}
