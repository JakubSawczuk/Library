package wat.ai.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wat.ai.models.Book;

import java.util.List;
@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    Book findByBookId(int bookId);
    List<Book> findByIsActive(boolean isActive);
}
