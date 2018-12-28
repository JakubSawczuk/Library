package wat.ai.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wat.ai.models.BookLoans;

import java.util.List;

@Repository
public interface BookLoansRepository extends CrudRepository<BookLoans, Integer> {

    @Query("SELECT bl.bookLoanId, bc.copyNumber, b.titlePL, b.titleEn, bl.loanDate, bl.plannedDueDate, bl.actualDueDate, bl.status " +
            "FROM BOOK_LOANS bl JOIN bl.bookCopy bc JOIN bc.book b" +
            " WHERE bl.status = :status AND bl.reader.readerId = :readerId ")
    List<Object[]> findByReaderIdAndStatus(@Param("readerId") Integer readerId,
                                           @Param("status") String status
    );
}
