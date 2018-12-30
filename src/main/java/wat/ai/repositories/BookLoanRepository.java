package wat.ai.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wat.ai.models.entities.BookLoans;

import java.util.List;

@Repository
public interface BookLoanRepository extends CrudRepository<BookLoans, Integer> {
    BookLoans findByBookLoanId(int bookLoanId);

    List<BookLoans> findByReaderReaderIdAndStatus(@Param("readerId") Integer readerId,
                                                  @Param("status") String status
    );
}
