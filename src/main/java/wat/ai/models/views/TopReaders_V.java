package wat.ai.models.views;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "top_readers_v")
public class TopReaders_V {

    @Id
    private String cardNumber;
    private String fullName;
    private String email;
    private String phone;
    private int bookLoansAmount;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBookLoansAmount() {
        return bookLoansAmount;
    }

    public void setBookLoansAmount(int bookLoansAmount) {
        this.bookLoansAmount = bookLoansAmount;
    }
}
