package wat.ai.services.graphs;

import wat.ai.models.views.BookLoansByGenre_V;

import java.util.List;

public interface IGraphService {
    List<BookLoansByGenre_V> getBookLoansByGenre_V();
}
