package wat.ai.services.bookloans.enums;

public enum BookLoansStatusE {

    BORROWED("BORROWED"),
    RETURNED("RETURNED");

    private String desc;

    BookLoansStatusE(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
