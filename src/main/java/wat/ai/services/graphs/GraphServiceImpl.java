package wat.ai.services.graphs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.models.views.BookLoansByGenre_V;
import wat.ai.repositories.GraphRepository;

import java.util.List;
import java.util.logging.Logger;

@Service
public class GraphServiceImpl implements IGraphService {
    static final Logger LOGGER = Logger.getLogger(GraphServiceImpl.class.getName());
    private final GraphRepository graphRepository;

    @Autowired
    public GraphServiceImpl(GraphRepository graphRepository) {
        this.graphRepository = graphRepository;
    }

    @Override
    public List<BookLoansByGenre_V> getBookLoansByGenre_V() {
        return graphRepository.readAllByNameIsNotNull();
    }
}
