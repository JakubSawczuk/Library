package wat.ai.securities.repositories;

import org.springframework.data.repository.CrudRepository;
import wat.ai.models.entities.Librarian;

public interface SecurityRepository extends CrudRepository<Librarian, Integer> {
    Librarian findLibrarianByUsernameAndPasswordHash(String username, String passwordHash);
}
