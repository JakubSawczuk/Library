package wat.ai.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wat.ai.models.BookCopy;

import java.util.List;

@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Integer> {
    List<BookCopy> findByIsAvailable(boolean isAvailable);
    BookCopy findByBookCopyId(int bookCopyId);
}
