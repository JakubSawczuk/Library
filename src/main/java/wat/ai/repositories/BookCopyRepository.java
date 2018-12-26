package wat.ai.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wat.ai.models.BookCopy;

import java.util.List;

@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Integer> {
    List<BookCopy> findByIsAvailable(boolean isAvailable);

    @Query("SELECT bc FROM BOOK_COPY bc WHERE bc.book.bookId = :bookId")
    List<BookCopy> findByBookId(@Param("bookId") Integer bookId);
}
