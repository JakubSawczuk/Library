package wat.ai.security2.repositories;

import org.springframework.data.repository.CrudRepository;
import wat.ai.models.entities.Librarian;

import java.util.Optional;

public interface SecurityRepository extends CrudRepository<Librarian, Integer> {
    Optional <Librarian> findLibrarianByUsernameAndPasswordHash(String username, String passwordHash);
    Optional <Librarian> findByUsername(String username);
}
