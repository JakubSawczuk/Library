package wat.ai.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wat.ai.models.Reader;

import java.util.List;

@Repository
public interface ReaderRepository<R> extends CrudRepository<Reader, Integer>{
    List<Reader> findByIsActive(boolean isActive);
    Reader findByReaderId(int idReader);
}
