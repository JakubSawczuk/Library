package wat.ai.models.views;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "book_loans_by_genre_v")
public class BookLoansByGenre_V {

    @Id
    public String genreName;

    public int bookLoansAmount;

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public int getBookLoansAmount() {
        return bookLoansAmount;
    }

    public void setBookLoansAmount(int bookLoansAmount) {
        this.bookLoansAmount = bookLoansAmount;
    }
}
