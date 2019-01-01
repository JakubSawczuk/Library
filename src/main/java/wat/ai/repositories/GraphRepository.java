package wat.ai.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wat.ai.models.views.BookLoansByGenre_V;

import java.util.List;

@Repository
public interface GraphRepository extends CrudRepository<BookLoansByGenre_V, String>{
    List<BookLoansByGenre_V> readAllByNameIsNotNull();
}
