package wat.ai.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DatabaseConnector {
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnector.class.getName());

    @Bean
    public EntityManager entityManager(){
        EntityManager entityManager = null;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
        try {
            entityManager = entityManagerFactory.createEntityManager();
            LOGGER.log(Level.FINE, "Connector to database was corectly created");
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }

        return entityManager;
    }
}
