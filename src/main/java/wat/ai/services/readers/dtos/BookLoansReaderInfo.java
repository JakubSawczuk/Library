package wat.ai.services.readers.dtos;

public class BookLoansReaderInfo {

    private String displayValue;
    private int readerId;

    public BookLoansReaderInfo(String displayValue, int readerId) {
        this.displayValue = displayValue;
        this.readerId = readerId;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }
}
