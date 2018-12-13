package wat.ai.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Component
public class DatabaseConnector {
    @Bean
    public EntityManager entityManager(){
        EntityManager entityManager = null;
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("biblioteka");
            entityManager = entityManagerFactory.createEntityManager();
            System.out.println("Create entityManager");
        }catch (Exception e){
            e.printStackTrace();
        }
        return entityManager;
    }
}
