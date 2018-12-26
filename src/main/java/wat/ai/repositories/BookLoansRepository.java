package wat.ai.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wat.ai.models.BookLoans;

@Repository
public interface BookLoansRepository  extends CrudRepository<BookLoans, Integer>{
}
