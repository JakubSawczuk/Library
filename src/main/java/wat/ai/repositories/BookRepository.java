package wat.ai.repositories;

import org.springframework.data.repository.CrudRepository;
import wat.ai.models.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
    Book findByBookId(int bookId);
}
